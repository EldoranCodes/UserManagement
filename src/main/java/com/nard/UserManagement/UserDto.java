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
  private String name;

  public UserDto(long id, String firstName, String lastName) {
    this.id = id;

    String name = lastName + ", " + firstName;
    this.name = name;
  }

}
