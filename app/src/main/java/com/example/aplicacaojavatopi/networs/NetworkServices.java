package com.example.aplicacaojavatopi.networs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServices {

    private final String URL_API_BASE = "https://api.github.com/search/";

    private Retrofit retrofit = null;

    public Retrofit configuraRetrofi(){
        if (retrofit == null){
            return retrofit = new Retrofit.Builder().baseUrl(URL_API_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        }else {
            return retrofit;
        }
    }

}
