package MovieRecommenderSystem.AverageRatings;

import MovieRecommenderSystem.ReadAndStoreData.Rating;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Number of movies: "+secondRatings.getMovieSize());
        System.out.println("Number of raters: "+secondRatings.getRaterSize());

        // a list of movies and their average ratings, for all those movies that have at least
        // a specified number of ratings, sorted by averages.
        int minimalRaters = 50;
        ArrayList<Rating> averageRatingsOfMinimalRaterMovies = secondRatings.getAverageRatings(minimalRaters);
        Collections.sort(averageRatingsOfMinimalRaterMovies);
        for (Rating eachRating : averageRatingsOfMinimalRaterMovies) {
            System.out.println(eachRating.getValue()+"  "+secondRatings.getTitle(eachRating.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String movieTitle = "Vacation";
        String movieID = secondRatings.getID(movieTitle);
        double averageRating = secondRatings.getAverageByID(movieID, 1);

        System.out.println("Average rating for the movie "+movieTitle+" is "+averageRating);
    }

    public static void main(String[] args) {
         MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
         movieRunnerAverage.printAverageRatings();
         movieRunnerAverage.getAverageRatingOneMovie();
    }
}
