package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false,length = 80)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 80)
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "dni",nullable = false,length = 8)
    private String dni;
}
