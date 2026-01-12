# SCRATCH PAD FOR CURLING THE API

##  GET HTTP METHODS

### get all users

curl -X GET \
  -H "Accept: application/json" \
  http://localhost:8100/users

./curlj.sh -H "Accept: application/json" http://localhost:8080/user


### get single user data by ID
curl -X GET \
  -H "Accept: application/json" \
  http://localhost:8080/users/2

./curlj.sh -H "Accept: application/json" http://localhost:8080/user/2

---

### creating user / REGISTER 
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
        "username": "nard",
        "email": "nard@example.com",
        "role": "ADMIN"
      }' \
  http://localhost:8080/users


./curlj.sh -X POST \
  -H "Content-Type: application/json" \
  -d '{
        "firstName": "zarnaih",
        "lastName": "salinas",
        "username": "naih",
        "role": "user",
        "createdBy": 1
      }' \
  http://localhost:8100/users


### updating a user
curl -X PATCH \
  -H "Content-Type: application/json" \
  -d '{"email": "patched@example.com"}' \
  http://localhost:8080/users/2


### deleting user

curl -X DELETE \
  http://localhost:8080/users/2
