package com.stackroute.moviecruiserserverapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	/**
	 * Id for a movie
	 */
	@Column(name = "movie_id")
	private int movieId;
	
	/**
	 * name of the movie
	 */
	@Column(name = "title")
	private String title;
	
	
	
	/**
	 * Comments for the movie
	 */
	@Column(name = "comments")
	private String comments;
	
	
	/**
	 * The path of the poster for the movie
	 */
	@Column(name = "poster_path")
	private String poster_path;
 
	
	/**
	 * The release date of the movie
	 */
	@Column(name = "release_date")
	private String release_date;
	
	/**
	 * Average vote of the movie
	 */
	@Column(name = "vote_average")
	private double vote_average;
	
	/**
	 *Vote Count of the movie
	 */
	@Column(name = "vote_count")
	private int vote_count;
	
	@Column(name = "user_id")
	private String userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Movie(int id, int movieId, String title, String comments, String poster_path, String release_date,
			double vote_average, int vote_count, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
		this.userId = userId;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}