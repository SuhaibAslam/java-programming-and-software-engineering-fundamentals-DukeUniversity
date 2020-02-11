package RandomTextProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int keyLength;

    public MarkovModel(int lengthOfPredictionKey) {
        myRandom = new Random();
        keyLength = lengthOfPredictionKey;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-keyLength);
        String key = myText.substring(index, index+keyLength);
        sb.append(key);

        for(int k=0; k < numChars-keyLength; k++){
            ArrayList<String> followingCharList = getFollows(key);
            if (followingCharList.size() == 0) {
                break;
            }
            index = myRandom.nextInt(followingCharList.size());
            String followingChar = followingCharList.get(index);
            sb.append(followingChar);
            key = key.substring(1,key.length()) + followingChar;
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {
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
}
