package com.marko.codeChallenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountLockedResponse {
    private String locked;
    public AccountLockedResponse() {
        locked = "Nalog je NEAKTIVAN, kontaktiraje administratora!";
    }
}
