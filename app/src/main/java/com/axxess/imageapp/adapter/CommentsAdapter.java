package com.axxess.imageapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.axxess.imageapp.R;
import com.axxess.imageapp.databinding.CommentItemRecyclerViewBinding;
import com.axxess.imageapp.models.Comment;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Comment> mComments;

    public CommentsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private CommentItemRecyclerViewBinding mCommentRecyclerViewBinding;
        private CheckBox checkBox;

        private CommentViewHolder(CommentItemRecyclerViewBinding CommentRecyclerViewBinding) {
            super(CommentRecyclerViewBinding.getRoot());
            this.mCommentRecyclerViewBinding = CommentRecyclerViewBinding;
        }
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommentItemRecyclerViewBinding CommentRecyclerViewBinding = DataBindingUtil
                .inflate(mInflater, R.layout.comment_item_recycler_view, parent, false);
        CommentViewHolder CommentViewHolder = new CommentViewHolder(CommentRecyclerViewBinding);
        return CommentViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, int position) {
        if (mComments != null) {
            final Comment current = mComments.get(position);
            holder.mCommentRecyclerViewBinding.setComment(current);
        }
    }

    public void setComments(List<Comment> Comments) {
        mComments = Comments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mComments != null)
            return mComments.size();
        else return 0;
    }
}