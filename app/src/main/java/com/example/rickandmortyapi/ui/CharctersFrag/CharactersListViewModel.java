package com.example.rickandmortyapi.ui.CharctersFrag;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmortyapi.API.APIConfig;
import com.example.rickandmortyapi.Models.APIRoot;
import com.example.rickandmortyapi.Models.CharactersItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersListViewModel extends ViewModel {

    MutableLiveData<List<CharactersItems>> listMutableLiveDataCharacters = new MutableLiveData<>();
    public void getCharacterList() {
        Call<APIRoot> call =  APIConfig.callAPI().getCharacterList();
        call.enqueue(new Callback<APIRoot>() {
            @Override
            public void onResponse(Call<APIRoot> call, Response<APIRoot> response) {
                if(response.isSuccessful()) {
                    List<CharactersItems> resultsList = response.body().results;
                    listMutableLiveDataCharacters.setValue(resultsList);
                }
            }

            @Override
            public void onFailure(Call<APIRoot> call, Throwable t) {

            }
        });


    }

    public void search(String query){
        List<CharactersItems> originalList = listMutableLiveDataCharacters.getValue();
        List<CharactersItems> filteredList = new ArrayList<>();

        for (CharactersItems items : originalList){
            if (items.getName().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(items);
            }
        }

        listMutableLiveDataCharacters.setValue(filteredList);

    }
}