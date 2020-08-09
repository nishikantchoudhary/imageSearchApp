package com.axxess.imageapp.repositories;

import android.os.AsyncTask;
import com.axxess.imageapp.daos.CommentsDAO;
import com.axxess.imageapp.models.Comment;

/**
 * Helper class that deals with operations related to local DB sqlite
 */
public class LocalRepositoryHelper {

    private static class SaveCommentAsyncTask extends AsyncTask<Comment, Void, Void> {

        private CommentsDAO mAsyncTaskDao;

        SaveCommentAsyncTask(CommentsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Comment... params) {
            mAsyncTaskDao.addCommentInLocalDB(params[0]);
            return null;
        }
    }

    public static void saveCommentInLocalDB(CommentsDAO dao, Comment newsItem) {
        new SaveCommentAsyncTask(dao).execute(newsItem);
    }
}
