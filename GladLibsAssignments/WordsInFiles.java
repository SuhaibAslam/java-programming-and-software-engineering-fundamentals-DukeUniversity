package GladLibsAssignments;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class WordsInFiles {
    HashMap<String,ArrayList<String>> wordToFileHash;
    public WordsInFiles() {
        wordToFileHash = new HashMap<String,ArrayList<String>>();
    }

    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if(wordToFileHash.containsKey(word)) {
                if (wordToFileHash.get(word).indexOf(f.getName()) == -1) {
                    ArrayList<String> currentWordFileArray = wordToFileHash.get(word);
                    currentWordFileArray.add(f.getName());
                    wordToFileHash.put(word, currentWordFileArray);
                }
            } else {
                ArrayList<String> newArray = new ArrayList<String>();
                newArray.add(f.getName());
                wordToFileHash.put(word, newArray);
            }
        }
    }

    public void buildWordFileMap() {
        wordToFileHash.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int maxNumberOfFiles = 0;
        for (String word: wordToFileHash.keySet()) {
            ArrayList<String> currentWordFileArray = wordToFileHash.get(word);
            if (currentWordFileArray.size()>maxNumberOfFiles) {
                maxNumberOfFiles = currentWordFileArray.size();
            }
        }
        return maxNumberOfFiles;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<String>();
        for (String word: wordToFileHash.keySet()) {
            ArrayList<String> currentWordFileArray = wordToFileHash.get(word);
            if (currentWordFileArray.size() == number) {
                wordList.add(word);
            }
        }
        return wordList;
    }

    public void printFilesIn(String word) {
        ArrayList<String> fileList = wordToFileHash.get(word);
        for (String fileName : fileList) {
            System.out.println(fileName);
        }
    }

    public void tester() {
        buildWordFileMap();
        int maxNumberOfFiles = maxNumber();
        System.out.println("Max number of Files any word is in is "+maxNumberOfFiles);
        ArrayList<String> wordsInMaxFiles = wordsInNumFiles(maxNumberOfFiles);
        ArrayList<String> wordsInSevenFiles = wordsInNumFiles(7);
        ArrayList<String> wordsInFourFiles = wordsInNumFiles(4);
        System.out.println("Number of words that are there in the maximum number of files is "+wordsInMaxFiles.size());
        System.out.println("Number of words that are there in 7 files is "+wordsInSevenFiles.size());
        System.out.println("Number of words that are there in 4 files is "+wordsInFourFiles.size());
        System.out.println("The word 'tree' appears in ");
        printFilesIn("tree");
        System.out.println("===WORDS IN MAX NUMBER OF FILES===");
        for (String word: wordsInMaxFiles) {
            System.out.println(word);
            printFilesIn(word);
        }
    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }

}
