package com.example.green_shadow.entity.impl;

import com.example.green_shadow.entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Field implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double extentSizeOfField;
    @JsonIgnore
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Crop> crops;
    @JsonIgnore
    @ManyToMany(mappedBy = "fields",fetch = FetchType.LAZY)
    private List<Staff> staff;
    @Column(columnDefinition = "LONGTEXT")
    private String image1;
    @Column(columnDefinition = "LONGTEXT")
    private String image2;
    @JsonIgnore
    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Equipment> equipments;
    @ManyToOne
    @JoinColumn(name = "logCode")
    private Log log;



}
