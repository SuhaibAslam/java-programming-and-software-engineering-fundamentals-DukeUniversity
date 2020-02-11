package WordGramClassProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram keyGram = new WordGram(myText, index, myOrder);
        sb.append(keyGram);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            //System.out.println("Key is "+key);
            ArrayList<String> follows = getFollows(keyGram);
            //System.out.println("Words following key "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keyGram = keyGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start) {
        for (int i=start; i<words.length; i++) {
            int currentWordGramIdx = 0;
            int currentWordIdx = i;
            if (words[currentWordIdx].equals(target.wordAt(currentWordGramIdx))) {
                if (myOrder == 1) {
                    return currentWordIdx;
                }
                while (currentWordGramIdx < myOrder){
                    currentWordIdx += 1;
                    currentWordGramIdx += 1;
                    if (!words[currentWordIdx].equals(target.wordAt(currentWordGramIdx))) {
                        break;
                    }
                    if (words[currentWordIdx].equals(target.wordAt(currentWordGramIdx)) && (currentWordGramIdx ==
                            myOrder-1)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<>();
        int searchFromPosition = 0;
        for (int i=0; i<myText.length; i++) {
            int currIndex = indexOf(myText, kGram, searchFromPosition);
            if (currIndex == -1) {
                break;
            }
            if (currIndex + myOrder >= myText.length) {
                break;
            }
            String wordFollowingKey = myText[currIndex+myOrder];
            follows.add(wordFollowingKey);
            searchFromPosition = currIndex+myOrder;
        }
        return follows;
    }

    public void testIndexOf() {
        String[] wordArray = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        //int indexFound = indexOf(wordArray, "simple", 9);
        //System.out.println(indexFound);
    }

}
