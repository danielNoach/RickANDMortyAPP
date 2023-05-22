package com.example.rickandmortyapi.ViewHolders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmortyapi.Intarfaces.Navagation;
import com.example.rickandmortyapi.Models.CharactersItems;
import com.example.rickandmortyapi.databinding.CharactersCardBinding;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private CharactersCardBinding binding;
    private Navagation navagation;

    public CharacterViewHolder(CharactersCardBinding binding, Navagation navagation) {
        super(binding.getRoot());
        this.binding = binding;
        this.navagation = navagation;
    }

    public void bind(CharactersItems charactersItems)
    {
        binding.characterCardTextview.setText(charactersItems.getName());
        Glide.with(binding.getRoot()).load(charactersItems.getImage()).into(binding.characterCardImageview);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navagation.navigationToCharacterPage(charactersItems);
            }
        });
    }


}
