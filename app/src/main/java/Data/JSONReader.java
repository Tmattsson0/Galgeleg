package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONReader {

    public ArrayList<Words> wordsFromText() throws IOException, NullPointerException {
        ArrayList<Words> wordArrayList = new ArrayList<>();
        String jsonStr = loadJSONFromAsset();

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray Ordliste = jsonObj.getJSONArray("Ordliste");

                // looping through All Words
                for (int i = 0; i < Ordliste.length(); i++) {
                    JSONObject c = Ordliste.getJSONObject(i);

                    String id = c.getString("id");
                    String ord = c.getString("ord");
                    String definition = c.getString("definition");

                    Words word = new Words(id, ord, definition);
                    wordArrayList.add(word);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            return wordArrayList;
    }

    //Loads words from JSON file on disk.
    public String loadJSONFromAsset() {
        String json;
        String file = "assets/words.json";

        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
