package WordGramClassProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

import javax.sound.midi.SysexMessage;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource("src/WordNGramStarterProgram/data/confucius.txt");
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(3);
        int size = 200;
        int randomSeed = 643;
        runModel(mw, st, size, randomSeed);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    }

    public void testHashMap() {
        String input = "this is a test yes this is really a test yes a test this is wow";
//        FileResource fr = new FileResource("InterfaceAbstractProgram/data/confucius.txt");
//        String st = fr.asString();
//        st = st.replace('\n', ' ');
        int size = 50;
        int seed = 42;
        int keySize = 2; //same as order

        EfficientMarkovWord em = new EfficientMarkovWord(keySize);
        runModel(em, input, size, seed);
    }


    public void compareMethods() {
        FileResource fr = new FileResource("InterfaceAbstractProgram/data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 42;
        int keySize = 2; //same as order
        MarkovWord mw = new MarkovWord(keySize);
        long startTime = System.nanoTime();
        runModel(mw, st, size, seed);
        long markovWordTime = System.nanoTime() - startTime;
        System.out.println("Time taken by MarkovWord is "+markovWordTime);

        EfficientMarkovWord emw = new EfficientMarkovWord(keySize);
        long startTime2 = System.nanoTime();
        runModel(emw, st, size, seed);
        long efficientMarkovWordTime = System.nanoTime() - startTime2;
        System.out.println("Time taken by efficientMarkovWord is "+efficientMarkovWordTime);
        long diff = markovWordTime - efficientMarkovWordTime;
        System.out.println("Time Difference: "+diff);

    }

    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
        //mr.runMarkov();
        mr.testHashMap();
        mr.compareMethods();
    }
}
