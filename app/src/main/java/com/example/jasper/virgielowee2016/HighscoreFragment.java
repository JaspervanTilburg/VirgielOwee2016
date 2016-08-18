package com.example.jasper.virgielowee2016;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighscoreFragment extends Fragment {

    private HighscoreAdapter adapter;

    public HighscoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_highscore, container, false);

        ListView highscoreList = (ListView) rootView.findViewById(R.id.highscoreList);
        adapter = new HighscoreAdapter(getContext(), new ArrayList<Highscore>());
        highscoreList.setAdapter(adapter);
        loadHighscores();
        return rootView;
    }

    public ArrayList<Highscore> getHighscores() {
        ArrayList<Highscore> selfieItems = new ArrayList<>();
        String s = "";

        try {
            URL ur = new URL("http://jouwidealestudententijd.nl/api/highscore");
            HttpURLConnection yc = (HttpURLConnection) ur.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                s += inputLine;

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!s.equals("")) {
            try {
                JSONArray array = new JSONArray(s);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    Highscore item = new Highscore(o.getInt("highscore"), o.getString("user_name"));
                    selfieItems.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return selfieItems;
    }

    public void loadHighscores() {
        new AsyncTask<String, Void, Void>(){
            protected Void doInBackground(String... urls) {
                try {
                    adapter.addAll(getHighscores());
                    return null;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
