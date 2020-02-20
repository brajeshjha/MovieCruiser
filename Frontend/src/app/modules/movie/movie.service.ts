import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Movie } from './movie';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  tmdbEndpoint: string;
  imagePrefix: string;
  apiKey: string;
  watchListEndpoint: string;
  springEndpoint: string;

  constructor(private http:HttpClient) {
    this.tmdbEndpoint="https://api.themoviedb.org/3";
    this.apiKey="api_key=edfc803e314bafffb311cacaf8eb5830";
    this.imagePrefix="https://image.tmdb.org/t/p/w500";
    this.watchListEndpoint="http://localhost:3000/watchlist";
    this.springEndpoint="http://localhost:8080/api/movie/";
   
  }
   
   getMovies(type:string,page: number=1){
    const endPoint=this.tmdbEndpoint+'/movie/'+type+ '?' +this.apiKey+'&page='+page;
    return this.http.get(endPoint).pipe(
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
   }

   transformPosterPath(movies): Observable<Array<Movie>>{
      return movies.map(movie => {
        movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
        return movie;
      });
   }
   pickMovieResults(response):Array<Movie>{
      return response['results'];
   }

   getWatchlistedMovies(): Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.springEndpoint)
  }
  
  addMovieToWatchList(movie){
      return this.http.post(this.springEndpoint, movie);
   }

  deleteMovieFromWatchlist(movie){
    const url=this.springEndpoint+movie.id;
    return this.http.delete(url,{responseType:'text'});
  }   

  updateMovieFromWatchlist(movie){
    const url=this.springEndpoint;
    return this.http.put<Movie>(url,movie);
  }  

  searchMovieFromApi(searchKey: string,page: number = 1): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const searchEndpoint=this.tmdbEndpoint+'/search/movie?'+this.apiKey+'&page='+page+'&include_adult=false&query='+searchKey;
      return this.http.get(searchEndpoint).pipe(  
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );
    }
  }
  

}
