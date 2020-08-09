package com.axxess.imageapp.repositories;

import android.os.Handler;
import android.util.Log;
import com.axxess.imageapp.apiclients.ImageApiClient;
import com.axxess.imageapp.apis.IImageSourceApi;
import com.axxess.imageapp.constants.Constants;
import com.axxess.imageapp.listeners.IDataReceivedListener;
import com.axxess.imageapp.models.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Helper class that Deals with network related operations.
 */
public class RemoteRepositoryHelper {

    private static final String TAG = RemoteRepositoryHelper.class.getSimpleName();

    private Handler mHandler;
    /**
     * Load group api header parameter hash map.
     */
    private HashMap<String, String> mHeadersMap;

    public RemoteRepositoryHelper() {

    }

    /**
     * Fetches data from api and inserts items in local DB.
     */
    public void searchImages(String query, final IDataReceivedListener dataReceivedListener) {
        addCommonHeaders();
        final IImageSourceApi apiInterface = ImageApiClient.getClient().create(IImageSourceApi.class);
        apiInterface.searchImages(mHeadersMap, query).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response != null && response.body() != null && response.body().getData() != null) {
                    List<ImageEntity> imageEntities = new ArrayList<>();
                    for (Datum datum : response.body().getData()) {
                        if (datum.getImages() != null && datum.getImages().size() > 0) {
                            for (Image image : datum.getImages()) {
                                ImageEntity imageEntity = new ImageEntity();
                                imageEntity.setLink(image.getLink());
                                imageEntity.setId(image.getId());
                                imageEntity.setName(datum.getTitle());
                                imageEntities.add(imageEntity);
                            }
                        }
                    }
                    dataReceivedListener.onSuccess(imageEntities);
                } else {
                    RepositoryError repositoryError = new RepositoryError();
                    repositoryError.setCode(response.code());
                    repositoryError.setMessage("Failed to load images");
                    dataReceivedListener.onFailure(repositoryError);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                RepositoryError repositoryError = new RepositoryError();
                repositoryError.setCode(0);
                repositoryError.setMessage("Internet not available");
                dataReceivedListener.onFailure(repositoryError);
            }
        });
    }


    /**
     * Adds common headers in mHeadersMap.
     */
    private void addCommonHeaders() {
        if (mHeadersMap == null) {
            mHeadersMap = new HashMap<>();
        } else {
            mHeadersMap.clear();
        }
        mHeadersMap.put(Constants.API_KEY, Constants.API_AUTH);
    }
}
