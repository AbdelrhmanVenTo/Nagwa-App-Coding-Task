package com.vento.nagwaappcodingtask.network;


import com.vento.nagwaappcodingtask.ui.model.VideoModelResponse;
import com.vento.nagwaappcodingtask.ui.model.VideoModelResponseItem;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {

    @GET("movies")
    Single<List<VideoModelResponseItem>> getMoviesList();



}
