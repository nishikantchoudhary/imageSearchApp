package com.axxess.imageapp.databases;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.axxess.imageapp.daos.CommentsDAO;
import com.axxess.imageapp.models.Comment;

@Database(entities = {Comment.class}, version = 1, exportSchema = false)
public abstract class CommentsDatabase extends RoomDatabase {

    /**
     * Returns dao.
     *
     * @return dao
     */
    public abstract CommentsDAO commentsDAO();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile CommentsDatabase INSTANCE;

    public static CommentsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CommentsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CommentsDatabase.class, "comments_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
