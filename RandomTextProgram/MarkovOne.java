package RandomTextProgram;

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int k=0; k < numChars-1; k++){
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
