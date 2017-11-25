package com.rynkbit.dienstplan.soldiers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rynkbit.dienstplan.R;
import com.rynkbit.dienstplan.db.facade.PostFacade;
import com.rynkbit.dienstplan.db.facade.SoldierFacade;
import com.rynkbit.dienstplan.entities.LikesPost;
import com.rynkbit.dienstplan.entities.Post;
import com.rynkbit.dienstplan.entities.Soldier;

public class LikesPostActivity extends AppCompatActivity {

    private ListView listLikesPost;
    private Soldier soldier;
    private SoldierFacade soldierFacade;
    private PostFacade postFacade;
    private Button btnSaveLikesPost;
    private Button btnCancelLikesPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes_post);

        if(this.getIntent().hasExtra("soldier") == false){
            throw new IllegalArgumentException("Intent must have soldier id");
        }

        soldierFacade = new SoldierFacade(this);
        postFacade = new PostFacade(this);
        soldier = soldierFacade.getById(this.getIntent().getLongExtra("soldier", -1));

        listLikesPost = (ListView) findViewById(R.id.listLikesPost);
        btnSaveLikesPost = (Button) findViewById(R.id.btnSaveLikesPost);
        btnCancelLikesPost = (Button) findViewById(R.id.btnCancelLikesPost);

        listLikesPost.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return postFacade.getAll().size();
            }

            @Override
            public Object getItem(int i) {
                return postFacade.getAll().get(i);
            }

            @Override
            public long getItemId(int i) {
                return postFacade.getAll().get(i).getId();
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View v = LayoutInflater.from(LikesPostActivity.this).inflate(R.layout.item_likes_post, null);
                TextView txtLikesPos = v.findViewById(R.id.txtPostName);
                Spinner spinnerLikesPost = v.findViewById(R.id.spinnerLikesPost);

                txtLikesPos.setText(postFacade.getAll().get(i).getName());
                spinnerLikesPost.setAdapter(
                        new ArrayAdapter<Integer>(LikesPostActivity.this, android.R.layout.simple_spinner_dropdown_item, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                );
                spinnerLikesPost.setSelection(5);

                for(LikesPost post : soldier.getLikesPostList()){
                    if(post.getPost().getId() == postFacade.getAll().get(i).getId()){
                        spinnerLikesPost.setSelection(post.getValue());
                    }
                }

                v.setTag(postFacade.getAll().get(i));
                return v;
            }
        });

        btnSaveLikesPost.setOnClickListener(this.getSaveLikesPostClickListener());
        btnCancelLikesPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikesPostActivity.this.finish();
            }
        });
    }

    private View.OnClickListener getSaveLikesPostClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soldier.getLikesPostList().clear();

                for (int i = 0; i < listLikesPost.getChildCount(); i++){
                    View child = listLikesPost.getChildAt(i);
                    Spinner spinner = child.findViewById(R.id.spinnerLikesPost);
                    Integer value = (Integer) spinner.getSelectedItem();
                    Post post = (Post) child.getTag();
                    LikesPost likesPost = new LikesPost();

                    likesPost.setPost(post);
                    likesPost.setValue(value);

                    soldier.getLikesPostList().add(likesPost);
                }

                soldierFacade.merge(soldier);
                LikesPostActivity.this.finish();
            }
        };
    }
}
