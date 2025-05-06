package com.example.listrandom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private final List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the current item in a position in the list
        Item item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        }

        // find name and progress
        TextView name = convertView.findViewById(R.id.item_name);
        ProgressBar progressBar = convertView.findViewById(R.id.task_progress);

        // set the values for name and progress
        if (item != null) {
            name.setText(item.getName());
            progressBar.setProgress(item.getProgress());
        }

        // return data to be displayed
        return convertView;
    }
}
