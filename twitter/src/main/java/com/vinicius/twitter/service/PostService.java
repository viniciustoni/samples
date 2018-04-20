package com.vinicius.twitter.service;

import java.util.List;

import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.exceptions.UserNotFoundException;

public interface PostService
{
    /**
     * Create the time line for user based in users that he is following and in
     * reverse chronological order.
     * 
     * @param userEmail
     *            User email
     * @return Timeline
     * @throws UserNotFoundException
     */
    List<PostDTO> createTimelineForUser(String userEmail) throws UserNotFoundException;

    /**
     * Post in timeline
     * 
     * @param postDTO
     */
    void post(PostDTO postDTO);

    /**
     * Get posts from user passed by parameter.
     * 
     * @param userDTO
     * @return List of posted by user
     * @throws UserNotFoundException
     */
    List<PostDTO> postByUser(UserDTO userDTO) throws UserNotFoundException;
}
