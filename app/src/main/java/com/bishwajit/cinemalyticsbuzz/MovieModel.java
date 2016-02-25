package com.bishwajit.cinemalyticsbuzz;

/**
 * Created by bishwajit on 12/17/2015.
 */
public class MovieModel {

    String id, originalTitle, description, genre, rating, releaseDate, runtime, posterPath, trailerLink;

    public MovieModel()
    {

    }
    public MovieModel(String id, String originalTitle, String description,
                      String genre, String rating, String releaseDate, String runtime,
                      String posterPath, String trailerLink)
    {
        this.id = id;
        this.originalTitle = originalTitle;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.posterPath = posterPath;
        this.trailerLink = trailerLink;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTrailerLink() {
        return trailerLink;
    }



    // setters

    public void setId(String id) {
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }


}
