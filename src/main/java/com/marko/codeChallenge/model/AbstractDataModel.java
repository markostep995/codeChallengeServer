package com.marko.codeChallenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class AbstractDataModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Version
//    private Long version;
    @JsonFormat(pattern = "DD-MMM-YYYY HH:mm:ss")
    @Column(updatable = false)
    private Date createdAt;
    @JsonFormat(pattern = "DD-MMM-YYYY HH:mm:ss")
    private Date updatedAt;
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
