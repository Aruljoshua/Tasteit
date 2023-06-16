package com.example.tasteit.Listeners;

import com.example.tasteit.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);

    void didError(String message);
}
