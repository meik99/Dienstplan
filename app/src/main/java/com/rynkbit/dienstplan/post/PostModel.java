package com.rynkbit.dienstplan.post;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rynkbit.dienstplan.entities.Post;


/**
 * Created by michael on 11/20/17.
 */

public class PostModel {
    private Post post;
    private EditText editTextName;
    private Spinner spinnerPostBurden;
    private Button saveButton;
    private Button cancelButton;

    public PostModel(){
        post = new Post();
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setEditTextName(EditText textViewName) {
        this.editTextName = textViewName;
    }

    public EditText getEditTextName() {
        return editTextName;
    }

    public void setSpinnerPostBurden(Spinner spinnerPostBurden) {
        this.spinnerPostBurden = spinnerPostBurden;
    }

    public Spinner getSpinnerPostBurden() {
        return spinnerPostBurden;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
