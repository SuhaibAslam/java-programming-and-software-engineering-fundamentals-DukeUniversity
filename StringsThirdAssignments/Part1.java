package StringsThirdAssignments;
import edu.duke.*;
public class Part1 {
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

    public void testGetAllGenes () {
        StorageResource genes = getAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        for (String g: genes.data()) {
            System.out.println(g);
        }
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

    public int countCTG (String dna) {
        return howMany("CTG", dna);
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        //p1.testFindStopCodon();
        //p1.testFindGene();
        p1.testGetAllGenes();
        float cg = p1.cgRatio("ATGCCATAG");
        System.out.println(cg);
        int ctgs = p1.countCTG("AAFAKLJCTGAKLDJALDJAKLSDJLKJCTG");
        System.out.println(ctgs);
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        ctgs = p1.countCTG(dna);
        System.out.println(ctgs);
    }
}
