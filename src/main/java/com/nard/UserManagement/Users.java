package com.nard.UserManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String firstName;
  private String lastName;

  private String username;
  private String passwrod;

  private String role;

  private Long createdBy; // adminId || user

  public Users(String firstName, String lastName, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  public Users(String firstName, String lastName, String role, String username, Long createdBy) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.username = username;
    this.createdBy = createdBy;
  }

  public Users(UserDto userDto) {
    this.firstName = userDto.getFirstName();
    this.lastName = userDto.getLastName();
    this.role = userDto.getRole();
    this.username = userDto.getUsername();
    this.createdBy = userDto.getCreatedBy();
  }

  @Override
  public String toString() {
    return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
  }

}
