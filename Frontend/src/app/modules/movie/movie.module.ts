import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MovieService } from './movie.service';
import { SharedModule } from '../shared/shared.module';
import { MovieRouterModule } from './movie-router.module';

import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { MovieSearchComponent } from './components/movie-search/movie-search.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './token-interceptor.service';



@NgModule({
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    TmdbContainerComponent,
    WatchlistComponent,
    MovieDialogComponent,
    MovieSearchComponent
  ],
  imports: [
    CommonModule,
    MovieRouterModule,
    SharedModule
  ],
  exports: [
    ThumbnailComponent,
    ContainerComponent,
    TmdbContainerComponent,
    WatchlistComponent,
    MovieSearchComponent,
    MovieRouterModule,
    MovieDialogComponent
  ],
  entryComponents:[
    MovieDialogComponent
  ],
  providers: [
    MovieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }

  ]

})
export class MovieModule { }
