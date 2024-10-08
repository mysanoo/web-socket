package com.example.web_socket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user){
        User storedUser = repository.findById(user.getNickName())
                        .orElseThrow(null);
        if(storedUser != null){
            user.setStatus(Status.OFFLINE);
            repository.save(user);
        }
    }

    public List<User> findConnectedUsers(){
        return repository.findAllByStatus(Status.ONLINE);
    }



}
