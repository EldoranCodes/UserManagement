package com.nard.UserManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
      String errorMsg = "User Not Found with ID:" + id;
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
          return ApiResponse.error("Username already exist!");
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
    // get the id of user
    Optional<Users> userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      return ApiResponse.error("Invalid User Id, User doesnt Exist!");
    }
    Users existingUser = userOpt.get();

    // validate the new values
    ValidationResult validatedDto = userValidator.validateUserDto(userDto);
    if (!validatedDto.isValid()) {
      return ApiResponse.error("Error updating existing user", validatedDto.getErrors());
    }

    // validates if it has same username
    Optional<Users> sameUsername = userRepository.findByUsername(userDto.getUsername());

    if (!sameUsername.isEmpty()) {
      log.info("Error: username already exist. username input: {} existing: {} with id: {}",
          userDto.getUsername(),
          sameUsername.get().getUsername(),
          sameUsername.get().getId());

      return ApiResponse.error("Username already exist!");
    }

    existingUser.setFirstName(userDto.getFirstName());
    existingUser.setLastName(userDto.getLastName());
    existingUser.setUsername(userDto.getUsername());
    existingUser.setPassword(userDto.getPassword());
    existingUser.setRole(userDto.getRole().toLowerCase());
    existingUser.setCreatedBy(userDto.getCreatedBy());

    Users updatedUser = userRepository.save(existingUser);
    return ApiResponse.ok("Success updating user id: " + id, updatedUser);

  }

  public ApiResponse patchUser(Long id, Map<String, Object> userAttribute) {

    Optional<Users> userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      return ApiResponse.error("Invalid user id, user doesnt exist!");
    }
    Users user = userOpt.get();

    // check the attribute and determine what attribute
    // check if attribute exist then get tis value
    if (userAttribute.containsKey("firstName")) {
      user.setFirstName((String) userAttribute.get("firstName"));
    }
    if (userAttribute.containsKey("lastName")) {
      user.setLastName((String) userAttribute.get("lastName"));
    }
    if (userAttribute.containsKey("username")) {
      user.setUsername((String) userAttribute.get("username"));
    }
    if (userAttribute.containsKey("password")) {
      user.setPassword((String) userAttribute.get("password"));
    }
    if (userAttribute.containsKey("createdBy")) {
      user.setCreatedBy((Long) userAttribute.get("createdBy"));
    }
    if (userAttribute.containsKey("role")) {
      String inputRole = (String) userAttribute.get("role");
      if (!"admin".equalsIgnoreCase(inputRole) && !"user".equalsIgnoreCase(inputRole)) {
        return ApiResponse.error("Invalid role, input must be 'admin' or 'user'");
      }
      user.setRole(inputRole.toLowerCase());
    }

    Users patchedUser = userRepository.save(user);

    UserDto userDto = new UserDto(patchedUser);

    return ApiResponse.ok("Success Pathing user with id: " + id, userDto);
  }

  public ApiResponse deleteUser(Long id) {
    // find a usesr by id
    Optional<Users> u = userRepository.findById(id);
    if (u.isEmpty()) {
      return ApiResponse.error("Invalid user id, user doesnt exist!");
    }
    // Users user = u.get();

    // jpa delete
    userRepository.deleteById(id);

    // verify again, if dont exist it means its deleted
    Optional<Users> deletedUser = userRepository.findById(id);

    if (!deletedUser.isEmpty()) {
      // this is error
      return ApiResponse.error("Error deleting the user id:" + id + ", try again later");
    }
    return ApiResponse.ok("Success deleting the user id: " + id);
  }

}
