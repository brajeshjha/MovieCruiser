import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';


@Component({
  selector: 'app-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(private snackBar: MatSnackBar, private dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieService) {
    
      this.movie = data.obj;
      this.comments = data.obj.comments;
      this.actionType = data.actionType;

  }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  updateMovieComments() {
    this.movie.comments = this.comments;
    this.dialogRef.close();
    this.movieService.updateMovieFromWatchlist(this.movie).subscribe(() => {
      this.snackBar.open("Movie updated to Watchlist successfully", "", {
        duration: 2000
      });
    })
  }


}
