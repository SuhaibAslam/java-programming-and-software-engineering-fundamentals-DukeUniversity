package WordNGramStarterProgram;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
            //System.out.println("Key is "+key);
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Words following key "+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		return sb.toString().trim();
	}

	private int indexOf(String[] words, String target, int start) {
	    for (int i=start; i<words.length; i++) {
	        if (words[i].equals(target)) {
	            return i;
            }
        }
        return -1;
    }
	
	private ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<>();
		int searchFromPosition = 0;
		for (int i=0; i<myText.length; i++) {
			int currIndex = indexOf(myText, key, searchFromPosition);
			if (currIndex == -1) {
				break;
			}
			if (currIndex + 1 >= myText.length) {
				break;
			}
			String wordFollowingKey = myText[currIndex+1];
			follows.add(wordFollowingKey);
			searchFromPosition = currIndex+1;
		}
		return follows;
    }

    public void testIndexOf() {
	    String[] wordArray = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
	    int indexFound = indexOf(wordArray, "simple", 9);
	    System.out.println(indexFound);
    }

    public static void main(String[] args) {
        MarkovWordOne mw1 = new MarkovWordOne();
        mw1.testIndexOf();
    }

}
