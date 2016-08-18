package com.example.jasper.virgielowee2016;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jasper on 18-8-2016.
 */
public class HighscoreAdapter extends ArrayAdapter<Highscore> {

    ArrayList<Highscore> data;

    public HighscoreAdapter(Context context, ArrayList<Highscore> data) {
        super(context, R.layout.score_layout, data);
        this.data = data;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.score_layout, parent, false);
        ViewGroup.LayoutParams params = rowView.getLayoutParams();
        final TextView textView = (TextView) rowView.findViewById(R.id.score_text);
        textView.setText(data.get(position).getName() + "   " + data.get(position).gethighscore());
        return rowView;
    }
}
