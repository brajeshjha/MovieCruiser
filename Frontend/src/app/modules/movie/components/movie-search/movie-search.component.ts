import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.css']
})
export class MovieSearchComponent implements OnInit {

  movies: Array<Movie>;

  constructor(private movieService: MovieService) { }

  ngOnInit() {
  }

  onEnter(searchKey) {
    //console.log(searchKey)
    if(searchKey.length > 0){
    this.movieService.searchMovieFromApi(searchKey).subscribe((movies) => {
      this.movies = movies;
    });
  }
  else{
    this.movies = [];
  }
  }


}
