import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';
import { MatSnackBar, MatDialog } from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';


@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  movie: Movie;

  @Input()
  useWatchlistApi: boolean;

  @Output()
  addMovie = new EventEmitter();

  @Output()
  deleteMovie = new EventEmitter();

  constructor(private movieService: MovieService, private matSnack: MatSnackBar,
    private dialog:MatDialog) {
  }

  ngOnInit() {
  }
  addToWatchlist() {
    this.addMovie.emit(this.movie);
  }

  deleteFromWatchlist() {
    this.deleteMovie.emit(this.movie);
  }
  
  updateFromWatchlist(actionType) {
    let dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '400px', data: { obj: this.movie, actionType: actionType }
    });
    dialogRef.afterClosed().subscribe(result => {
    });

  }

}
