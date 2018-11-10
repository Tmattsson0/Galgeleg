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
    private static String url = "https://api.myjson.com/bins/yvro6";

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
            pDialog.setMessage("Vent venligst...");
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

                    // looping through All Words
                    for (int i = 0; i < Ordliste.length(); i++) {
                        JSONObject c = Ordliste.getJSONObject(i);

                        String id = c.getString("id");
                        String ord = c.getString("ord");
                        String definition = c.getString("definition");

                        Words word = new Words(id, ord, definition);
                        wordList.add(word);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing fejl: " + e.getMessage(),
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
                                "Kunne ikke få JSON fra server. Kig i LogCat for mulige fej!",
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
