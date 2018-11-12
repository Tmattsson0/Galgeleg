package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONReader {

    public ArrayList<String> wordsFromText() throws IOException, NullPointerException {
        int i;
        ArrayList<String> wordArrayList = new ArrayList<>();
        String jsonStr = loadJSONFromAsset();

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray Ordliste = jsonObj.getJSONArray("Ordliste");

                // looping through All Words
                for (i = 0; i < Ordliste.length(); i++) {
                    JSONObject c = Ordliste.getJSONObject(i);

                    String ord = c.getString("ord");
                    System.out.println(ord);
                    wordArrayList.add(ord.toLowerCase());
                }
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        }
    return wordArrayList;
    }

    public String loadJSONFromAsset() {
        String json = null;
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
