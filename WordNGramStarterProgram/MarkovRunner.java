package WordNGramStarterProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

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
        for(int k=0; k < 1; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource("src/InterfaceAbstractProgram/data/confucius.txt");
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne();
        int size = 120;
        int seed = 175;
        runModel(markovWord, st, size, seed);
    }

    public void runMarkovTwo() {
        FileResource fr = new FileResource("src/InterfaceAbstractProgram/data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is just a test yes this is a simple test";
        MarkovWordTwo markovWord = new MarkovWordTwo();
        int size = 120;
        int seed = 549;
        runModel(markovWord, st, size, seed);
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

    public void testGetFollows() {
        String input = "this is just a test yes this is a simple test";
        MarkovWordOne mw1 = new MarkovWordOne();
        runModel(mw1, input, 200, 42);
    }

    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
        mr.runMarkovTwo();
    }

}
