package org.example.shoppinglist.service.impl;

import org.example.shoppinglist.model.entity.UserEntity;
import org.example.shoppinglist.repository.UserRepository;
import org.example.shoppinglist.service.UserService;
import org.example.shoppinglist.model.service.UserServiceModel;
import org.example.shoppinglist.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        return user != null ? modelMapper.map(userRepository.save(user), UserServiceModel.class) : null;

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> {
                    return modelMapper.map(user, UserServiceModel.class);
                })
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id).setUsername(username);
    }

    @Override
    public void logout() {
        currentUser.setId(null).setUsername(null);
    }
}
