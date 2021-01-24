package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.Message;
import com.marko.codeChallenge.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        Message createdMessage = messageRepository.save(message);
        return createdMessage;
    }

    @Override
    public List<Message> findAllMessagesOrderdByDateCreated() {
        return messageRepository.findAllByOrderByCreatedAtAsc();
    }

    @Override
    public Message update(Message message) {
        return null;
    }

    @Override
    public Message findById(Long id) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public List<Message> delete(Long id) {
        return null;
    }


}
