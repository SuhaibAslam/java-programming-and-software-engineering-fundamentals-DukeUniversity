import edu.duke.*;

public class Cipher {
    private String alphabet;
    private String shiftedAlphabet;
    private String lowerAlphabet;
    private String lowerShiftedAlphabet;
    private int mainKey;
    public Cipher(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        lowerShiftedAlphabet = lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);
    }
    public String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder(input);
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
    public String decrypt (String input) {
        Cipher c = new Cipher(26-mainKey);
        String decrypted = c.encrypt(input);
        return decrypted;
    }
    public void cipherTester (String input) {
        String encrypted = encrypt(input);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }
    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        c.caesarTester();
        Cipher cc = new Cipher(15);
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        cc.cipherTester(input);
        FileResource fr = new FileResource("datatest1.txt");
        System.out.println(fr);

    }
}

