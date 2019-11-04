package com.example.movieapp;

public class Movie {
    private String title;
    private String imageUrl;
    private String plot;
    private double rating;
    private String releaseDate;

    public Movie(String title, String imageUrl, String plot, double rating, String releaseDate) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.plot = plot;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", plot='" + plot + '\'' +
                ", rating=" + rating +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
