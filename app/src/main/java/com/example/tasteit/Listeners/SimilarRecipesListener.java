package com.example.tasteit.Listeners;

import com.example.tasteit.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);

    void didError(String message);
}
