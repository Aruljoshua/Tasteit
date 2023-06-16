package com.example.tasteit.Listeners;

import com.example.tasteit.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);

    void didError(String message);
}
