package GladLibsAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        String slicedDNA = dna.substring(start, dna.length());
        for (int i=0; i<slicedDNA.length()-3; i += 3){
            String currentCodon = slicedDNA.substring(i, i+3);
            if (codonMap.containsKey(currentCodon)) {
                codonMap.put(currentCodon,codonMap.get(currentCodon)+1);
            } else {
                codonMap.put(currentCodon, 1);
            }
        }
    }

    public void debug() {
        String dna = "CGTTCAAGTTCAA";
        buildCodonMap(0, dna);
        for (String codon: codonMap.keySet()) {
            System.out.println(codon+" "+codonMap.get(codon));
        }
    }

    public String getMostCommonCodon() {
        int maxCount = 0;
        String commonCodon = "";
        for (String codon : codonMap.keySet()) {
            int currCount = codonMap.get(codon);
            if (currCount>maxCount) {
                maxCount = currCount;
                commonCodon = codon;
            }
        }
        return commonCodon;
    }

    public void  printCodonCounts(int start, int end) {
        for (String codon : codonMap.keySet()) {
            int currCount = codonMap.get(codon);
            if (currCount>=start && currCount<=end) {
                System.out.println(codon+" "+codonMap.get(codon));
            }
        }
    }


    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        for (int i=0; i<3; i++) {
            buildCodonMap(i, dna);
            System.out.println("The Number of Unique Codons in Reading Frame "+ i +" is "+codonMap.size());
            String commonCodon = getMostCommonCodon();
            System.out.println("Most common codon: "+commonCodon+"\nCounts of most common Codon: "+codonMap.get(commonCodon));
            int startIndex = 0;
            int stopIndex = 1000;
            System.out.println("Counts of Codons between "+startIndex+" and "+stopIndex+" inclusive are: ");
            printCodonCounts(startIndex,stopIndex);

        }

    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
