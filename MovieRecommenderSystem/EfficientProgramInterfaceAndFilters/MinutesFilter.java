package MovieRecommenderSystem.EfficientProgramInterfaceAndFilters;

public class MinutesFilter implements Filter {
    private int min;
    private int max;

    public MinutesFilter(int minimumValue, int maximumValue) {
        min = minimumValue;
        max = maximumValue;
    }

    @Override
    public boolean satisfies(String id) {
        return ((MovieDatabase.getMinutes(id) >= min) && (MovieDatabase.getMinutes(id)) <= max);
    }
}
