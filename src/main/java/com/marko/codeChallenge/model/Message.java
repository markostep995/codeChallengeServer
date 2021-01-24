package com.marko.codeChallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Message extends AbstractDataModel {

    @NotBlank
    @Column(length = 2000)
    @Size(max = 2000)
    private String messageText;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID")
    private User user;
}
