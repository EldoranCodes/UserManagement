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

  public ApiResponse getUser(Long id) {

    // check first if exist
    Users user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    log.info("Returning UserDto with id of {}:", id);

    UserDto userDto = null;
    ApiResponse apiResponse = null;

    // fail response
    boolean success = false;
    String message = "User not found! with id: " + id;
    if (user != null) {
      userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName());

      // if user exist chage it to success response
      success = true;
      message = "User found!";
      apiResponse = new ApiResponse(false, message, userDto);
    }
    return apiResponse;
  }

}
