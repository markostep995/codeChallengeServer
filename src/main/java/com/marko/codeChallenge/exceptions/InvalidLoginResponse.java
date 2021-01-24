package com.marko.codeChallenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Pogrešno korisničko ime ili lozinka.";
        this.password = " ";
    }
}
