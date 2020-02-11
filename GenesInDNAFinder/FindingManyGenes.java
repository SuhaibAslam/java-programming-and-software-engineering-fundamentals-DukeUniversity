package GenesInDNAFinder;

/**
 * Find a gene in a strand of DNA where the stop codon could be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * Find all the genes (where the stop codon could be any of the three stop codons) in a strand of DNA.
 */

public class FindingManyGenes {

    /**
     * findStopCodon method for finding the index of first stopCodon occurence.
     * @param dna a String parameter named dna
     * @param startIndex an integer parameter named startIndex that
     *                   represents where the first occurrence of ATG occurs in dna
     * @param stopCodon a String parameter named stopCodon
     * @return This method returns the index of the first occurrence of stopCodon that
     * appears past startIndex and is a multiple of 3 away from startIndex.
     * If there is no such stopCodon, this method returns the length of the dna strand.
     */

    public static int findStopCodon (String dna, int startIndex, String stopCodon) {
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

    public void testFindStopCodon() {
                    //01234567890123456789012345678901234567
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxxxyyyzzzTAGxx";
        System.out.println(""+dna.length());
        int stopCodonIdx;
        stopCodonIdx = findStopCodon(dna,0,"TAA");
        System.out.println("Should be 9, and is... "+stopCodonIdx);
        stopCodonIdx = findStopCodon(dna, 12, "TAA");
        System.out.println("Should be 21, and is... "+stopCodonIdx);
    }

    /**
     * Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
     * Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG”
     * that is a multiple of three away from the “ATG”.
     * Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG”
     * that is a multiple of three away from the “ATG”.
     * Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG”
     * that is a multiple of three away from the “ATG”.
     * @param dna
     * @return Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
     * If there is no valid stop codon and therefore no gene, return the empty string.
     */

    public String findGene (String dna) {
        int startIndex = dna.indexOf("ATG");
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

    public void testFindGene () {
        String dna1 = "ATGxxxyyyzzzTAGTAA";
        String case1 = findGene(dna1);
        System.out.println(case1);
    }

    public static String findGeneWhere (String dna, int where) {
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

    /**
     * printAllGenes has one String parameter dna, representing a string of DNA.
     * In this method you should repeatedly find genes and print each one until there are no more genes.
     * @param dna
     */
    public void printAllGenes (String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGeneWhere(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }

    public static void main(String[] args) {
        FindingManyGenes findingManyGenes = new FindingManyGenes();
        //p1.testFindStopCodon();
        //p1.testFindGene();
        findingManyGenes.printAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    }
}
