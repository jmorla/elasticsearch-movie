import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie.model';
import { environment as env } from '../../../environments/environment';
import { MovieSearchRequest } from '../model/movie-search-request.model';

@Injectable({
    providedIn: 'root'
})
export class MovieService {

    constructor (private http: HttpClient) {}

    getAllMovies(pageable: {page: number, size: number}): Observable<Array<Movie>> {
        return this.http.get<Array<Movie>>(env.apiUrl + `/movies?page=${pageable.page}&size=${pageable.size}`);
    }

    getAllMoviesMatchCriteria(request: MovieSearchRequest, pageable: {page: number, size: number}): Observable<Array<Movie>>  {
        let params = new HttpParams();
        params = params.append('page', pageable.page + '');
        params = params.append('size', pageable.size + '');
        for(const key in request) {
            if(request[key] !== '') {
                params = params.append(key, request[key]);
            }
        }
        return this.http.get<Array<Movie>>(env.apiUrl + '/movies/_search', { params });
    }
}