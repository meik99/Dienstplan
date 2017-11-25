package com.rynkbit.dienstplan.workplan.task;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.rynkbit.dienstplan.db.facade.PostFacade;
import com.rynkbit.dienstplan.entities.Post;

import java.util.List;

/**
 * Created by michael on 11/25/17.
 */

class PostSpinnerAdapter extends BaseAdapter{
    private final Context context;
    List<Post> posts;

    public PostSpinnerAdapter(Context context) {
        this.context = context;
        PostFacade postFacade = new PostFacade(context);
        posts = postFacade.getAll();
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return posts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  LayoutInflater.from(context).inflate(
                android.R.layout.simple_dropdown_item_1line,
                parent,
                false
        );

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(posts.get(position).getName());

        return view;
    }
}
