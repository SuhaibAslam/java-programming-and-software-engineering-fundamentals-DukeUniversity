public class WordPlay
{
    public boolean isVowel (char ch) {
        String vowels = "aeiou";
        ch = Character.toLowerCase(ch);
        if (vowels.indexOf(ch) != -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void isVowelTester () {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0; i < alphabet.length(); i++) {
            char ch = alphabet.charAt(i);
            boolean vowelBool = isVowel(ch);
            System.out.println(""+ch+"  "+vowelBool);
        }
    }

    public String replaceVowels (String phrase, char ch) {
        StringBuilder mutablePhrase = new StringBuilder(phrase);
        for (int i=0; i < mutablePhrase.length(); i++) {
            char phraseChar = mutablePhrase.charAt(i);
            if (isVowel(phraseChar)) {
                mutablePhrase.replace(i, i + 1, Character.toString(ch));
            }
        }
        return mutablePhrase.toString();
    }

    public void replaceVowelsTester () {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println(replaceVowels(alphabet, '*'));
    }

    public String emphasize (String phrase, char ch) {
        StringBuilder mutablePhrase = new StringBuilder(phrase);
        for (int i=0; i < mutablePhrase.length(); i++) {
            char currentChar = Character.toLowerCase(mutablePhrase.charAt(i));
            if (i%2 == 0 && currentChar == ch) {
                mutablePhrase.replace(i,i+1,"*");
            }
            if (i%2 != 0 && currentChar == ch) {
                mutablePhrase.replace(i,i+1,"+");
            }
        }
        return mutablePhrase.toString();
    }

    public static void main(String[] args) {
        WordPlay wp = new WordPlay();
        wp.isVowelTester();
        wp.replaceVowelsTester();
        System.out.println(wp.emphasize("dna ctgaaactga", 'a'));
        System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a'));
    }
}

