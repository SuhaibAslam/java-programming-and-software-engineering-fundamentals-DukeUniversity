package InterfaceAbstractProgram;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int myOrder;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    abstract public void setRandom(int seed);
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int searchFromPosition = 0;
        for (int i=0; i<myText.length(); i++) {
            int currIndex = myText.indexOf(key, searchFromPosition);
            if (currIndex == -1) {
                break;
            }
            if (currIndex + key.length() >= myText.length()) {
                break;
            }
            String charFollowingKey = myText.substring(currIndex+key.length(), currIndex+key.length()+1);
            follows.add(charFollowingKey);
            searchFromPosition = currIndex+key.length();
        }
        return follows;
    }

    @Override
    public String toString() {
        return "MarkovModel of order "+myOrder;
    }
}
