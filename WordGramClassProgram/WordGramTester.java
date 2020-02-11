package WordGramClassProgram;
import java.util.*;

public class WordGramTester {
	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
//			int hashCode = wg.hashCode();
//			System.out.println(hashCode);
		}
	}
	
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}

	public void testShiftAdd() {
        String[] word = {"this", "is", "a", "test"};
        WordGram wg = new WordGram(word, 0, word.length);
        WordGram wgWithShiftAdd = wg.shiftAdd("yes");
        System.out.println(wgWithShiftAdd);
    }

//    public void testIndexOf() {
//        String[] wordArray = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
//        int order = 1;
//        MarkovWord mw = new MarkovWord(order);
//        mw.setRandom(42);
//        mw.setTraining(wordArray.toString());
//        WordGram wg = new WordGram(wordArray, 10, order);
//        System.out.println("WordGram is: "+wg);
//        int indexFound = mw.indexOf(wordArray, wg, 8);
//        System.out.println(indexFound);
//    }

    public static void main(String[] args) {
        WordGramTester wgt = new WordGramTester();
        wgt.testWordGram();
//        wgt.testWordGramEquals();
//        wgt.testShiftAdd();
//        wgt.testIndexOf();
    }
}
