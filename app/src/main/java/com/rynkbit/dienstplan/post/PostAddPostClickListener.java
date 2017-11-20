package com.rynkbit.dienstplan.post;

import android.content.Intent;
import android.view.View;

/**
 * Created by michael on 11/20/17.
 */

public class PostAddPostClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), PostActivity.class);
        view.getContext().startActivity(intent);
    }
}
