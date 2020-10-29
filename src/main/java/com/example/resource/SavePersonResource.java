package com.example.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SavePersonResource {
    private String firstName;
    private String lastName;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;
    private String dni;
    private SaveUserResource user;
}
