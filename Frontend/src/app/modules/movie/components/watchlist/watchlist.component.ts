import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies:Array<Movie>;

  useWatchlistApi: boolean;

  constructor(private movieService: MovieService) { 
    this.movies= [];
    this.useWatchlistApi = true;
  }

  ngOnInit() {
   //console.log(this.useWatchlistApi);
    this.movieService.getWatchlistedMovies()
    .subscribe(
      (movies)  => { this.movies.push(...(movies as Array<Movie>))
    });
  }

}
