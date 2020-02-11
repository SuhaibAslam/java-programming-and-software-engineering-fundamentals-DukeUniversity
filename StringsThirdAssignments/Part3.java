package StringsThirdAssignments;

import edu.duke.*;

public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1) {
            if (((currIndex - startIndex)%3) == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }

    public String findGeneWhere (String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }

    public StorageResource getAllGenes (String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true) {
            String gene = findGeneWhere(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return geneList;
    }

    public int howMany (String stringa, String stringb) {
        int counts = 0;
        int currIndex = stringb.indexOf(stringa, 0);
        while (currIndex != -1) {
            if ((currIndex >= stringb.length())) {
                break;
            }
            counts += 1;
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
        }
        return counts;
    }

    public float cgRatio (String dna) {
        int numCs = howMany("C", dna);
        int numGs = howMany("G", dna);
        return (float) (numCs+numGs)/dna.length();
    }

    public void processGenes(StorageResource sr) {
        int numGenes = 0;
        for (String s: sr.data()) {
            numGenes += 1;
        }
        System.out.println("Number of genes is..."+numGenes);
        int nineCharCounter = 0;
        System.out.println("STRINGS LONGER THAN 60 CHARS:");
        for (String s: sr.data()) {
            if (s.length()>60) {
                System.out.println(s);
                nineCharCounter += 1;
            }
        }
        System.out.println("Number of strings longer than 60 chars: "+nineCharCounter);
        int cgCounter = 0;
        System.out.println("Strings with cgRation greater than 0.35: ");
        for (String s: sr.data()) {
            if (cgRatio(s)>0.35) {
                System.out.println(s);
                cgCounter += 1;
            }
        }
        System.out.println("Number of strings with cgRatio above 0.35: "+cgCounter);
        int longestLength = 0;
        for (String s: sr.data()) {
            int currLength = s.length();
            if (currLength > longestLength) {
                longestLength = currLength;
            }
        }
        System.out.println("Longest length is: "+longestLength);
    }
    public void testProcessGenes(String dna) {
        // dna for testing "ATGATCTAATTTATGCTGCAACGGTGAAGA"
        StorageResource s1 = getAllGenes(dna);
        processGenes(s1);
    }

    public static void main(String[] args) {
        Part3 p3 = new Part3();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        p3.testProcessGenes(dna);
    }
}
