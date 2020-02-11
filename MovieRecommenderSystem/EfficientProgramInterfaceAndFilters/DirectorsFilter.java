package MovieRecommenderSystem.EfficientProgramInterfaceAndFilters;

import java.util.ArrayList;

public class DirectorsFilter implements Filter {
    private String myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }

    @Override
    public boolean satisfies(String id) {
        String[] myDirectorList = myDirectors.split(",");
        for (String eachDirector : myDirectorList) {
            if (MovieDatabase.getDirector(id).contains(eachDirector)) {
                return true;
            }
        }
        return false;
    }

}
