package com.marko.codeChallenge.controller;

import com.marko.codeChallenge.model.Message;
import com.marko.codeChallenge.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Message message, BindingResult result, Principal principal) {
        Message createdUser = messageService.create(message);
        return new ResponseEntity<Message>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/findAllOrderByDateCreated")
    public List<Message> findAllMessagesOrderdByDateCreated(Principal principal) {
        return messageService.findAllMessagesOrderdByDateCreated();
    }

}
