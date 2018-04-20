package com.vinicius.twitter.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.twitter.api.v1.resources.FeedResponseDTO;
import com.vinicius.twitter.config.properties.MyApplicationProperties;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/feed")
public class FeedRest
{
    @Autowired
    private MyApplicationProperties myApplicationProperties;

    @RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Show application status", nickname = "Show application status")
    public ResponseEntity<FeedResponseDTO> status()
    {

        final FeedResponseDTO infoDTO = new FeedResponseDTO(myApplicationProperties.getApplicationName());
        return ResponseEntity.ok(infoDTO);
    }
}
