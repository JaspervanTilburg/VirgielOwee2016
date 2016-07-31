package com.example.jasper.virgielowee2016;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jasper on 27-6-2016.
 */
public class SelfieListAdapter extends ArrayAdapter<SelfieItem> {

    private ArrayList<SelfieItem> data;

    public SelfieListAdapter(Context context, ArrayList<SelfieItem> data) {
        super(context, R.layout.selfie_layout, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.selfie_layout, parent, false);
        ViewGroup.LayoutParams params = rowView.getLayoutParams();

        ImageButton vo_button = (ImageButton) rowView.findViewById(R.id.vo_button);
        vo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement vind ik vo
            }
        });

        new DownloadImageTask((ImageView) rowView.findViewById(R.id.selfie_display)).
                execute("http://jouwidealestudententijd.nl/selfies/" + data.get(position).getId() + ".jpg");

        return rowView;
    }

    public ArrayList<SelfieItem> getSelfies() {
        ArrayList<SelfieItem> selfieItems = new ArrayList<>();
        String s = "";

        try {
            URL ur = new URL("http://jouwidealestudententijd.nl/api/selfies");
            HttpURLConnection yc =(HttpURLConnection) ur.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                s+= inputLine;

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!s.equals("")){
            try {
                JSONArray array = new JSONArray(s);
                for(int i = 0; i < array.length(); i++){
                    JSONObject o = array.getJSONObject(i);
                    SelfieItem item = new SelfieItem(o.getInt("id"));
                    selfieItems.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return selfieItems;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
