package com.example.green_shadow.entity.impl;

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
public class Log implements SuperEntity {
    @Id
    private String logCode;
    private Date LogDate;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @JsonIgnore
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Field> fields;
    @JsonIgnore
    @ManyToMany(mappedBy = "logs",fetch = FetchType.LAZY)
    private List<Crop> crops;
    @JsonIgnore
    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Staff> staff;

    public void addCrop(Crop crop) {
        crops.add(crop);
        crop.getLogs().add(this);
    }
    public void removeCrop(Crop crop) {
        crops.remove(crop);
        crop.getLogs().remove(this);
    }


}
