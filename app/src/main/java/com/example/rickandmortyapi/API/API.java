package com.example.rickandmortyapi.API;

import com.example.rickandmortyapi.Models.APIRoot;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("character")
    Call<APIRoot> getCharacterList();
}
