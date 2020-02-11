package ArrayListAssignments;

import edu.duke.*;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myFreqs.clear();
        myWords.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int Index = myWords.indexOf(word);
            if (Index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(Index);
                myFreqs.set(Index, value+1);
            }
        }
    }

    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i=0; i<myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester() {
        findUnique();
        System.out.println("Number of Unique Words: "+myWords.size());
        for (int i=0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The most frequent word is "+myWords.get(maxIndex)+" it occurs "+myFreqs.get(maxIndex)+" times");
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }

}
