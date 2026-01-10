package com.example.userManagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  private final Logger log = LoggerFactory.getLogger(UserService.class);

  // exquivalent to @autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserDto> getAllUsers() {
    List<UserDto> userDtoList = null;

    List<Users> usersLists = userRepository.findAll();
    userDtoList = new ArrayList<>();

    for (Users users : usersLists) {
      String name = users.getFirstName() + " " + users.getLastName();
      UserDto userDto = new UserDto(users.getId(), name);
      userDtoList.add(userDto);
    }

    log.info("Returning {} users", userDtoList.size());

    return userDtoList;
  }

}
