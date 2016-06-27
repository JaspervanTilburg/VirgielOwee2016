package com.example.jasper.virgielowee2016;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import at.markushi.ui.CircleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelfieFragment extends Fragment implements View.OnClickListener {

    private Uri imgUri;
    private View rootView;
    private SelfieListAdapter adapter;

    public SelfieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_selfie, container, false);
        adapter = new SelfieListAdapter(getContext(), new ArrayList<SelfieItem>());
        final ListView list  = (ListView) rootView.findViewById(R.id.selfie_view);
        list.setAdapter(adapter);
        loadSelfies();
        CircleButton imgButton = (CircleButton) rootView.findViewById(R.id.photo_button);
        imgButton.setOnClickListener(this);
        return rootView;
    }

    public void loadSelfies() {
        new AsyncTask<String, Void, Void>(){
            protected Void doInBackground(String... urls) {
                try {
                    adapter.addAll(adapter.getSelfies());
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

    @Override
    public void onClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivityForResult(takePictureIntent, 1);
        }
    }
    
}
