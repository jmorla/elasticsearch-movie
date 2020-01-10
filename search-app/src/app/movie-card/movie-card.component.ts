import { Component } from '@angular/core';
import { Movie } from '../core/model/movie.model';

@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.component.html'
})
export class MovieCardComponent {

  movie: Movie = {
    Title: 'Around the World in 80 Days',
    US_Gross: 24004159,
    Worldwide_Gross: 72004159,
    US_DVD_Sales: null,
    Production_Budget: 110000000,
    Release_Date: 'Jun 16 2004',
    MPAA_Rating: 'PG',
    Running_Time_min: 120,
    Distributor: 'Walt Disney Pictures',
    Source: 'Remake',
    Major_Genre: 'Adventure',
    Creative_Type: 'Historical Fiction',
    Director: 'Frank Coraci',
    Rotten_Tomatoes_Rating: 30,
    IMDB_Rating: 5.6,
    IMDB_Votes: 21516
  };
}
