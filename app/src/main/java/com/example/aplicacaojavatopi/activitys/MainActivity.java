package com.example.aplicacaojavatopi.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;

import com.example.aplicacaojavatopi.R;
import com.example.aplicacaojavatopi.adapters.AdapterItems;
import com.example.aplicacaojavatopi.models.Items;
import com.example.aplicacaojavatopi.networs.ItemsServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Items items = null;
    private List<Items> itemsList = new ArrayList<>();
    private final String URL_API_BASE = "https://api.github.com/search/";
    private Retrofit retrofit = null;
    private RecyclerView recyclerView;
    private AdapterItems adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerItens);
        retrofit = new Retrofit.Builder().baseUrl(URL_API_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        items = new Items();
        recuperarListaItens();
    }

    public void recuperarListaItens(){
        ItemsServices itemsServices = retrofit.create(ItemsServices.class);
        Call<List<Items>> listItems = itemsServices.getItemsList();
        listItems.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response != null) {
                    Log.i("Certo", "Certo");

                    itemsList = response.body();

                    AdapterItems adapterItems = new AdapterItems(getApplicationContext(), itemsList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapterItems);
                }else {
                    Log.i("Erro", "Erro");
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Log.i("Falha", "Falha" + t);
            }
        });
    }
}