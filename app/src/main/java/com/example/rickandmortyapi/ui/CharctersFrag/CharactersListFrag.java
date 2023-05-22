package com.example.rickandmortyapi.ui.CharctersFrag;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.rickandmortyapi.Adapters.CharactersAdapter;
import com.example.rickandmortyapi.Intarfaces.Navagation;
import com.example.rickandmortyapi.Models.CharactersItems;
import com.example.rickandmortyapi.R;
import com.example.rickandmortyapi.databinding.FragmentCharactersListBinding;

import java.util.List;

public class CharactersListFrag extends Fragment {

    private CharactersListViewModel mViewModel;
    private FragmentCharactersListBinding binding;

    private CharactersAdapter adapter;

    public static CharactersListFrag newInstance() {
        return new CharactersListFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CharactersListViewModel.class);
        binding = FragmentCharactersListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CharactersListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerInit();
        observeCharacterList();
        mViewModel.getCharacterList();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void observeCharacterList() {
        mViewModel.listMutableLiveDataCharacters.observe(getViewLifecycleOwner(), new Observer<List<CharactersItems>>() {
            @Override
            public void onChanged(List<CharactersItems> charactersItems) {
                adapter = new CharactersAdapter(requireContext(), charactersItems, new UpdateNavigation());
                binding.charactersRecycler.setAdapter(adapter);
            }
        });
    }

    private void recyclerInit() {
        binding.charactersRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private class UpdateNavigation implements Navagation {

        @Override
        public void navigationToCharacterPage(CharactersItems charactersItems) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("character", charactersItems);
            androidx.navigation.Navigation.findNavController(requireView()).navigate(R.id.action_charactersListFrag_to_characterPageFrag, bundle);
        }
    }
}