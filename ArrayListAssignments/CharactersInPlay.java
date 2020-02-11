package ArrayListAssignments;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> characterCounts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }

    public void update(String person) {
        int Index = characters.indexOf(person);
        if (Index == -1) {
            characters.add(person);
            characterCounts.add(1);
        } else {
            int value = characterCounts.get(Index);
            characterCounts.set(Index, value+1);
        }
    }

    public void findAllCharacters() {
        characters.clear();
        characterCounts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int currIndex = line.indexOf('.');
            if (currIndex != -1) {
                String currString = line.substring(0, currIndex);
                update(currString);
            }
        }
    }

    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i=0; i<characterCounts.size(); i++) {
            if ((characterCounts.get(i) > characterCounts.get(maxIndex))) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int thirdLargestIndex() {
        int largest = 0;
        int secondlargest = 0;
        int thirdlargest = 0;
        for (int i=0; i<characterCounts.size(); i++) {
            if (largest < characterCounts.get(i)) {
                thirdlargest = secondlargest;
                secondlargest = largest;
                largest = characterCounts.get(i);
            } else if (secondlargest < characterCounts.get(i)) {
                thirdlargest = secondlargest;
                secondlargest = characterCounts.get(i);
            } else if (thirdlargest < characterCounts.get(i)) {
                thirdlargest = characterCounts.get(i);
            }
        }
        return thirdlargest;
    }

    public void tester() {
        findAllCharacters();
        charactersWithNumParts(10,15);
        int maxIndex = findIndexOfMax();
        System.out.println("The most frequent character is "+characters.get(maxIndex)+" it occurs "+characterCounts.get(maxIndex)+" times");
        int thirdMostPartsIndex = thirdLargestIndex();
        System.out.println("The character with the third most speaking parts is "+characters.get(thirdMostPartsIndex)+" with "+characterCounts.get(thirdMostPartsIndex)+" parts");
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i=0; i<characters.size(); i++) {
            if (characterCounts.get(i)>=num1 && characterCounts.get(i)<=num2) {
                System.out.println("Character:"+characters.get(i)+"\t Frequency: "+characterCounts.get(i));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
