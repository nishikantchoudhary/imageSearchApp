package com.axxess.imageapp.apis;

import com.axxess.imageapp.models.Datum;
import com.axxess.imageapp.models.Example;
import com.axxess.imageapp.models.ImageEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.HashMap;
import java.util.List;

public interface IImageSourceApi {

    @GET("search/1")
    Call<Example> searchImages(@HeaderMap HashMap<String, String> headerMap,
                               @Query("q") String query);
}
