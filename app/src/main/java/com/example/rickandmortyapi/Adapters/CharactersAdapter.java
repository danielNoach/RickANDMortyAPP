package com.example.rickandmortyapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmortyapi.Intarfaces.Navagation;
import com.example.rickandmortyapi.Models.CharactersItems;
import com.example.rickandmortyapi.ViewHolders.CharacterViewHolder;
import com.example.rickandmortyapi.databinding.CharactersCardBinding;

import java.util.ArrayList;
import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharacterViewHolder> implements Filterable {

    Context context;
    List<CharactersItems> charactersItemsList;

    private List<CharactersItems> filteredItems;
    private MyFilter filter;
    private Navagation navagation;

    public CharactersAdapter(Context context, List<CharactersItems> charactersItemsList, Navagation navagation) {
        this.context = context;
        this.charactersItemsList = charactersItemsList;
        this.filteredItems = new ArrayList<>(charactersItemsList);
        this.filter = new MyFilter();
        this.navagation = navagation;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CharactersCardBinding binding = CharactersCardBinding.inflate(layoutInflater, parent, false);
        return new CharacterViewHolder(binding, navagation);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(filteredItems.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    public Filter getFilter() {
        return filter;
    }

    private class MyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<CharactersItems> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(charactersItemsList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CharactersItems item : charactersItemsList) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItems.clear();
            filteredItems.addAll((List<CharactersItems>) results.values);
            notifyDataSetChanged();
        }
    }
}