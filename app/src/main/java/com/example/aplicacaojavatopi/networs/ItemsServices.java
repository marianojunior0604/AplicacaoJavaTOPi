package com.example.aplicacaojavatopi.networs;

import com.example.aplicacaojavatopi.models.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemsServices {
    @GET("repositories?q=language:Java&sort=stars&page=1")
    Call<List<Items>> getItemsList();
}
