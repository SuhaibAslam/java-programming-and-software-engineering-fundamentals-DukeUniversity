package MovieRecommenderSystem.ReadAndStoreData;
import MovieRecommenderSystem.EfficientProgramInterfaceAndFilters.EfficientRater;
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<>();
        FileResource fr = new FileResource("src/MovieRecommenderSystem/ReadAndStoreData/data/"+filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            //id,title,year,country,genre,director,minutes,poster
            String anID = record.get("id");
            String aTitle = record.get("title");
            String aYear = record.get("year");
            String theGenres = record.get("genre");
            String aDirector = record.get("director");
            String aCountry = record.get("country");
            String aPoster = record.get("poster");
            int theMinutes = Integer.parseInt(record.get("minutes"));

            Movie aMovie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);

            movieList.add(aMovie);
        }
        return movieList;
    }

    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource("src/MovieRecommenderSystem/ReadAndStoreData/data/"+filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String,Rater> raterIDToRatingMap = new HashMap<>();
        for (CSVRecord record : parser) {
            //rater_id,movie_id,rating,time
            String myID = record.get("rater_id");
            String item = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if (!raterIDToRatingMap.containsKey(myID)) {
                Rater currentRater = new EfficientRater(myID);
                currentRater.addRating(item, rating);
                raterIDToRatingMap.put(myID, currentRater);
            } else {
                Rater currentRater = raterIDToRatingMap.get(myID);
                currentRater.addRating(item, rating);
                raterIDToRatingMap.put(myID, currentRater);
            }
        }
        Collection<Rater> raterCollection = raterIDToRatingMap.values();
        ArrayList<Rater> raterList = new ArrayList<>(raterCollection);
        return raterList;
    }
}
