package VigenereProgram;

import edu.duke.FileResource;

public class Tester {
    public void caesarCipherTester() {
        CaesarCipher cc = new CaesarCipher(3);
        String input = new FileResource("VigenereProgram/VigenereTestData/titus-small.txt").asString();
        String encrypted = cc.encrypt(input);
        System.out.println("===Encrypted===");
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("===Decrypted===");
        System.out.println(decrypted);
        char encryptedLetter = cc.encryptLetter('Z');
        System.out.println("Encrypted letter is "+encryptedLetter);
    }

    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        String input = new FileResource("VigenereProgram/VigenereTestData/titus-small_key5.txt").asString();
        int keyOfEncrypted = cc.getKey(input);
        System.out.println("Key of encrypted message is "+keyOfEncrypted);
    }

    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        String input = new FileResource("VigenereProgram/VigenereTestData/titus-small.txt").asString();
        String encrypted = vc.encrypt(input);
        System.out.println("===Encrypted===");
        System.out.println(encrypted);
        String decrypted = vc.decrypt(encrypted);
        System.out.println("===Decrypted===");
        System.out.println(decrypted);
    }

    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        String input = new FileResource("VigenereProgram/messages/secretmessage1.txt").asString();
        //String message = "abcdefghijklm";
        //int whichSlice = 4;
        //int totalSlices = 5;
        //String slicedString = vb.sliceString(message,whichSlice,totalSlices);
        //System.out.println("Sliced string is "+slicedString);
        int[] keyVigenereBreaker = vb.tryKeyLength(input, 4, 'e');
        System.out.println("Elements of key array outputted by tryKeyLength in VigenereBreaker are ");
        for (int eachKey : keyVigenereBreaker) {
            System.out.println(eachKey);
        }
        vb.breakVigenere();

    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.caesarCipherTester();
        t.testCaesarCracker();
        t.testVigenereCipher();
        t.testVigenereBreaker();
    }
}
