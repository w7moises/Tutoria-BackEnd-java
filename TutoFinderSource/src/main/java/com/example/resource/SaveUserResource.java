package com.example.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserResource {
    private Long userId;
    private SavePersonResource personResource;
    private String username;
    private String password;
    private boolean enabled;
}
