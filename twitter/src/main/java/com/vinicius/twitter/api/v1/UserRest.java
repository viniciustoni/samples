package com.vinicius.twitter.api.v1;

import static org.springframework.http.HttpStatus.ACCEPTED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.dto.validations.StartFollowingValidationGroup;
import com.vinicius.twitter.dto.validations.UserRegistrerValidationGroup;
import com.vinicius.twitter.exceptions.RelationshipAlreadyExistException;
import com.vinicius.twitter.exceptions.UserNotFoundException;
import com.vinicius.twitter.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserRest
{
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/registrer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registrer a new user", nickname = "Registrer")
    public ResponseEntity<String> registreUser(@RequestBody @Validated(UserRegistrerValidationGroup.class) UserDTO user)
    {
        userService.registreUser(user);
        return new ResponseEntity<>(ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/follow", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Follow another user", nickname = "Follow")
    public ResponseEntity<String> follow(
            @RequestBody @Validated(StartFollowingValidationGroup.class) final FollowingDTO followingDTO)
            throws UserNotFoundException, RelationshipAlreadyExistException
    {
        userService.follow(followingDTO);
        return new ResponseEntity<>(ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/following/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of users that the user is following", nickname = "Following")
    public @ResponseBody List<UserDTO> following(
            @ApiParam(value = "userEmail", required = true) @PathVariable("userEmail") final String userEmail)
            throws UserNotFoundException
    {
        return userService.following(userEmail);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/followers/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of follower from user", nickname = "Followers")
    public @ResponseBody List<UserDTO> followers(
            @ApiParam(value = "userEmail", required = true) @PathVariable("userEmail") final String userEmail)
            throws UserNotFoundException
    {
        return userService.followers(userEmail);
    }
}
