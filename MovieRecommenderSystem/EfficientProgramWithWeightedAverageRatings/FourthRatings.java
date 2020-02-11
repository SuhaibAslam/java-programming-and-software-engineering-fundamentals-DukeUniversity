package MovieRecommenderSystem.EfficientProgramWithWeightedAverageRatings;

import MovieRecommenderSystem.EfficientProgramInterfaceAndFilters.*;
import MovieRecommenderSystem.ReadAndStoreData.*;

import java.util.*;

public class FourthRatings {
    public double getAverageByID(String id, int minimalRaters) {
        double ratingsSum = 0;
        double numberOfRatings = 0;
        for (Rater eachRater : RaterDatabase.getRaters()) {
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
        for (Rater eachRater : RaterDatabase.getRaters()) {
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
            for (Rater eachRater : RaterDatabase.getRaters()) {
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

    private double dotProduct(Rater me, Rater r) {
        HashSet<String> smallerRatedSet;
        HashSet<String> longerRatedSet;
        HashSet<String> commonRatedSet = new HashSet<>();
        if (me.numRatings() < r.numRatings()) {
            smallerRatedSet = new HashSet<>(me.getItemsRated());
            longerRatedSet = new HashSet<>(r.getItemsRated());
        } else {
            longerRatedSet = new HashSet<>(me.getItemsRated());
            smallerRatedSet = new HashSet<>(r.getItemsRated());
        }

        for (String eachElement1 : smallerRatedSet) {
            for (String eachElement2 : longerRatedSet) {
                if (eachElement1.equals(eachElement2)) {
                    commonRatedSet.add(eachElement1);
                }
            }
        }

        double dotProductResult = 0;
        for (String eachMovieID : commonRatedSet) {
            double rating1 = me.getRating(eachMovieID)-5;
            double rating2 = r.getRating(eachMovieID)-5;
            dotProductResult += rating1*rating2;
        }

        return dotProductResult;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> listOfSimilartyRatings = new ArrayList<>();
        ArrayList<Rater> listOfRaters = RaterDatabase.getRaters();
        Rater myRater = RaterDatabase.getRater(id);
        for (Rater eachRater : listOfRaters) {
            if (!eachRater.getID().equals(id)) {
                double similarity = dotProduct(myRater, eachRater);
                if (similarity > 0) {
                    listOfSimilartyRatings.add(new Rating(eachRater.getID(), similarity));
                }
            }
        }
        Collections.sort(listOfSimilartyRatings, Collections.reverseOrder());
        //System.out.println(listOfSimilartyRatings);
        return listOfSimilartyRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter filterCriteria) {
        ArrayList<Rating> similaritiesList = getSimilarities(id);
        ArrayList<Rating> returnSimilarRatingsList =  new ArrayList<>();

        int lastIndex = 0;
        if (similaritiesList.size()<numSimilarRaters) {
            lastIndex = similaritiesList.size();
        } else {
            lastIndex = numSimilarRaters;
        }
        ArrayList<Rating> topNumSimilarRaters = new ArrayList<>(similaritiesList.subList(0, lastIndex));

        // hashmap only contains those movieIDs that have been rated by at least one top rater.
        HashMap<String, Integer> movieIDToNumOfTopRaters = new HashMap<>();
        for (String eachMovieID : MovieDatabase.filterBy(filterCriteria)) {
            for (Rating eachRaterRating : topNumSimilarRaters) {
                Rater rater = RaterDatabase.getRater(eachRaterRating.getItem());
                if (rater.hasRating(eachMovieID)) {
                    if (!movieIDToNumOfTopRaters.containsKey(eachMovieID)) {
                        movieIDToNumOfTopRaters.put(eachMovieID, 1);
                    } else {
                        movieIDToNumOfTopRaters.put(eachMovieID, movieIDToNumOfTopRaters.get(eachMovieID)+1);
                    }
                }
            }
        }

        for (String eachMovieIDRatedByTopRater : movieIDToNumOfTopRaters.keySet()) {
            if (movieIDToNumOfTopRaters.get(eachMovieIDRatedByTopRater) >= minimalRaters) {
                int numberOfRaters = movieIDToNumOfTopRaters.get(eachMovieIDRatedByTopRater);
                double weightedSum = 0;
                for (Rating eachRaterRating : topNumSimilarRaters) {
                    Rater topRater = RaterDatabase.getRater(eachRaterRating.getItem());
                    if (topRater.hasRating(eachMovieIDRatedByTopRater)) {
                        weightedSum += topRater.getRating(eachMovieIDRatedByTopRater)*eachRaterRating.getValue();
                    }
                }
                returnSimilarRatingsList.add(new Rating(eachMovieIDRatedByTopRater, (weightedSum/numberOfRaters)));
            }
        }

        Collections.sort(returnSimilarRatingsList, Collections.reverseOrder());
        return returnSimilarRatingsList;
    }
}
