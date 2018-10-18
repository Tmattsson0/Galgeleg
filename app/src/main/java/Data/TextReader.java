package Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TextReader {

    public ArrayList<String> wordsFromText() throws IOException, NullPointerException {
        int i;
        ArrayList<String> wordArrayList = new ArrayList<String>();

        try {
            String file = "assets/words.txt";

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();

            for (String word : byteArrayOutputStream.toString().split("\\r?\\n")) {
                wordArrayList.add(word);
            }
            System.out.println("Uden for aktivitet" + wordArrayList.toString());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return (wordArrayList);
    }
}
