package com.example.controller;

import com.example.entity.UserSignIn;
import com.example.resource.SaveUserResource;
import com.example.resource.UserResource;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Page<UserResource>> getAllUsers(Pageable pageable) {
        Page<UserSignIn> usersPage = userService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        Page<UserResource> page=new PageImpl<>(resources);
        //return new PageImpl<>(resources, pageable, resources.size());
        return new ResponseEntity<Page<UserResource>>(page, HttpStatus.OK);
    }

    @PostMapping
    // public ResponseEntity<UserResource> createUser(@Valid @RequestBody SaveUserResource resource)  {
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserSignIn user)  {
        //UserSignIn user = convertToEntity(resource);
        //UserResource userResource=convertToResource(userService.registerUser(user));
        userService.registerUser(user);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }


    private UserSignIn convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, UserSignIn.class);
    }

    private UserResource convertToResource(UserSignIn entity) {
        return mapper.map(entity, UserResource.class);
    }



}