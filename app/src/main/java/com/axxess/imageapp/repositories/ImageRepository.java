package com.axxess.imageapp.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.axxess.imageapp.daos.CommentsDAO;
import com.axxess.imageapp.databases.CommentsDatabase;
import com.axxess.imageapp.listeners.IDataReceivedListener;
import com.axxess.imageapp.models.Comment;

import java.util.List;

/**
 * Repository of images.
 */
public class ImageRepository {

    private Application mApplication;

    private CommentsDAO mCommentsDAO;

    public ImageRepository(Application application) {
        mApplication = application;
        CommentsDatabase commentsDatabase = CommentsDatabase.getDatabase(mApplication);
        mCommentsDAO = commentsDatabase.commentsDAO();
    }

    public void searchImages(String query, IDataReceivedListener dataReceivedListener) {
        new RemoteRepositoryHelper().searchImages(query, dataReceivedListener);
    }

    public void saveComment(Comment comment) {
        LocalRepositoryHelper.saveCommentInLocalDB(mCommentsDAO, comment);
    }

    public LiveData<List<Comment>> getComments(String imageId) {
        return mCommentsDAO.getComments(imageId);
    }
}
