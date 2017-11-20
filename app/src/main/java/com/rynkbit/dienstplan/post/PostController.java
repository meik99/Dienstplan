package com.rynkbit.dienstplan.post;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.rynkbit.dienstplan.db.facade.PostFacade;
import com.rynkbit.dienstplan.entities.Post;

/**
 * Created by michael on 11/20/17.
 */

class PostController implements AdapterView.OnItemSelectedListener, View.OnClickListener, TextWatcher {
    private final PostActivity postActivity;
    private final PostFacade postFacade;
    private boolean spinnerFirstStart = false;

    public PostController(PostActivity postActivity){
        this.postActivity = postActivity;
        this.postFacade = new PostFacade(postActivity);

        if(postActivity.getIntent().hasExtra("post")){
            long postId = postActivity.getIntent().getLongExtra("post", -1);
            postActivity.postModel
                    .setPost(
                            postFacade.getById(postId)
                    );
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(spinnerFirstStart == true){
            postActivity
                    .postModel
                    .getPost()
                    .setPostBurden(
                            Post.PostBurden.values()[i]
                    );
        }else{
            spinnerFirstStart = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(view == postActivity.postModel.getSaveButton()){
            postFacade.merge(postActivity.postModel.getPost());
            postActivity.finish();
        }
        else if(view == postActivity.postModel.getCancelButton()){
            postActivity.finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        postActivity.postModel.getPost().setName(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
