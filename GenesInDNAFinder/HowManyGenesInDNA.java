package GenesInDNAFinder;
import static GenesInDNAFinder.FindingManyGenes.findGeneWhere;

public class HowManyGenesInDNA {
    /**
     * countGenes...
     * @param dna a String parameter named dna representing a string of DNA.
     * @return a String parameter named dna representing a string of DNA.
     */
    public int countGenes (String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String gene = findGeneWhere(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            count += 1;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }

    public void testCountGenes () {
        int counts = countGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        System.out.println(counts);
        counts = countGenes("ATGTAAGATGCCCTGT");
        System.out.println(counts);
        counts = countGenes("AKJDHLKDFFSD");
        System.out.println(counts);
    }

    public static void main(String[] args) {
        HowManyGenesInDNA howManyGenesInDNA = new HowManyGenesInDNA();
        howManyGenesInDNA.testCountGenes();
    }
}
