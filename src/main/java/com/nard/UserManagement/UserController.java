package com.nard.UserManagement;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserController {

  private final UserService userService;

  private final Logger log = LoggerFactory.getLogger(UserController.class);

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // get all users GET
  @GetMapping("/user")
  public ApiResponse getAllUsers() {
    List<UserDto> usersDto = userService.getAllUsers();
    return ApiResponse.ok("List of All Users", usersDto);
  }

  // get user GET
  @GetMapping("/user/{id}")
  public ApiResponse getSingleUser(@PathVariable("id") Long id) {
    log.info("fetching user with id: {}", id);
    return userService.getUser(id);
  }

  // create user POST
  // here in creating a user it must have a body or parameters
  @PostMapping("/user")
  public ApiResponse createUser(@RequestBody UserDto userDto) {
    return userService.createUser(userDto);
  }

  // udpate user, PUT = update all user
  //
}
