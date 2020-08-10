package com.axxess.imageapp.apis;

import com.axxess.imageapp.models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

import java.util.HashMap;

public interface IImageSourceApi {

    /**
     * Searches images.
     *
     * @param headerMap headers
     * @param query searched query
     * @return response
     */
    @GET("search/1")
    Call<ResponseModel> searchImages(@HeaderMap HashMap<String, String> headerMap,
                                     @Query("q") String query);
}
