package EarthquakeFilterProgram;

import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter eachFilter : filters) {
            if (!eachFilter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        StringBuilder stringOfAllFilters = new StringBuilder();
        for (Filter eachFilter : filters) {
            String filterName = eachFilter.getClass().getName().split("\\.")[1];
            stringOfAllFilters.append(filterName);
            stringOfAllFilters.append(", ");
        }
        return stringOfAllFilters.substring(0, stringOfAllFilters.length()-2);
    }
}
