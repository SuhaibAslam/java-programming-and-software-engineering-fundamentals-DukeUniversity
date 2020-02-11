package MovieRecommenderSystem.EfficientProgramInterfaceAndFilters;

import MovieRecommenderSystem.ReadAndStoreData.*;
import sun.awt.Symbol;

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("src/MovieRecommenderSystem/ReadAndStoreData/data/ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatingsOfMinimalRaterMovies = new ArrayList<>();
        HashMap<String, Integer> movieToNumberOfRatersMap = new HashMap<>();
        ArrayList<String> filteredMovieIDs = new ArrayList<>();
        filteredMovieIDs = MovieDatabase.filterBy(filterCriteria);
        //System.out.println(filteredMovieIDs);
        for (String eachfilteredMovieID : filteredMovieIDs) {
            for (Rater eachRater : myRaters) {
                if (eachRater.hasRating(eachfilteredMovieID)) {
                    if (!movieToNumberOfRatersMap.containsKey(eachfilteredMovieID)) {
                        movieToNumberOfRatersMap.put(eachfilteredMovieID, 1);
                    } else {
                        movieToNumberOfRatersMap.put(eachfilteredMovieID,
                                movieToNumberOfRatersMap.get(eachfilteredMovieID)+1);
                    }
                }
            }
            if (!movieToNumberOfRatersMap.containsKey(eachfilteredMovieID)) {
                movieToNumberOfRatersMap.put(eachfilteredMovieID, 0);
            }
        }

        for (String eachfilteredMovieID : filteredMovieIDs) {
            if (movieToNumberOfRatersMap.get(eachfilteredMovieID) >= minimalRaters) {
                double averageRatingOfCurrentMovie = getAverageByID(eachfilteredMovieID, minimalRaters);
                Rating averageRatingObjectOfCurrentMovie = new Rating(eachfilteredMovieID, averageRatingOfCurrentMovie);
                averageRatingsOfMinimalRaterMovies.add(averageRatingObjectOfCurrentMovie);
            }
        }

        return averageRatingsOfMinimalRaterMovies;
    }
}
