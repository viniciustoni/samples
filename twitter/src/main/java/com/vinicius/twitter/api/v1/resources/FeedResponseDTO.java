package com.vinicius.twitter.api.v1.resources;

public class FeedResponseDTO
{
    private final String response;

    public FeedResponseDTO(String response)
    {
        this.response = response;
    }

    public String getResponse()
    {
        return response;
    }
}
