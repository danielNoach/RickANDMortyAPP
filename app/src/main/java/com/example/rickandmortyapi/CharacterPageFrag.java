package com.example.rickandmortyapi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rickandmortyapi.Models.CharactersItems;
import com.example.rickandmortyapi.databinding.FragmentCharacterPageBinding;

public class CharacterPageFrag extends Fragment {

    FragmentCharacterPageBinding binding;
    private CharactersItems charactersItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCharacterPageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        charactersItems = (CharactersItems) requireArguments().getSerializable("character");
        binding.characterPageGendertv.setText(charactersItems.gender);
        binding.characterPageNametv.setText(charactersItems.name);
        binding.characterPageSpeciestv.setText(charactersItems.species);
        binding.characterPageTypetv.setText(charactersItems.type);
        binding.characterPageStatustv.setText(charactersItems.status);
        Glide.with(binding.getRoot().getContext()).load(charactersItems.image).into(binding.characterPageImagetv);
    }
}