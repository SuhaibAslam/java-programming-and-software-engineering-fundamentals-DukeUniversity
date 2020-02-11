package MovieRecommenderSystem.ReadAndStoreData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FirstRatingsTester {
    public void testLoadMovies() {
        FirstRatings firstRatings = new FirstRatings();
        ArrayList<Movie> movieList = firstRatings.loadMovies("ratedmoviesfull.csv");

        // Number of movies in the list and the titles of those movies
        System.out.println("Number of Movies is "+movieList.size());
        System.out.println();
//        System.out.println("Movies are: ");
//        for (Movie eachMovie : movieList) {
//            System.out.println(eachMovie.getTitle());
//        }
//        System.out.println();

        // Number of Comedy Movies
        int numberOfComedy = 0;
        for (Movie eachMovie : movieList) {
            if (eachMovie.getGenres().contains("Comedy")) {
                numberOfComedy += 1;
            }
        }
        System.out.println("Number of comedy movies: "+numberOfComedy);
        System.out.println();

        // number of movies that are greater than 150 minutes in length
        int moviesLongerThan150 = 0;
        for (Movie eachMovie : movieList) {
            if (eachMovie.getMinutes()>150) {
                moviesLongerThan150 += 1;
            }
        }
        System.out.println("Number of movies that are greater than 150 minutes in length: "+moviesLongerThan150);
        System.out.println();

        // the maximum number of movies by any director, and who the directors are that directed that many movies.
        HashMap<String, Integer> directorAndNumberOfMoviesMap = new HashMap<>();
        for (Movie eachMovie : movieList) {
            String currentDirector = eachMovie.getDirector();
            if (!directorAndNumberOfMoviesMap.containsKey(currentDirector)) {
                directorAndNumberOfMoviesMap.put(currentDirector, 1);
            } else {
                directorAndNumberOfMoviesMap.put(currentDirector, directorAndNumberOfMoviesMap.get(currentDirector)+1);
            }
        }
        int maxMoviesByDirector = 0;
        for (String eachDirector : directorAndNumberOfMoviesMap.keySet()) {
            int numberOfMovies = directorAndNumberOfMoviesMap.get(eachDirector);
            if (numberOfMovies > maxMoviesByDirector) {
                maxMoviesByDirector = numberOfMovies;
            }
        }
        System.out.println("Maximum number of movies by one director: "+maxMoviesByDirector);
        System.out.println();
        System.out.println("Directors with max movies: ");
        for (String eachDirector : directorAndNumberOfMoviesMap.keySet()) {
            int numberOfMovies = directorAndNumberOfMoviesMap.get(eachDirector);
            if (numberOfMovies == maxMoviesByDirector) {
                System.out.println(eachDirector);
            }
        }

    }

    public void testLoadRaters() {
        FirstRatings firstRatings = new FirstRatings();
        ArrayList<Rater> raterList = firstRatings.loadRaters("ratings.csv");

        // Number of raters in the list
        System.out.println("Number of Raters is "+raterList.size());
        System.out.println();
//        for (Rater eachRater : raterList) {
//            System.out.println("Rater ID: "+eachRater.getID()+"     Number of Ratings: "+eachRater.numRatings());
//            System.out.println("============Ratings List============");
//            ArrayList<String> itemsList = eachRater.getItemsRated();
//            for (String eachItem : itemsList) {
//                double eachRating = eachRater.getRating(eachItem);
//                System.out.println("Rating ID: "+eachItem+"     Rating: "+eachRating);
//            }
//            System.out.println("====================================");
//            System.out.println();
//        }
//        System.out.println();

        // number of ratings for a particular rater
        String raterID = "193";
        for (Rater eachRater : raterList) {
            if (eachRater.getID().equals(raterID)) {
                int ratingOfID = eachRater.numRatings();
                System.out.println("Number of ratings of rater with ID of "+raterID+" are "+ratingOfID);
            }
        }
        System.out.println();

        // maximum number of ratings by any rater
        // how many raters have this maximum number of ratings
        // and who those raters are.
        int maxNumberOfRatings = 0;
        for (Rater eachRater : raterList) {
            if (eachRater.numRatings() > maxNumberOfRatings) {
                maxNumberOfRatings = eachRater.numRatings();
            }
        }
        System.out.println("Maximum number of ratings by any rater: "+maxNumberOfRatings);
        System.out.println();
        int numberOfMaxRatingRaters = 0;
        System.out.println("=====Raters that have the maximum number of Ratings=====");
        for (Rater eachRater : raterList) {
            if (eachRater.numRatings()==maxNumberOfRatings) {
                numberOfMaxRatingRaters += 1;
                System.out.println(eachRater.getID());
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Number of raters that have the maximum number of ratings: "+numberOfMaxRatingRaters);
        System.out.println();

        // number of ratings a particular movie has
        String movieID = "1798709";
        int numberOfRatings = 0;
        for (Rater eachRater : raterList) {
            if (eachRater.hasRating(movieID)) {
                numberOfRatings += 1;
            }
        }
        System.out.println("Number of ratings the movie with ID "+movieID+": "+numberOfRatings);
        System.out.println();

        // number of different movies that have been rated by all the raters
        HashSet<String> ratedMoviesList = new HashSet<>();
        for (Rater eachRater : raterList) {
            ArrayList<String> ratedMovies = eachRater.getItemsRated();
            for (String eachMovie : ratedMovies) {
                if (!ratedMoviesList.contains(eachMovie)) {
                    ratedMoviesList.add(eachMovie);
                }
            }
        }
        System.out.println("Number of unique movies rated by all the raters: "+ratedMoviesList.size());
    }

    public static void main(String[] args) {
        FirstRatingsTester firstRatingsTester = new FirstRatingsTester();
        //firstRatingsTester.testLoadMovies();
        firstRatingsTester.testLoadRaters();
    }
}
