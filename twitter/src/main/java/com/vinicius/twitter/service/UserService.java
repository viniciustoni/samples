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
     * Get all users that he the user is following
     * 
     * @param userEmail
     * @return List of following
     * @throws RelationshipAlreadyExistException
     *             in case of relationship already exists
     */
    List<UserDTO> following(String userEmail) throws UserNotFoundException;

    /**
     * Get All users that is her/him followers
     * 
     * @param userEmail
     * @return list of follwers
     * @throws RelationshipAlreadyExistException
     *             in case of relationship already exists
     */
    List<UserDTO> followers(String userEmail) throws UserNotFoundException;

    /**
     * Verify if user exist in database.
     * 
     * @param userEmail
     * @throws UserNotFoundException
     */
    void verifyUserExist(String userEmail) throws UserNotFoundException;
}
