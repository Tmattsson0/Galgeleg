package com.example.thomasmattsson.galgeleg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Data.Player;
import Data.Words;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class WordListActivity extends AppCompatActivity {

    private String TAG = WordListActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    // URL to get contacts JSON
    private static String url = "https://api.myjson.com/bins/1h427q";

    ArrayList<Words> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        wordList = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.word_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new WordAdapter(wordList);

        new GetWords().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetWords extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(WordListActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray Ordliste = jsonObj.getJSONArray("Ordliste");

                    // looping through All Contacts
                    for (int i = 0; i < Ordliste.length(); i++) {
                        JSONObject c = Ordliste.getJSONObject(i);

                        String id = c.getString("id");
                        String ord = c.getString("ord");
                        String Definition = c.getString("Definition");

                        Words word = new Words(id, ord, Definition);
                        wordList.add(word);

//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        String mobile = phone.getString("mobile");
//                        String home = phone.getString("home");
//                        String office = phone.getString("office");

                        // tmp hash map for single contact
//                        HashMap<String, String> word = new HashMap<>();

                        // adding each child node to HashMap key => value
//                        word.put("id", id);
//                        word.put("ord", ord);
//                        word.put("Definition", Definition);
//                        word.put("mobile", mobile);

                        // adding contact to contact list
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into RecyclerView
             * */
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }

    }
}
