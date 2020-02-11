package InterfaceAbstractProgram;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int keyLength;
    private HashMap<String, ArrayList<String>> followingCharsHashMap;

    public EfficientMarkovModel(int lengthOfPredictionKey) {
        myRandom = new Random();
        keyLength = lengthOfPredictionKey;
        myOrder = keyLength;
        followingCharsHashMap = new HashMap<>();
    }

    private void buildMap() {
        for(int k=0; k <= myText.length()-keyLength; k++){
//            System.out.println("key length "+keyLength);
//            System.out.println(k);
            String key = myText.substring(k, k+keyLength);
            if ((k<(myText.length()-keyLength))) {
                if (!followingCharsHashMap.containsKey(key)) {
                    ArrayList<String> followingCharList = new ArrayList<>();
                    followingCharList.add(myText.substring(k+keyLength,(k+keyLength+1)));
                    followingCharsHashMap.put(key, followingCharList);
                } else {
                    ArrayList<String> followingCharList = followingCharsHashMap.get(key);
                    followingCharList.add(myText.substring(k+keyLength,(k+keyLength+1)));
                    followingCharsHashMap.put(key, followingCharList);
                }
            } else {
                ArrayList<String> followingCharList = new ArrayList<>();
                followingCharList.add("");
                followingCharsHashMap.put(key, followingCharList);
            }
        }
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
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
            if (followingCharList.get(0).isEmpty()) {
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
        return followingCharsHashMap.get(key);
    }

    public void printHashMapInfo() {
        System.out.println("===Hashmap printout===");
        //System.out.println(followingCharsHashMap);
        System.out.println("===Number of keys in the HashMap===");
        System.out.println(followingCharsHashMap.size());
        System.out.println("===Size of the largest ArrayList of characters following a key===");
        int maxArraySize = 0;
        for (String key : followingCharsHashMap.keySet()) {
            if (followingCharsHashMap.get(key).size()>maxArraySize) {
                maxArraySize = followingCharsHashMap.get(key).size();
            }
        }
        System.out.println(maxArraySize);
        System.out.println("===Keys that have the maximum size value===");
        for (String key : followingCharsHashMap.keySet()) {
            if (followingCharsHashMap.get(key).size() == maxArraySize) {
               System.out.println(key);
            }
        }
    }

    public String toString() {
        return "EfficientMarkovModel of order "+myOrder;
    }
}
