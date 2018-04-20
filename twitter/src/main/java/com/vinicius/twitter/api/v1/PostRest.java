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

import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.validations.PostValidationGroup;
import com.vinicius.twitter.exceptions.UserNotFoundException;
import com.vinicius.twitter.service.PostService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/post")
public class PostRest
{
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Post one new message", nickname = "post")
    public ResponseEntity<String> post(@RequestBody @Validated(PostValidationGroup.class) PostDTO post)
            throws UserNotFoundException
    {
        postService.post(post);
        return new ResponseEntity<>(ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/createTimeline/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of post in timeline", nickname = "Timeline")
    public @ResponseBody List<PostDTO> createTimeline(
            @ApiParam(value = "userEmail", required = true) @PathVariable("userEmail") final String userEmail)
            throws UserNotFoundException
    {
        return postService.createTimelineForUser(userEmail);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/postByUser/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of post from one user", nickname = "postByUser")
    public @ResponseBody List<PostDTO> postByUser(
            @ApiParam(value = "userEmail", required = true) @PathVariable("userEmail") final String userEmail)
            throws UserNotFoundException
    {
        return postService.postByUser(userEmail);
    }
}
