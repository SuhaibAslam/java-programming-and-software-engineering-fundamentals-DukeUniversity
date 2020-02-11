package EarthquakeFilterProgram;

public class PhraseFilter implements Filter {
    String whereToSearchInTitle;
    String phraseToSearch;

    public PhraseFilter(String where, String phrase) {
        whereToSearchInTitle = where;
        phraseToSearch = phrase;
    }

    public boolean satisfies(QuakeEntry qe) {
        int indexToCheck = 0;
        if (whereToSearchInTitle.equals("start") || whereToSearchInTitle.equals("end")) {
            if (whereToSearchInTitle.equals("start")) {
                String currentQuakeTitle = qe.getInfo();
                String[] wordsInTitle = currentQuakeTitle.split("\\W+");
                if (wordsInTitle[indexToCheck].contains(phraseToSearch)) {
                    return true;
                }
            } else {
                String currentQuakeTitle = qe.getInfo();
                String[] wordsInTitle = currentQuakeTitle.split("\\W+");
                indexToCheck = wordsInTitle.length-1;
                if (wordsInTitle[indexToCheck].contains(phraseToSearch)) {
                    return true;
                }
            }
        } else {
            String currentQuakeTitle = qe.getInfo();
            String[] wordsInTitle = currentQuakeTitle.split("\\W+");
            for (String eachWord : wordsInTitle) {
                if (eachWord.contains(phraseToSearch)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getName() {
        return (this.getClass().getName().split("\\.")[1]);
    }
}
