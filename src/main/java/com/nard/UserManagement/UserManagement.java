package com.nard.UserManagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class UserManagement {

  private final Logger log = LoggerFactory.getLogger(UserManagement.class);

  public static void main(String[] args) {
    SpringApplication.run(UserManagement.class, args);
  }

  @Bean
  public CommandLineRunner userMangement(UserRepository userRepo) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {

        // logger.info("Hello, World");

        // add seed data
        // add new user
        userRepo.save(new Users("Nard", "Doe", "admin"));
        userRepo.save(new Users("Billy", "Jeans", "user"));
        userRepo.save(new Users("Poochie", "Salinas", "user"));
        userRepo.save(new Users("karen", "Davila", "user"));
        userRepo.save(new Users("Ash", "Checksum", "user"));

        List<Users> inserteduser = userRepo.findAll();

        int usersCount = inserteduser.size();
        log.info("Seed User:" + usersCount);
      }

    };
  }
}
