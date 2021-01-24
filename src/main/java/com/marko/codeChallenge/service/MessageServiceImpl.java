package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.Message;
import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Override
    public Message create(Message message) {
        User user = userService.findById(message.getUser().getId());
        message.setUser(user);
        Message createdMessage = messageRepository.save(message);
        return createdMessage;
    }
    @Override
    public List<Message> createAndReturnList(Message message) {
        User user = userService.findById(message.getUser().getId());
        message.setUser(user);
        Message createdMessage = messageRepository.save(message);
        return findAllMessagesOrderdByDateCreated();
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
