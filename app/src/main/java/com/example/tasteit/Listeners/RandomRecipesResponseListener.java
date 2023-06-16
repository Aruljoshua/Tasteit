package com.example.tasteit.Listeners;

import com.example.tasteit.Models.RandomRecipeApiResponse;

public interface RandomRecipesResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);

    void didError(String message);
}
