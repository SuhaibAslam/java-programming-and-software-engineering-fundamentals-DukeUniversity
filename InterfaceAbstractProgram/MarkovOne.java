package InterfaceAbstractProgram;

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {

    public MarkovOne() {
        myRandom = new Random();
        myOrder = 1;
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
}
