public class CaesarCipher {
    public String encrypt (String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        String lowerShiftedAlphabet = lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);
        for (int i=0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            // currentChar index in the alphabet.
            if (Character.isUpperCase(currentChar)) {
                int idx = alphabet.indexOf(currentChar);
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            if (Character.isLowerCase(currentChar)) {
                int idx = lowerAlphabet.indexOf(currentChar);
                char newChar = lowerShiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public void caesarTester () {
        int key = 15;
        String encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", key);
        System.out.println("This is encrypted: "+encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public String encryptTwoKeys (String input, int key1, int key2) {
        String key1Encryption = encrypt(input, key1);
        String key2Encryption = encrypt(input, key2);
        StringBuilder doubleEncryption = new StringBuilder();
        for (int i=0; i < input.length(); i++) {
            if (i%2 == 0) {
               doubleEncryption.append(key1Encryption.charAt(i));
            }
            if (i%2 != 0) {
                doubleEncryption.append(key2Encryption.charAt(i));
            }
        }
        return doubleEncryption.toString();
    }

    public void doubleEncryptTester () {
        int key1 = 14;
        int key2 = 24;
        //String encrypted = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", key1, key2);
        //System.out.println(encrypted);
        String decrypted = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",26-key1, 26-key2);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.caesarTester();
        cc.doubleEncryptTester();
    }
}
