package com.example.userManagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

  List<Users> findByFirstName(String firstName);

  List<Users> findByLastName(String lastName);

  Users findById(long id);

}
