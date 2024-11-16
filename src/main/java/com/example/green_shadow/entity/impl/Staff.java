package com.example.green_shadow.entity.impl;

import com.example.green_shadow.entity.Gender;
import com.example.green_shadow.entity.Role;
import com.example.green_shadow.entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Staff implements SuperEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_code")
    )
    private List<Field> fields;
    @JsonIgnore
    @OneToMany(mappedBy = "staff",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;
    @ManyToOne
    @JoinColumn(name = "logCode")
    private Log log;

    public void addField(Field field){
        fields.add(field);
        field.getStaff().add(this);
    }
    public void removeField(Field field){
        fields.remove(field);
        field.getStaff().remove(this);
    }









}
