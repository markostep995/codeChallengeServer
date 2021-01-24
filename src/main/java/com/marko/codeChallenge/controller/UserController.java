package com.marko.codeChallenge.controller;

import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.payload.LoginRequest;
import com.marko.codeChallenge.security.JwtTokenProvider;
import com.marko.codeChallenge.service.UserService;
import com.marko.codeChallenge.exceptions.AppException;
import com.marko.codeChallenge.payload.JWTLoginSuccessResponse;
import com.marko.codeChallenge.payload.RefreshTokenRequest;
import com.marko.codeChallenge.util.DescriptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.marko.codeChallenge.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/findByUsernameAndPassword/{username}/{password}")
    private ResponseEntity<?> findUserById(@PathVariable String username, @PathVariable String password, Principal principal) {
        User user = userService.findByUsernameAndPassword(username, password);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<User> findAll(Principal principal) {
        return userService.findAll();
    }

    @GetMapping("/findAllNotDeleted")
    public List<User> findAllNotDeleted(Principal principal) {
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result, Principal principal) {
        User createdUser = userService.create(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, Principal principal) {
        User updatedUser = userService.update(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.CREATED);
    }

    @PatchMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> deleteUser(@PathVariable Long id, Principal principal){
        return userService.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        Authentication authentication = null;
        try {
            authentication = authenticateUser(loginRequest.getUsername(),loginRequest.getPassword());
        } catch (Exception e){
            throw new AppException(DescriptionUtil.getErrorDescription("INCORECT_USERNAME_OR_PASSWORD"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> tokenList = getTokens(authentication);
        String accessToken = tokenList.get(0);
        String refreshToken = tokenList.get(1);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, accessToken, refreshToken));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        JWTLoginSuccessResponse response = tokenProvider.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(response);
    }

    private Authentication authenticateUser(String username, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return authentication;
    }

    private List<String> getTokens(Authentication authentication){
        String accessToken = TOKEN_PREFIX + tokenProvider.generateAccessToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);
        List<String> tokenList = new ArrayList<>();
        tokenList.add(0,accessToken);
        tokenList.add(1,refreshToken);
        return tokenList;
    }

}
