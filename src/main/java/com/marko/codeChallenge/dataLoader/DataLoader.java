package com.marko.codeChallenge.dataLoader;

import com.marko.codeChallenge.model.Message;
import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.repository.MessageRepository;
import com.marko.codeChallenge.repository.UserRepository;
import com.marko.codeChallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> userList = userRepository.findAll();
        if (userList.size() == 0){
            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword(bCryptPasswordEncoder.encode("codeChallenge"));
            user1.setDeleted(false);
            user1.setActive(true);
            User createdUser1 = userRepository.save(user1);

            User user2 = new User();
            user2.setUsername("marko");
            user2.setPassword(bCryptPasswordEncoder.encode("codeChallenge"));
            user2.setDeleted(false);
            user2.setActive(true);
            User createdUser2 = userRepository.save(user2);

            User user3 = new User();
            user3.setUsername("milica");
            user3.setPassword(bCryptPasswordEncoder.encode("codeChallenge"));
            user3.setDeleted(false);
            user3.setActive(true);
            User createdUser3 = userRepository.save(user3);

            Message message = new Message();
            message.setUser(user2);
            message.setMessageText("Hello Doodle Team! I hope you enjoy the application...");
            messageRepository.save(message);
        }


    }
}
