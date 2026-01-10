package com.nard.UserManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
      UserDto userDto = new UserDto(users.getId(), users.getFirstName(), users.getLastName());
      userDtoList.add(userDto);
    }

    log.info("Returning {} users", userDtoList.size());

    return userDtoList;
  }

  public UserDto getUser(Long id) {
    // check first if exist
    Users user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    log.info("Returning UserDto with id of {}:", id);

    return new UserDto(user.getId(), user.getFirstName(), user.getlastName());

  }

}
