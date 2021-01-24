package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.Message;

import java.util.List;

public interface MessageService extends BasicService<Message> {
    List<Message> findAllMessagesOrderdByDateCreated();
}
