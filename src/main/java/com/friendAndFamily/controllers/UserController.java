package com.friendAndFamily.controllers;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.friendAndFamily.dao.UserDao;
import com.friendAndFamily.entity.User;

@RestController
public class UserController {

  UserDao userDao;

  public UserController() {
    userDao = new UserDao();
    // if (CollectionUtils.isEmpty(GenerateUtils.users)) {
    // GenerateUtils.generateRandomUsers();
    // }
  }

  // get all users
  @GetMapping("/users")
  public Object getAllUsers() {
    if (userDao.users.isEmpty()) {
      return "There is no users found!";
    }
    return (ArrayList<User>) this.userDao.get();
  }

  // get user by ID
  @RequestMapping(method = RequestMethod.GET, value = "/user/{msisdn}")
  public ResponseEntity<User> getUserByID(@PathVariable("msisdn") String msisdn) {
    User user = this.userDao.get(msisdn);
    if (user != null) {
      // user bulundu
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } else
      // user bulunamdi
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
  }

  // update user by ID
  // ID path parameter
  // User in query parameter
  @RequestMapping(method = RequestMethod.PUT, value = "/user/{msisdn}",
      consumes = "application/json")
  public ResponseEntity<Boolean> updateUser(@PathVariable("userID") String userID,
      @RequestBody User user) {
    boolean updatingStatus = this.userDao.update(userID, user);
    if (updatingStatus) {
      // update succeeded
      return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    // update not succeeded
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<Boolean> createUser(@RequestBody User user) {
    boolean creatingStatus = this.userDao.create(user);
    if (creatingStatus) {
      // return 200
      // return true;
      return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    // return 404
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/user/{msisdn}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable("msisdn") String msisdn) {
    boolean creatingStatus = this.userDao.delete(msisdn);
    if (creatingStatus) {
      // return 200
      return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    // return 404
    else
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
  }
}
