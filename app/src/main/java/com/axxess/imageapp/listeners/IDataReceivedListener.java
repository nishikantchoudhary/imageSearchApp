package com.axxess.imageapp.listeners;

import com.axxess.imageapp.models.ImageEntity;
import com.axxess.imageapp.models.RepositoryError;

import java.util.List;

public interface IDataReceivedListener {

    void onSuccess(List<ImageEntity> imageEntities);

    void onFailure(RepositoryError repositoryError);
}
