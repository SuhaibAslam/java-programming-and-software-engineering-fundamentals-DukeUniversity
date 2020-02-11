package GladLibsAssignments;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
//    private ArrayList<String> adjectiveList;
//    private ArrayList<String> nounList;
//    private ArrayList<String> colorList;
//    private ArrayList<String> countryList;
//    private ArrayList<String> nameList;
//    private ArrayList<String> animalList;
//    private ArrayList<String> timeList;
//    private ArrayList<String> verbList;
//    private ArrayList<String> fruitList;

    private ArrayList<String> wordSeen;
    private ArrayList<String> labelUsed;
    private Random myRandom;
    private int wordsReplaced;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] labels = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for (String s: labels) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
        wordSeen = new ArrayList<String>();
        labelUsed = new ArrayList<String>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if ((!labelUsed.contains(label)) && !label.equals("number")) {
            labelUsed.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int seenIndex = wordSeen.indexOf(sub);
        while (seenIndex != -1) {
            sub = getSubstitute(w.substring(first+1,last));
            seenIndex = wordSeen.indexOf(sub);
        }
        wordSeen.add(sub);
        wordsReplaced += 1;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        System.out.println("\n");
        wordSeen.clear();
        labelUsed.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Number of words replaced: "+wordsReplaced);
        int totalWords = totalWordsInMap();
        System.out.println("Total number of words that were possible to pick from were: "+totalWords);
        int totalWordsInUsedLabels = totalWordsConsidered();
        System.out.println("The total number of words in the ArrayLists of the categories that were used: "+totalWordsInUsedLabels);
    }

    public int totalWordsInMap() {
        int totalWords = 0;
        for (String label:myMap.keySet()) {
            ArrayList<String> currentWordsArray = myMap.get(label);
            totalWords += currentWordsArray.size();
        }
        return totalWords;

        //ALTERNATIVE CODE
//        int sum = 0;
//        for (String category : myMap.keySet()) {
//            sum += myMap.get(category).size();
//        }
//        return sum;
    }

    public int totalWordsConsidered(){
        int totalWordsInUsedLabels = 0;
        for (String label : labelUsed) {
            ArrayList<String> currentWordsArray = myMap.get(label);
            totalWordsInUsedLabels += currentWordsArray.size();
        }
        return totalWordsInUsedLabels;
    }

    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
    }

}
