import edu.duke.FileResource;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            boolean isFirstLetter = Character.isLetter(word.charAt(0));
            boolean isLastLetter = Character.isLetter(word.charAt(word.length() - 1));
            int wordLength = word.length();
            //System.out.println(wordLength);

            if (!isFirstLetter) {
                wordLength--;
            }
            if (!isLastLetter) {
                wordLength--;
            }
            if (wordLength >= 30) {
                counts[30] += 1;
            } else if (!isFirstLetter && wordLength==-1) {
                counts[0] += 1;
            } else {
                //System.out.println(word);
                //System.out.println(wordLength);
                counts[wordLength] += 1;
            }
//            if (isFirstLetter && isLastLetter) {
//                int wordLength = word.length();
//                if (wordLength >= 30) {
//                    counts[30] += 1;
//                } else {
//                    counts[wordLength] += 1;
//                }
//            } else if (isFirstLetter || isLastLetter) {
//                int wordLength = word.length() - 1;
//                if (wordLength >= 30) {
//                    counts[30] += 1;
//                } else {
//                    counts[wordLength] += 1;
//                }
//            } else {
//                int wordLength = word.length() - 2;
//                if (wordLength >= 30) {
//                    counts[30] += 1;
//                } else {
//                    System.out.println(wordLength);
//                    counts[wordLength] += 1;
//                }
        }

    }


    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource fr = new FileResource("manywords.txt");
        countWordLengths(fr, counts);
        for (int i=0; i < counts.length; i++) {
            System.out.println(counts[i]+" Words of Length: "+i);
        }
        int maxIndex = indexOfMax(counts);
        System.out.println("Max Index is: "+maxIndex);
    }


    public int indexOfMax (int[] values) {
        int maxValue = values[0];
        int maxIndex = 0;
        for(int i=1;i < values.length;i++){
            if(values[i] > maxValue){
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}

