package WordGramClassProgram;
import InterfaceAbstractProgram.EfficientMarkovModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> gramToStringListMap;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        gramToStringListMap = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram keyGram = new WordGram(myText, index, myOrder);
        sb.append(keyGram);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            //System.out.println("Key is "+keyGram);
            ArrayList<String> follows = getFollows(keyGram);
            //System.out.println("Words following key "+follows);
            if (follows.size()==1 && follows.get(0)=="") {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            //System.out.println(next);
            sb.append(" ");
            keyGram = keyGram.shiftAdd(next);
            //System.out.println(keyGram);
        }
        return sb.toString().trim();
    }

    private void buildMap() {
        for(int k=0; k <= myText.length-myOrder; k++){
//            System.out.println("key length "+keyLength);
//            System.out.println(k);
            WordGram keyGram = new WordGram(myText, k, myOrder);
            if ((k<(myText.length-myOrder))) {
                if (!gramToStringListMap.containsKey(keyGram)) {
                    ArrayList<String> followingWordsList = new ArrayList<>();
                    followingWordsList.add(myText[k+myOrder]);
                    gramToStringListMap.put(keyGram, followingWordsList);
                } else {
                    ArrayList<String> followingWordList = gramToStringListMap.get(keyGram);
                    followingWordList.add(myText[k+myOrder]);
                    gramToStringListMap.put(keyGram, followingWordList);
                }
            } else {
                if (!gramToStringListMap.containsKey(keyGram)) {
                    ArrayList<String> followingWordList = new ArrayList<>();
                    followingWordList.add("");
                    gramToStringListMap.put(keyGram, followingWordList);
                }
            }
        }
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

    private ArrayList<String> getFollows(WordGram kGram) { return gramToStringListMap.get(kGram); }

    public void printHashMapInfo() {
        System.out.println("===Hashmap printout===");
        System.out.println(gramToStringListMap);
        System.out.println("===Number of keys in the HashMap===");
        System.out.println(gramToStringListMap.size());
        System.out.println("===Size of the largest ArrayList of characters following a key===");
        int maxArraySize = 0;
        for (WordGram keyGram : gramToStringListMap.keySet()) {
            if (gramToStringListMap.get(keyGram).size()>maxArraySize) {
                maxArraySize = gramToStringListMap.get(keyGram).size();
            }
        }
        System.out.println(maxArraySize);
        System.out.println("===Keys that have the maximum size value===");
        for (WordGram keyGram : gramToStringListMap.keySet()) {
            if (gramToStringListMap.get(keyGram).size() == maxArraySize) {
                System.out.println(keyGram);
            }
        }
    }

    public void testIndexOf() {
        String[] wordArray = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        //int indexFound = indexOf(wordArray, "simple", 9);
        //System.out.println(indexFound);
    }
}
