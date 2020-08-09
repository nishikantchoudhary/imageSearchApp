package com.axxess.imageapp.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.axxess.imageapp.listeners.IDataReceivedListener;
import com.axxess.imageapp.models.ImageEntity;
import com.axxess.imageapp.models.RepositoryError;
import com.axxess.imageapp.repositories.ImageRepository;

import java.util.List;

public class ImageViewModel extends AndroidViewModel {

    private ImageRepository mImageRepository;

    private MutableLiveData<List<ImageEntity>> mImageEntityList;
    private MutableLiveData<String> mNetworkError;
    public ImageViewModel(@NonNull Application application) {
        super(application);
        mImageRepository = new ImageRepository(application);
        mImageEntityList = new MutableLiveData<>();
        mNetworkError = new MutableLiveData<>();
    }

    public MutableLiveData<List<ImageEntity>> getImages() {
        return mImageEntityList;
    }

    public MutableLiveData<String> getNetworkError(){
        return mNetworkError;
    }

    public void searchImages(String query) {
        IDataReceivedListener dataReceivedListener = new IDataReceivedListener() {
            @Override
            public void onSuccess(List<ImageEntity> imageEntities) {
                mImageEntityList.setValue(imageEntities);
            }

            @Override
            public void onFailure(RepositoryError repositoryError) {
                mNetworkError.setValue(repositoryError.getMessage());
            }
        };
        mImageRepository.searchImages(query, dataReceivedListener);
    }
}
