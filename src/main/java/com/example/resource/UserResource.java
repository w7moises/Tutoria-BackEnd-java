package com.example.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource {
    private PersonResource personResource;
    private String username;
    private String password;
    private boolean enabled;
}
