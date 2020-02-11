package MovieRecommenderSystem.EfficientProgramWithWeightedAverageRatings;

import MovieRecommenderSystem.EfficientProgramInterfaceAndFilters.*;
import MovieRecommenderSystem.ReadAndStoreData.*;
import sun.awt.Symbol;

import java.util.*;

public class MovieRunnerSimilarRatings {
    private FourthRatings fourthRatings;
    private int minimalRaters;

    public MovieRunnerSimilarRatings() {
        fourthRatings = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters: "+RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: "+MovieDatabase.size());
        System.out.println();
        minimalRaters = 3;
    }

    public void printAverageRatings() {
        // a list of movies and their average ratings, for all those movies that have at least
        // a specified number of ratings, sorted by averages.
        ArrayList<Rating> averageRatingsOfMinimalRaterMovies = fourthRatings.getAverageRatings(minimalRaters);
        System.out.println("Found "+averageRatingsOfMinimalRaterMovies.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsOfMinimalRaterMovies);
        for (Rating eachRating : averageRatingsOfMinimalRaterMovies) {
            System.out.println(eachRating.getValue()+"  "+ MovieDatabase.getTitle(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        AllFilters allFilters = new AllFilters();
        String genre = "Drama";
        int year = 1990;
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new YearAfterFilter(year));

        ArrayList<Rating> averageRatingsWithFilter = fourthRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getYear(eachRating.getItem())
                    +"  "+MovieDatabase.getTitle(eachRating.getItem()));
            System.out.println(MovieDatabase.getGenres(eachRating.getItem()));
        }
    }

    public void printSimilarRatings() {
        String raterID = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        System.out.println("Found "+similarRatings.size()+" movies");
        System.out.println();
        for (Rating eachRating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(eachRating.getItem())+"   "+eachRating.getValue());
        }
    }

    public void printSimilarRatingsByGenre() {
        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Mystery";
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                new GenreFilter(genre));
        System.out.println("Found "+similarRatings.size()+" movies");
        System.out.println();
        for (Rating eachRating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(eachRating.getItem())+"   "+eachRating.getValue());
            System.out.println(MovieDatabase.getGenres(eachRating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack," +
                "David Cronenberg,Oliver Stone,Mike Leigh";
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                new DirectorsFilter(directors));
        System.out.println("Found "+similarRatings.size()+" movies");
        System.out.println();
        for (Rating eachRating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(eachRating.getItem())+"   "+eachRating.getValue());
            System.out.println(MovieDatabase.getDirector(eachRating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String genre = "Drama";
        int minimum = 80;
        int maximum = 160;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new MinutesFilter(minimum, maximum));
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                allFilters);
        System.out.println("Found "+similarRatings.size()+" movies");
        System.out.println();
        for (Rating eachRating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(eachRating.getItem())+"   "+eachRating.getValue()+"   Minutes: "
            +MovieDatabase.getMinutes(eachRating.getItem()));
            System.out.println(MovieDatabase.getGenres(eachRating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinute() {
        String raterID = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int year = 1975;
        int minimum = 70;
        int maximum = 200;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new MinutesFilter(minimum, maximum));
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters,
                allFilters);
        System.out.println("Found "+similarRatings.size()+" movies");
        System.out.println();
        for (Rating eachRating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(eachRating.getItem())+"   "+eachRating.getValue()+"   Minutes: "
                    +MovieDatabase.getMinutes(eachRating.getItem())+"   "+MovieDatabase.getYear(eachRating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
        //movieRunnerSimilarRatings.printAverageRatings();
        //movieRunnerSimilarRatings.printAverageRatingsByYearAfterAndGenre();
        //movieRunnerSimilarRatings.printSimilarRatings();
        //movieRunnerSimilarRatings.printSimilarRatingsByGenre();
        //movieRunnerSimilarRatings.printSimilarRatingsByDirector();
        //movieRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes();
        movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinute();
    }
}
