/**
 * youtubeFinder method reads the lines from the file at this URL location,
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html, and prints 
 * each URL on the page that is a link to youtube.com.
 *
 * @author Suhaib Aslam
 */

package StringsFirstAssignments;
import edu.duke.*;

public class YouTubeFinds {
    public void youtubeFinder () {
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : url.words()) {
            word = word.toLowerCase();
            int youtubeIdx = word.indexOf("youtube.com");
            if (youtubeIdx != -1) {
                int firstQuoteIdx = word.lastIndexOf("\"", youtubeIdx);
                int lastQuoteIdx = word.lastIndexOf("\">");
                System.out.println(word.substring(firstQuoteIdx+1, lastQuoteIdx));
            }
        }
    }

    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            System.out.println("Index + 1 is "+(index+1)+"Index + 4 is "+(index+4));
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
    public void test() {
        //findAbc("abcd");
        findAbc("abcdabc");
    }

    public static void main(String[] args) {
        YouTubeFinds youtube = new YouTubeFinds();
        youtube.youtubeFinder();
        //youtube.test();
    }

}
