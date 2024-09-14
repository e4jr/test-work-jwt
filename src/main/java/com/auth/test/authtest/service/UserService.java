package com.auth.test.authtest.service;


import com.auth.test.authtest.exceptions.LoginException;
import com.auth.test.authtest.exceptions.RegistrationException;
import com.auth.test.authtest.models.Users;
import com.auth.test.authtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserReg {
    private final UserRepository userRepository;

    @Override
    public void register(String clientId, String clientSecret) {
        if(userRepository.findById(clientId).isPresent())
            throw new RegistrationException("Client with id: " + clientId + " already registered");

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new Users(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) {
        Optional<Users> optionalUserEntity = userRepository.findById(clientId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException("Client with id: " + clientId + " not found");

        Users clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
