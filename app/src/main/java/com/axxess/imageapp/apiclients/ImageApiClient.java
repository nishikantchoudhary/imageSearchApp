package com.axxess.imageapp.apiclients;

import com.axxess.imageapp.models.ImageEntity;
import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;


/**
 * Singleton class to get Retrofit instance.
 */
public class ImageApiClient {
    public static final String Base_URL = "https://api.imgur.com/3/gallery/";
    private static Retrofit retrofit = null;

    /**
     * Returns retrofit instance, creates new object if null.
     *
     * @return retrofit instance
     */
    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (retrofit == null) {
            OkHttpClient ok = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ok.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build())
                    .build();
        }
        return retrofit;
    }

//    private void createImageResponseConverter(){
//        GsonBuilder gsonBuilder = new GsonBuilder();
//
//        JsonDeserializer<ImageEntity> deserializer = new JsonDeserializer<ImageEntity>() {
//            @Override
//            public ImageEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//
//            return new ImageEntity(
//                        jsonObject.get("name").getAsString(),
//                        jsonObject.get("id").getAsString()
//                );
//            }
//        };
//        gsonBuilder.registerTypeAdapter(ImageEntity.class, deserializer);
//
//        Gson customGson = gsonBuilder.create();
//        return new GsonConverterFactory().;
//    }
}