import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie.model';
import { environment as env } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class MovieService {

    constructor (private http: HttpClient) {}

    getAllMovies(pageable: {page: number, size: number}): Observable<Array<Movie>> {
        return this.http.get<Array<Movie>>(env.apiUrl + `/movies?page=${pageable.page}&size=${pageable.size}`);
    }
}