package dev.raycool.polaction.user.api.service.impl;

import dev.raycool.polaction.user.api.shared.dto.UserDto;
import dev.raycool.polaction.user.api.UserRepository;
import dev.raycool.polaction.user.api.entity.UserEntity;
import dev.raycool.polaction.user.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        //todo encrypt passwords
        userEntity.setEncryptedPassword("testEncryptedPassword");
        UserEntity storedUserDetails = userRepository.save(userEntity);
        System.out.println(storedUserDetails.toString());
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}
