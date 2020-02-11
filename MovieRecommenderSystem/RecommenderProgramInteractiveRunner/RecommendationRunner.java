package MovieRecommenderSystem.RecommenderProgramInteractiveRunner;

import MovieRecommenderSystem.EfficientProgramInterfaceAndFilters.*;
import MovieRecommenderSystem.EfficientProgramWithWeightedAverageRatings.FourthRatings;
import MovieRecommenderSystem.EfficientProgramWithWeightedAverageRatings.RaterDatabase;
import MovieRecommenderSystem.ReadAndStoreData.Rating;

import java.util.ArrayList;

public class RecommendationRunner implements Recommender {

    @Override
    public ArrayList<String> getItemsToRate() {
        AllFilters allFilters = new AllFilters();
        String genre = "Action, Comedy";
        int year = 2014;
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new YearAfterFilter(year));
        return MovieDatabase.filterBy(allFilters);
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings();

        //RaterDatabase.initialize("ratings.csv");

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, 15, 5);
        System.out.println();
        System.out.println("Found "+similarRatings.size()+" movies");
        System.out.println();
        System.out.println("<table style=\"border: 1px solid black;width:75%\">");
        System.out.println("<tr>");
        System.out.println("<th style=\"border: 1px solid black\">Movies Recommended for You</th>");
        System.out.println("</tr>");

        for (int i=0; i<10; i++) {
            if (i >= similarRatings.size()) {
                break;
            }
            Rating eachRating = similarRatings.get(i);
            System.out.println("<tr>");
            System.out.println("<td>"+MovieDatabase.getTitle(eachRating.getItem())+"</td>");
            System.out.println("</tr>");
        }

        System.out.println("</table>");
    }

//    public static void main(String[] args) {
//        RecommendationRunner rr = new RecommendationRunner();
//        ArrayList<String> moveiesToRate = rr.getItemsToRate();
//        for (String eachMovieID : moveiesToRate) {
//            System.out.println(MovieDatabase.getTitle(eachMovieID));
//        }
//        rr.printRecommendationsFor("71");
//    }
}
