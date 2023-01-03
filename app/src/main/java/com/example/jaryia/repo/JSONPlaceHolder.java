package com.example.jaryia.repo;

import com.example.jaryia.model.PostInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolder {
    @GET("adverts/")
    Call<List<PostInfo>> getPost();
}
