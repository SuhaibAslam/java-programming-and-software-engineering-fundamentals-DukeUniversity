package MovieRecommenderSystem.EfficientProgramInterfaceAndFilters;

import MovieRecommenderSystem.AverageRatings.*;
import MovieRecommenderSystem.ReadAndStoreData.*;

import java.util.*;

public class MovieRunnerWithFilters {
    private ThirdRatings thirdRatings;
    private int minimalRaters;

    public MovieRunnerWithFilters() {
        thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("Number of raters: "+thirdRatings.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies: "+MovieDatabase.size());
        System.out.println();
        minimalRaters = 3;
    }

    public void printAverageRatings() {
        // a list of movies and their average ratings, for all those movies that have at least
        // a specified number of ratings, sorted by averages.
        ArrayList<Rating> averageRatingsOfMinimalRaterMovies = thirdRatings.getAverageRatings(minimalRaters);
        System.out.println("Found "+averageRatingsOfMinimalRaterMovies.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsOfMinimalRaterMovies);
        for (Rating eachRating : averageRatingsOfMinimalRaterMovies) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getTitle(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByYear() {
        int year = 2000;
        Filter yearAfterFilter = new YearAfterFilter(year);
        ArrayList<Rating> averageRatingsWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, yearAfterFilter);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getTitle(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByGenre() {
        String genre = "Comedy";
        Filter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> averageRatingsWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, genreFilter);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getTitle(eachRating.getItem()));
            System.out.println(MovieDatabase.getGenres(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes() {
        int minimum = 105;
        int maximum = 135;
        Filter minutesFilter = new MinutesFilter(minimum, maximum);
        ArrayList<Rating> averageRatingsWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, minutesFilter);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getTitle(eachRating.getItem())+"  Minutes: "
                    +MovieDatabase.getMinutes(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors() {
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter directorsFilter = new DirectorsFilter(directors);
        ArrayList<Rating> averageRatingsWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, directorsFilter);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getTitle(eachRating.getItem()));
            System.out.println(MovieDatabase.getDirector(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        AllFilters allFilters = new AllFilters();
        String genre = "Action, Comedy";
        int year = 2013;
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new YearAfterFilter(year));

        ArrayList<Rating> averageRatingsWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getYear(eachRating.getItem())
                    +"  "+MovieDatabase.getTitle(eachRating.getItem()));
            System.out.println(MovieDatabase.getGenres(eachRating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        AllFilters allFilters = new AllFilters();
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        int minimum = 90;
        int maximum = 180;
        allFilters.addFilter(new DirectorsFilter(directors));
        allFilters.addFilter(new MinutesFilter(minimum, maximum));

        ArrayList<Rating> averageRatingsWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("Found "+averageRatingsWithFilter.size()+" movies");
        System.out.println();
        Collections.sort(averageRatingsWithFilter);
        for (Rating eachRating : averageRatingsWithFilter) {
            System.out.println(eachRating.getValue()+"  "+MovieDatabase.getTitle(eachRating.getItem())+"  Minutes: "
                    +MovieDatabase.getMinutes(eachRating.getItem()));
            System.out.println(MovieDatabase.getDirector(eachRating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
//        movieRunnerWithFilters.printAverageRatings();
//        movieRunnerWithFilters.printAverageRatingsByYear();
//        movieRunnerWithFilters.printAverageRatingsByGenre();
//        movieRunnerWithFilters.printAverageRatingsByMinutes();
//        movieRunnerWithFilters.printAverageRatingsByDirectors();
        movieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre();
//        movieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes();
    }
}
