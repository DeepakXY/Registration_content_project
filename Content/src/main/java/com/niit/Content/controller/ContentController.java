package com.niit.Content.controller;

import com.niit.Content.model.CommonUser;
import com.niit.Content.model.Contents;
import com.niit.Content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class ContentController {

    @Autowired
    ContentService contentService;

    private ResponseEntity<?> responseEntity;

    //http://localhost:8085/contect/v2/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CommonUser commonUser) {
        responseEntity = new ResponseEntity<>(contentService.registerUser(commonUser), HttpStatus.CREATED);
        return responseEntity;
    }

    //http://localhost:8085/api/v2/add/Deepak@gmail.com
    @PostMapping("/add/{userEmail}")
    public ResponseEntity<?> addEmail(@RequestBody Contents contents, @PathVariable String userEmail) {
        responseEntity = new ResponseEntity<>(contentService.addContent(contents, userEmail), HttpStatus.CREATED);
        return responseEntity;
    }

    //http://localhost:8085/contect/v2/delete/{userEmail}/
    @DeleteMapping("/delete/{userEmail}/{contentname}")
    public ResponseEntity<?> deleteContent(@PathVariable String userEmail, @PathVariable String contentname) throws Exception {
        responseEntity = new ResponseEntity<>(contentService.deleteContent(userEmail, contentname), HttpStatus.OK);
        return responseEntity;
    }

    //http://localhost:8085/contect/v2/get/{userEmail}
    @GetMapping("/get/{userEmail}")
    public ResponseEntity<?> getAllUser(@PathVariable String userEmail) throws Exception {
        responseEntity = new ResponseEntity<>(contentService.getAllContents(userEmail), HttpStatus.OK);
        return responseEntity;
    }
}
