package com.nard.UserManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

  List<Users> findByFirstName(String firstName);

  Optional<Users> findByUsername(String username);

  List<Users> findByLastName(String lastName);

  Optional<Users> findById(Long id);

  void delete(Users u);

  void deleteById(Long id);
}
