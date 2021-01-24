package com.marko.codeChallenge.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTLoginSuccessResponse {
    private boolean success;
    private String token;
    private String refreshToken;
}
