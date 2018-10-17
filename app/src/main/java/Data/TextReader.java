package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class TextReader {

    public void wordsFromText() throws Exception{
        File words = new File("Data/words.txt");
        System.out.println(words.exists());

        BufferedReader br = new BufferedReader(new FileReader(words));

        String st;

        while ((st = br.readLine()) != null){
            System.out.println(st);
        }
        br.close();
    }
}
