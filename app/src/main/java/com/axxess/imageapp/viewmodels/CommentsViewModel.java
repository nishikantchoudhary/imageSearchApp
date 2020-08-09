package com.axxess.imageapp.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.axxess.imageapp.models.Comment;
import com.axxess.imageapp.repositories.ImageRepository;

import java.util.List;

public class CommentsViewModel extends AndroidViewModel {

    private ImageRepository mRepository;
    private LiveData<List<Comment>> mCommentsList;

    public CommentsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ImageRepository(application);
    }

    public LiveData<List<Comment>> getComments(String imageId) {
        mCommentsList = mRepository.getComments(imageId);
        return mCommentsList;
    }

    public void saveNewComment(Comment comment) {
        mRepository.saveComment(comment);
    }

}
