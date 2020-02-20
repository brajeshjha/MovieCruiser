import { Component, OnInit, Input } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';


@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  movies:Array<Movie>;
 
  @Input()
  useWatchlistApi: boolean;
  
  constructor(private movieService:MovieService,private matSnack:MatSnackBar) { 
    
  }
  
  ngOnInit() {

  }

  addMovieToWatchlist(movie){
    let message = `${movie.title} added from your watchlist`;

    this.movieService.addMovieToWatchList(movie)
    .subscribe((movie) => {
      this.matSnack.open(message, '',{ duration: 1000 });
    });
  }
 
  deleteMovieFromWatchList(movie){
    let id=movie.id;
    let message = `${movie.title} deleted from your watchlist`;
    this.movieService.deleteMovieFromWatchlist(movie)
    .subscribe((movie) => {

      for(var i=0;i<this.movies.length;i++){
        if(this.movies[i].id===id){
          this.movies.splice(i,1);
        }
      }
      this.matSnack.open(message, '',{ duration: 1000 });
    });
  }


}
