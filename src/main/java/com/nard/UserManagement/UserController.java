package com.nard.UserManagement;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  @GetMapping("/users")
  public ApiResponse getAllUsers() {
    List<UserDto> usersDto = userService.getAllUsers();
    return new ApiResponse(true, "List of All Users", usersDto);
  }
  // public List<UserDto> getUsers() {
  // return userService.getAllUsers();
  // }

  // get user GET
  @GetMapping("/users/{id}")
  public ApiResponse getSingleUser(@PathVariable("id") Long id) {
    log.info("fetching user with id: {}", id);

    return userService.getUser(id);
  }

  // create user POST

  // udpate user, PUT = update all user
  //
}
