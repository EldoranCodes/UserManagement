# Things need I need to do
- get all users 
- get users
- create user
- update user
- update user using patch
- delete user
 ---
# must fix, and test.

 - errror in searching a user, if user is not found must return user not found, not Internal server error, coz, its informative for user consumer of the APi



# improvements or extendsion
 - validation classes can be extend to input formats and more.

---
 - get All user endpoint
 - get user by Id
 - validation of null values
 - creating user with requestbody using userDto class
 - user validitor for request bodyies

---

# UserManagement
A simple crud REST API
- filestructure can be cotnroeller servoice jpa

-it has endpioints:
get
getall users
post a user via dto or json in body
put http method for updating a user //cant be null must be valid
patch for updating atributes of a user can be null the attribute
delete http method by id

thats for v1
--for future featurs illbe targeting to implement:
searching paramgters then return a list of users, can be uto suggest, or per request or per type of a letter in search box

