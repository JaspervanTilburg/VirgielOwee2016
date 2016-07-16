package com.example.jasper.virgielowee2016;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jasper on 14-7-2016.
 */
public class ProgrammaAdapter extends ArrayAdapter<ProgrammaItem> {

    public ProgrammaAdapter(Context context) {
        super(context, R.layout.programma_layout);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        System.out.println("test");
        View v = view;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.programma_layout, null);
        }

        TextView time = (TextView) v.findViewById(R.id.tijd_view);
        TextView title = (TextView) v.findViewById(R.id.title_view);
        TextView subtitle = (TextView) v.findViewById(R.id.subtitle_view);

        time.setText(getItem(position).getTime());
        title.setText(getItem(position).getTitle());
        subtitle.setText(getItem(position).getSubtitle());

        return v;
    }
}
