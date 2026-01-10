package com.nard.UserManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;

  private String firstName;
  private String lastName;

  private String username;
  private String password;

  private String role;
  private Long createdBy;

  private String fullName;

  public UserDto(long id, String firstName, String lastName) {
    this.id = id;

    String fullname = lastName + ", " + firstName;
    this.fullName = fullname;
  }

  // from USER to UserDto
  public UserDto(Users newUser) {
    this.id = newUser.getId();

    this.firstName = newUser.getFirstName();

    this.lastName = newUser.getLastName();

    this.username = newUser.getUsername();

    this.role = newUser.getRole();

    this.createdBy = newUser.getCreatedBy();

    String fullname = lastName + ", " + firstName;
    this.fullName = fullname;
  }

}
