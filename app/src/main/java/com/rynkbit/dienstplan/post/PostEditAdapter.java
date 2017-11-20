package com.rynkbit.dienstplan.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.rynkbit.dienstplan.entities.Post;

/**
 * Created by michael on 11/20/17.
 */

class PostEditAdapter extends BaseAdapter{
    private final PostModel postModel;

    public PostEditAdapter(PostModel postModel) {
        this.postModel = postModel;
    }

    @Override
    public int getCount() {
        return Post.PostBurden.values().length;
    }

    @Override
    public Object getItem(int i) {
        return Post.PostBurden.values()[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View result = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);
        TextView textView = result.findViewById(android.R.id.text1);

        textView.setText(getItem(i).toString());
        return result;
    }
}
