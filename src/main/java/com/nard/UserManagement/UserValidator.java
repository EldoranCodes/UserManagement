package com.nard.UserManagement;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {

  public ValidationResult validateUserDto(UserDto userDto) {

    ValidationResult result = new ValidationResult();

    String firstName = userDto.getFirstName();
    String lastName = userDto.getLastName();
    String username = userDto.getUsername();
    String role = userDto.getRole();
    Long createdBy = userDto.getCreatedBy();

    // validate user input if not null
    if (firstName == null) {
      result.addError("firstName", "Invalid firstName, input must not be null");
    }

    if (lastName == null) {
      result.addError("lastName", "Invalid LastName, input must not be null");
    }
    if (username == null) {
      result.addError("username", "Invalid username, input must not be null");
    }
    if (role == null) {
      result.addError("role", "Invalid role, input must not be null");
    } else {
      if (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("user")) {
        result.addError("role", "Invalid role, input must be 'admin' or 'user'");
      }
    }
    if (createdBy == null || createdBy < 1) {
      result.addError("createdBy", "Invalid createdBy, input must not be null");
    }
    return result;
  }

}
