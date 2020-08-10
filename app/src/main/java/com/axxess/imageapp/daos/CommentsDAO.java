package com.axxess.imageapp.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.axxess.imageapp.models.Comment;

import java.util.List;

@Dao
public interface CommentsDAO {

    @Query("Select * from comments_table where imageId= :imageId")
    LiveData<List<Comment>> getComments(String imageId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCommentInLocalDB(Comment comment);

    @Query("DELETE FROM comments_table")
    void deleteAll();
}
