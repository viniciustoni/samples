package com.vinicius.twitter.service;

import java.util.List;

import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.exceptions.RelationshipAlreadyExistException;
import com.vinicius.twitter.exceptions.UserNotFoundException;

public interface UserService
{
    /**
     * Registre user
     * 
     * @param userDTO
     */
    void registreUser(UserDTO userDTO);

    /**
     * Start to follow one user.
     * 
     * @param followingDTO
     * @throws UserNotFoundException
     *             in case of user not found
     * @throws RelationshipAlreadyExistException
     *             in case of relationship already exists
     */
    void follow(FollowingDTO followingDTO) throws UserNotFoundException, RelationshipAlreadyExistException;

    /**
     * Get all users that he the {@link UserDTO} is following
     * 
     * @param userDTO
     * @return List of following
     */
    List<UserDTO> following(UserDTO userDTO);

    /**
     * Get All users that is her/him followers
     * 
     * @param userDTO
     * @return list of follwers
     */
    List<UserDTO> followers(UserDTO userDTO);
}
