package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Svet on 11.11.2014.
 */
public class MyAdapter extends BaseAdapter {
    Storage data;
    Context context;

    MyAdapter(Storage d, Context c) {
        data = d;
        context = c;
    }

    @Override
    public int getCount() {
        return data.getCandidatesCount();
    }

    @Override
    public Object getItem(int i) {
        return data.getCandidateById(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null);
        TextView curView = (TextView) v.findViewById(R.id.candidate_item);
        curView.setText(data.getCandidateById(i));
        return v;
    }
}
