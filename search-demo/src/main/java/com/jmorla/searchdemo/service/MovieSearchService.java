package com.jmorla.searchdemo.service;

import com.jmorla.searchdemo.controller.dto.MovieSearchRequest;
import com.jmorla.searchdemo.domain.Movie;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public MovieSearchService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchOperations = elasticsearchRestTemplate;
    }


    public List<Movie> searchAllMoviesMatchCriteria(MovieSearchRequest request, Pageable pageable) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (!checkNullOrEmpty(request.getTitle())) {
            boolQuery.must(QueryBuilders.wildcardQuery("Title", "*" + request.getTitle().toLowerCase() + "*"));
        }

        if(!checkNullOrEmpty(request.getDirector())) {
            boolQuery.must(QueryBuilders.matchPhraseQuery("Director", request.getDirector().toLowerCase()));
        }

        if(!checkNullOrEmpty(request.getDistributor())) {
            boolQuery.must(QueryBuilders.matchPhraseQuery("Distributor", request.getDistributor().toLowerCase()));
        }

        if(!checkNullOrEmpty(request.getType())) {
            boolQuery.must(QueryBuilders.matchPhraseQuery("Major_Genre", request.getType().toLowerCase()));
        }

        if(!checkNullOrEmpty(request.getProductionBudgetOp()) &&
                request.getProductionBudgetMin() > 0) {
            String op = request.getProductionBudgetOp();
            switch (op) {
                case "gt": {
                    boolQuery.filter(QueryBuilders.rangeQuery("Production_Budget").gt(request.getProductionBudgetMin()));
                    break;
                }
                case "lt": {
                    boolQuery.filter(QueryBuilders.rangeQuery("Production_Budget").lt(request.getProductionBudgetMin()));
                    break;
                }
                case "bt": {
                    if(request.getProductionBudgetMin() > request.getProductionBudgetMax()) {
                        throw new IllegalArgumentException("ProductionBudgetMin is grater than ProductionBudgetMax");
                    }
                    boolQuery.filter(QueryBuilders.rangeQuery("Production_Budget")
                            .from(request.getProductionBudgetMin())
                            .to(request.getProductionBudgetMax()));
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid Production Budget Operator: eq|gt|lt|bt");
                }
            }
        }
        builder.withQuery(boolQuery);
        builder.withPageable(pageable);
        return elasticsearchOperations.queryForList(builder.build(), Movie.class);
    }


    public List<String> getAllDirectors() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders
                        .terms("directors")
                        .field("Director.keyword")
                        .size(10000))
                .withPageable(PageRequest.of(0, 1))
                .build();

        Terms directors = elasticsearchOperations.query(query, response -> response.getAggregations().get("directors"));
        return directors.getBuckets()
                .stream()
                .map(Terms.Bucket::getKeyAsString)
                .collect(Collectors.toList());

    }


    public List<String> getAllDistributor() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders
                        .terms("distributors")
                        .field("Distributor.keyword")
                        .size(10000))
                .withPageable(PageRequest.of(0, 1))
                .build();

        Terms directors = elasticsearchOperations.query(query, response -> response.getAggregations().get("distributors"));
        return directors.getBuckets()
                .stream()
                .map(Terms.Bucket::getKeyAsString)
                .collect(Collectors.toList());
    }

    public List<String> getAllTypes() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(AggregationBuilders
                        .terms("movie_type")
                        .field("Major_Genre.keyword")
                        .size(10000))
                .withPageable(PageRequest.of(0, 1))
                .build();

        Terms directors = elasticsearchOperations.query(query, response -> response.getAggregations().get("movie_type"));
        return directors.getBuckets()
                .stream()
                .map(Terms.Bucket::getKeyAsString)
                .collect(Collectors.toList());
    }


    private static boolean checkNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
