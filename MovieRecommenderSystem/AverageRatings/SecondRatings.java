package MovieRecommenderSystem.AverageRatings;
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import MovieRecommenderSystem.ReadAndStoreData.*;

import java.awt.*;
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("src/MovieRecommenderSystem/ReadAndStoreData/data/ratedmoviesfull.csv",
                "src/MovieRecommenderSystem/ReadAndStoreData/data/ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {
        double ratingsSum = 0;
        double numberOfRatings = 0;
        for (Rater eachRater : myRaters) {
            if (eachRater.hasRating(id)) {
                double currentRating = eachRater.getRating(id);
                ratingsSum += currentRating;
                numberOfRatings += 1;
            }
        }
        if (numberOfRatings >= minimalRaters) {
            return ratingsSum/numberOfRatings;
        } else {
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatingsOfMinimalRaterMovies = new ArrayList<>();
        HashMap<String, Integer> movieToNumberOfRatersMap = new HashMap<>();
        for (Rater eachRater : myRaters) {
            ArrayList<String> moviesRatedByRater = eachRater.getItemsRated();
            for (String eachMovieID : moviesRatedByRater) {
                if (!movieToNumberOfRatersMap.containsKey(eachMovieID)) {
                    movieToNumberOfRatersMap.put(eachMovieID, 1);
                } else {
                    movieToNumberOfRatersMap.put(eachMovieID, movieToNumberOfRatersMap.get(eachMovieID)+1);
                }
            }
        }

        for (String movieID : movieToNumberOfRatersMap.keySet()) {
            if (movieToNumberOfRatersMap.get(movieID) >= minimalRaters) {
                double averageRatingOfCurrentMovie = getAverageByID(movieID, minimalRaters);
                Rating averageRatingObjectOfCurrentMovie = new Rating(movieID, averageRatingOfCurrentMovie);
                averageRatingsOfMinimalRaterMovies.add(averageRatingObjectOfCurrentMovie);
            }
        }

        return averageRatingsOfMinimalRaterMovies;
    }

    public String getTitle(String id) {
        for (Movie eachMovie : myMovies) {
            if (eachMovie.getID().equals(id)) {
                return eachMovie.getTitle();
            }
        }
        return "TITLE OF MOVIE WITH ID "+id+" NOT FOUND";
    }

    public String getID(String title) {
        for (Movie eachMovie : myMovies) {
            if (eachMovie.getTitle().equals(title)) {
                return eachMovie.getID();
            }
        }
        return "NO SUCH TITLE";
    }

}
