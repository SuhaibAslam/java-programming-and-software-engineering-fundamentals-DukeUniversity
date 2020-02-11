import edu.duke.FileResource;

public class CaesarBreaker {
    public void countLetters (String input, int[] counts) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i < input.length(); i++) {
            char currentChar = Character.toLowerCase(input.charAt(i));
            int idx = alphabet.indexOf(currentChar);
            if (idx != -1) {
                counts[idx] += 1;
            }
        }
    }

    public int maximumIndex (int[] values) {
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

    public String decrypt (String input) {
        CaesarCipher cc = new CaesarCipher();
        int[] counts = new int[26];
        countLetters(input, counts);
        int maxIdx = maximumIndex(counts);
        int decryptKey;
        if (maxIdx < 4) {
            decryptKey = 26-(4-maxIdx);
        } else {
            decryptKey = maxIdx - 4;
        }
        String decrypted = cc.encrypt(input, 26-decryptKey);
        return decrypted;
    }

    public void decryptTester (String input, int key) {
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(input, key);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }

    public void letterCountAndIndexTester () {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        CaesarBreaker cb = new CaesarBreaker();
        cb.countLetters("aaaaaa", counts);
        for (int i=0; i < 26; i++) {
            System.out.println(alphabet.charAt(i)+" Frequency: "+counts[i]);
        }
        int maxIndex = cb.maximumIndex(counts);
        System.out.println("Max Index is: "+maxIndex);
    }

    public String halfOfString (String message, int start) {
        StringBuilder halfString = new StringBuilder();
        if (start == 0) {
            for (int i=0; i < message.length(); i++) {
                if (i%2 == 0) {
                    halfString.append(message.charAt(i));
                }
            }
        }
        if (start == 1) {
            for (int i=0; i < message.length(); i++) {
                if (i%2 != 0) {
                    halfString.append(message.charAt(i));
                }
            }
        }
        return halfString.toString();
    }

    public int getKey (String s) {
        int[] counts = new int[26];
        CaesarBreaker cb = new CaesarBreaker();
        cb.countLetters(s, counts);
        int maxIndex = cb.maximumIndex(counts);
        int key;
        if (maxIndex < 4) {
            key = 26-(4-maxIndex);
        } else {
            key = maxIndex - 4;
        }
        return key;
    }

    public String decryptTwoKeys (String encrypted) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("First Key: "+firstKey);
        System.out.println("Second Key: "+secondKey);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26-firstKey, 26-secondKey);
        System.out.println(encrypted);
        System.out.println(decrypted);
        return decrypted;
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.letterCountAndIndexTester();
        cb.decryptTester("Just a test string with lots of eeeeeeeeeeeeeeeees", 3);
        System.out.println(cb.halfOfString("Qbkm Zgis", 0));
        System.out.println(cb.halfOfString("Qbkm Zgis", 1));
        String decrypted = cb.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        String encryptedString = fr.asString();
        decrypted = cb.decryptTwoKeys(encryptedString);
    }
}
