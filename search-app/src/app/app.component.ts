import { Component, OnInit } from '@angular/core';
import { MovieService } from './core/service/movie.service';
import { Movie } from './core/model/movie.model';
import { trigger, transition, style, animate, state } from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [
    trigger('simpleFadeAnimation', [

      state('in', style({opacity: 1})),
      transition(':enter', [
        style({opacity: 0}),
        animate(600 )
      ]),
      transition(':leave',
        animate(600, style({opacity: 0})))
    ])
  ]
})
export class AppComponent implements OnInit {
  movies: Movie[] = [];
  loading: boolean;

  constructor (
    private movieService: MovieService
  ) {}

  ngOnInit() {
    this.loading = false;
    this.movieService.getAllMovies({page: 0, size: 10})
    .subscribe({
      next: (movies) => {
        setTimeout(() => {
          this.movies = movies
          this.loading = true
        }, 2000)
      }
    });
  }
}
