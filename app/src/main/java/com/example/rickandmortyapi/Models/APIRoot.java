package com.example.rickandmortyapi.Models;

import java.util.List;

public class APIRoot {

    public List<CharactersItems> results;

    public List<CharactersItems> getResults() {
        return results;
    }

    public void setResults(List<CharactersItems> results) {
        this.results = results;
    }
}
