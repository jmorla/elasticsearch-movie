import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment as env } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class MovieTypeService {

    constructor(private http: HttpClient) {}

    getAllMovieTypes(): Observable<string[]> {
        return this.http.get<string[]>(env.apiUrl + '/movie-types')
    }
}