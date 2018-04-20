package com.vinicius.twitter.service;

import java.util.List;

import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;

public interface PostService
{
    /**
     * Create the time line for user based in users that he is following and in
     * reverse chronological order.
     * 
     * @param userDTO
     *            User Logged
     * @return Timeline
     */
    List<PostDTO> createTimelineForUser(UserDTO userDTO);

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
     */
    List<PostDTO> postByUser(UserDTO userDTO);
}
