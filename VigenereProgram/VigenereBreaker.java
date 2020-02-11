package VigenereProgram;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedString = new StringBuilder();
        for(int i = 0 ; i < message.length() ; i++) {
            char c = message.charAt(i);
            if ((i%totalSlices) == whichSlice) {
                slicedString.append(c);
            }
        }
        return slicedString.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i<klength; i++){
            String currentSlice = sliceString(encrypted, i, klength);
            int currentSliceKey = cc.getKey(currentSlice);
            key[i] = currentSliceKey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionaryHash = new HashSet<String>();
        for (String dictionaryWord : fr.lines()) {
            dictionaryWord = dictionaryWord.toLowerCase();
            dictionaryHash.add(dictionaryWord);
        }
        return dictionaryHash;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] wordsInMessage = message.split("\\W+");
        int realWordsCount = 0;
        for (String eachWord : wordsInMessage) {
            if (dictionary.contains(eachWord.toLowerCase())) {
                realWordsCount += 1;
            }
        }
        return realWordsCount;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        char mostCommonCharacter = mostCommonCharIn(dictionary);
        int maxRealWordsCount = 0;
        int keyLengthWithMaxCount = 0;
        StringBuilder stringWithMostRealWords = new StringBuilder();
        for (int klength = 1; klength < 110; klength++) {
            int[] keyVigenereBreaker = tryKeyLength(encrypted, klength, mostCommonCharacter);
            VigenereCipher vc = new VigenereCipher(keyVigenereBreaker);
            String decrypted = vc.decrypt(encrypted);
            int realWordsCount = countWords(decrypted, dictionary);
            if (realWordsCount > maxRealWordsCount) {
                maxRealWordsCount = realWordsCount;
                keyLengthWithMaxCount = klength;
            }
        }
        int[] keyVigenereBreaker = tryKeyLength(encrypted, keyLengthWithMaxCount, mostCommonCharacter);
        VigenereCipher vc = new VigenereCipher(keyVigenereBreaker);
        String decrypted = vc.decrypt(encrypted);
        return decrypted;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character,Integer> charCountsMap = new HashMap<Character,Integer>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (char eachAlphabet : alphabet.toCharArray()) {
            charCountsMap.put(eachAlphabet, 0);
        }
        for (String eachWord : dictionary) {
            char[] charsInWord = eachWord.toCharArray();
            for (char eachCar : charsInWord) {
                eachCar = Character.toLowerCase(eachCar);
                if (charCountsMap.containsKey(eachCar)) {
                    charCountsMap.put(eachCar, charCountsMap.get(eachCar)+1);
                }
            }
        }
        int maxCount = 0;
        char mostCommonChar = 'a';
        for (char eachChar : charCountsMap.keySet()) {
            int currCounts = charCountsMap.get(eachChar);
            if (currCounts > maxCount) {
                maxCount = currCounts;
                mostCommonChar = eachChar;
            }
        }
        return mostCommonChar;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxRealWordsCount = 0;
        String languageWithMaxWordsCount = "Language";
        for (String eachLanguage : languages.keySet()) {
            HashSet<String> currentLanguageDictionary = languages.get(eachLanguage);
            String currentDecryption = breakForLanguage(encrypted, currentLanguageDictionary);
            int wordsInCurrentDecryption = countWords(currentDecryption, currentLanguageDictionary);
            if (wordsInCurrentDecryption > maxRealWordsCount) {
                maxRealWordsCount = wordsInCurrentDecryption;
                languageWithMaxWordsCount = eachLanguage;
            }
        }
        String decrypted = breakForLanguage(encrypted, languages.get(languageWithMaxWordsCount));
        System.out.println("====DECRYPTED====");
        System.out.println(decrypted);
        System.out.println("Language identified for the message is "+languageWithMaxWordsCount);
    }

    public void breakVigenere () {
        String input = new FileResource("VigenereProgram/messages/secretmessage1.txt").asString();
        HashMap<String, HashSet<String>> allLanguagesDictionaries = new HashMap<>();
        String[] languageNames = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (String eachLanguage : languageNames) {
            FileResource fr = new FileResource("VigenereProgram/dictionaries/"+eachLanguage);
            HashSet<String> wordSetForLanguage = readDictionary(fr);
            allLanguagesDictionaries.put(eachLanguage, wordSetForLanguage);
        }
        breakForAllLangs(input, allLanguagesDictionaries);
        //// INTERMEDIATE TEST CODE ////
        //FileResource wordDictionary = new FileResource("VigenereProgram/dictionaries/English");
        //HashSet<String> dictionaryHash = readDictionary(wordDictionary);
        //String decrypted = breakForLanguage(input, dictionaryHash);
        //System.out.println(decrypted);
        //char mostCommonChar = mostCommonCharIn(dictionaryHash);
        //System.out.println("Most common character in the given dictionary is "+mostCommonChar);
    }

}
