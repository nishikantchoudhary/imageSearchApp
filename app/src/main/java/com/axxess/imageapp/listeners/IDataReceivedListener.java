package com.axxess.imageapp.listeners;

import com.axxess.imageapp.models.ImageEntity;
import com.axxess.imageapp.models.RepositoryError;

import java.util.List;

/**
 * Listener for receiving data.
 */
public interface IDataReceivedListener {

    /**
     * Called when api call successes.
     *
     * @param imageEntities list of image
     */
    void onSuccess(List<ImageEntity> imageEntities);

    /**
     * Called when api call fails.
     *
     * @param repositoryError error
     */
    void onFailure(RepositoryError repositoryError);
}
