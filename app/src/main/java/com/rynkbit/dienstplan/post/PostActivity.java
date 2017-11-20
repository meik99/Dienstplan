package com.rynkbit.dienstplan.post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rynkbit.dienstplan.R;

public class PostActivity extends AppCompatActivity {
    PostModel postModel;
    PostController postController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postModel = new PostModel();
        postController = new PostController(this);

        postModel.setEditTextName(
                (EditText) findViewById(R.id.txtPostName)
        );
        postModel.setSpinnerPostBurden(
                (Spinner) findViewById(R.id.spinnerPostBurden)
        );
        postModel.setSaveButton(
                (Button) findViewById(R.id.btnPostSave)
        );
        postModel.setCancelButton(
                (Button) findViewById(R.id.btnPostCancel)
        );

        postModel.getSpinnerPostBurden()
                .setAdapter(new PostEditAdapter(postModel));

        if(postModel.getPost().getName() != null &&
                postModel.getPost().getName().isEmpty() == false){
            postModel.getEditTextName().setText(postModel.getPost().getName());

            postModel
                    .getSpinnerPostBurden()
                    .setSelection(
                        postModel
                                .getPost()
                                .getPostBurden().ordinal()
                    );
        }


        postModel.getSpinnerPostBurden().setOnItemSelectedListener(
                postController
        );
        postModel.getSaveButton().setOnClickListener(
                postController
        );
        postModel.getCancelButton().setOnClickListener(
                postController
        );
        postModel.getEditTextName().addTextChangedListener(
                postController
        );
    }
}
