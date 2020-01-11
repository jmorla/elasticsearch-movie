package com.jmorla.searchdemo.service;

import com.jmorla.searchdemo.controller.dto.MovieSearchRequest;
import com.jmorla.searchdemo.domain.Movie;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if(!checkNullOrEmpty(request.getProductionBudgetOp())) {
            String op = request.getProductionBudgetOp();
            switch (op) {
                case "gt": {
                    System.out.println("Grater than " + request.getProductionBudgetMin());
                    boolQuery.filter(QueryBuilders.rangeQuery("Production_Budget").gt(request.getProductionBudgetMin()));
                    break;
                }
                case "lt": {
                    System.out.println("Less than " + request.getProductionBudgetMin());
                    boolQuery.filter(QueryBuilders.rangeQuery("Production_Budget").lt(request.getProductionBudgetMin()));
                    break;
                }
                case "bt": {
                    System.out.println("Between {0} and {1} "
                            .replace("{0}", String.valueOf(request.getProductionBudgetMin()))
                            .replace("{1}", String.valueOf(request.getProductionBudgetMax())));

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
        return elasticsearchOperations.queryForList(builder.build(), Movie.class);
    }


    private static boolean checkNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
