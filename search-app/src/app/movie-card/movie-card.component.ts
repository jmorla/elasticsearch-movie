import { Component, Input } from '@angular/core';
import { Movie } from '../core/model/movie.model';

@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.component.html'
})
export class MovieCardComponent {

  @Input() movie: Movie;

  getStars() {
    const stars = Array((this.movie.IMDB_Rating / 2) | 0).fill(1);
    while(stars.length < 5) {
      stars.push(0);
    }
    return stars;
  }
}
