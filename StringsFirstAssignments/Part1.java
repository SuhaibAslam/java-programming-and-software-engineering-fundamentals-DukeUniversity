package StringsFirstAssignments;

public class Part1 {
    public String findSimpleGene (String dna) {
        String result;
        int startIdx = dna.indexOf("ATG");
        if (startIdx == -1) {
            return "";
        }
        int stopIdx = dna.indexOf("TAA", startIdx+3);
        if (stopIdx == -1) {
            return "";
        }
        result = dna.substring(startIdx, stopIdx+3);
        return result;
    }

}
