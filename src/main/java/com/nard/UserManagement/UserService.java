package com.nard.UserManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final Logger log = LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;
  private UserValidator userValidator;

  // exquivalent to @autowired
  public UserService(UserRepository userRepository, UserValidator userValidator) {
    this.userRepository = userRepository;
    this.userValidator = userValidator;
  }

  public List<UserDto> getAllUsers() {
    List<UserDto> userDtoList = null;

    List<Users> usersLists = userRepository.findAll();
    userDtoList = new ArrayList<>();

    for (Users users : usersLists) {
      UserDto userDto = new UserDto(users);
      userDtoList.add(userDto);
    }

    log.info("Returning {} users", userDtoList.size());
    return userDtoList;
  }

  public ApiResponse getUser(Long id) {
    // check first if exist
    Users user = userRepository.findById(id).orElse(null);
    // .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    log.info("Returning UserDto with id of {}:", id);
    UserDto userDto = null;
    ApiResponse apiResponse = null;

    if (user != null) {
      userDto = new UserDto(user);

      // if user exist chage it to success response
      String okMsg = "User Found with ID: " + id;
      apiResponse = ApiResponse.ok(okMsg, userDto);
    } else {
      String errorMsg = "User Not Found with ID;" + id;
      apiResponse = ApiResponse.error(errorMsg);
    }
    return apiResponse;
  }

  public ApiResponse createUser(UserDto userDto) {

    // input must not be null
    ValidationResult validatedDto = userValidator.validateUserDto(userDto);

    if (!validatedDto.isValid()) {
      return ApiResponse.error("Invalid inputs", validatedDto.getErrors());
    }

    // check useranme first before anuthing else
    List<Users> allUsers = userRepository.findAll();
    for (Users u : allUsers) {
      // check username
      if (u.getUsername() != null) {
        // ceheck if not null
        if (u.getUsername().equals(userDto.getUsername())) {

          log.info("username already exist and found in records. input username: {}  recorded username {}",
              userDto.getUsername(),
              u.getUsername());
          return ApiResponse.error("Username Already Exist!");
        }
      }
    }

    // Users adminUser = userRepository.findById(createdBy).orElse(null);
    Optional<Users> adminOpt = userRepository.findById(userDto.getCreatedBy());

    if (adminOpt.isEmpty()) {
      return ApiResponse.error("Invalid input createdBy, user admin doesnt exist!");
    }

    Users newUser = null;
    Users adminUser = adminOpt.get();
    // then check if role of createdBy is admin
    log.info("createdBy User.getRole():{}", adminUser.getRole());
    if (adminUser.getRole().equals("admin")) {
      // create object user
      newUser = new Users(userDto);
    } else {
      return ApiResponse.error("Invalid createdBy, input must be an admin user");
    }

    // insert it in the repo/dao
    try {
      Users savedUser = userRepository.save(newUser);
      // transfer it to dto
      UserDto savedUserDto = new UserDto(savedUser);
      return ApiResponse.ok("Success creating USER!", savedUserDto);

    } catch (DataIntegrityViolationException ex) {
      return ApiResponse.error("Duplicate or invalid data: " + ex.getMostSpecificCause().getMessage());

    } catch (ConstraintViolationException ex) {
      return ApiResponse.error("Database constraint error: " + ex.getMessage());

    } catch (Exception ex) {
      return ApiResponse.error("Unexpected error while creating user.");
    }

  }

  public ApiResponse updateUser(Long id, UserDto userDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

}
