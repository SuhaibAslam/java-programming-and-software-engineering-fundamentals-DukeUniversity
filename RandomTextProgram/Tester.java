package RandomTextProgram;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows() {
        MarkovOne m1 = new MarkovOne();
        FileResource fr = new FileResource("RandomTextProgram/data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        m1.setTraining(st);
        ArrayList<String> followsArray = m1.getFollows("he");
        System.out.println(followsArray.size());
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testGetFollows();
    }
}
