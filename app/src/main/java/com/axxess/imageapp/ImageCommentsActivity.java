package com.axxess.imageapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.axxess.imageapp.adapter.CommentsAdapter;
import com.axxess.imageapp.models.Comment;
import com.axxess.imageapp.models.ImageEntity;
import com.axxess.imageapp.viewmodels.CommentsViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.axxess.imageapp.constants.Constants.IMAGE_SELECTED_KEY;

public class ImageCommentsActivity extends AppCompatActivity {

    /**
     * Selected image entity.
     */
    private ImageEntity mImageEntity;

    /**
     * Selected image view.
     */
    private ImageView mImageView;


    /**
     * Instance of Recycler view to show comments.
     */
    private RecyclerView mCommentsRecyclerView;

    /**
     * Instance of ViewModel.
     */
    private CommentsViewModel mCommentViewModel;
    /**
     * Instance of comment adapter.
     */
    private CommentsAdapter mCommentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_comments);
        if (getIntent().getSerializableExtra(IMAGE_SELECTED_KEY) != null) {
            mImageEntity = (ImageEntity) getIntent().getSerializableExtra(IMAGE_SELECTED_KEY);
        }
        mCommentViewModel = ViewModelProviders.of(this).get(CommentsViewModel.class);
        mImageView = findViewById(R.id.expandedImage);
        mCommentsRecyclerView = findViewById(R.id.coments_recyclerview);
        mCommentsAdapter = new CommentsAdapter(this);
        mCommentsRecyclerView.setAdapter(mCommentsAdapter);
        mCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCommentViewModel.getComments(mImageEntity.getId()).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(@Nullable final List<Comment> employees) {
                mCommentsAdapter.setComments(employees);
                mCommentsAdapter.notifyDataSetChanged();
            }
        });
        Picasso.with(this).load(mImageEntity.getLink()).into(mImageView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mImageEntity.getName());
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(mImageEntity.getName());
        final EditText commentInput = findViewById(R.id.comment_input);
        ImageView saveCommentButton = findViewById(R.id.comment_save);
        saveCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commentInput.getText().toString().isEmpty()) {
                    Toast.makeText(ImageCommentsActivity.this, "Comment cannot be empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Comment comment = new Comment();
                    comment.setComment(commentInput.getText().toString());
                    comment.setImageId(mImageEntity.getId());
                    mCommentViewModel.saveNewComment(comment);
                }
            }
        });
    }
}
