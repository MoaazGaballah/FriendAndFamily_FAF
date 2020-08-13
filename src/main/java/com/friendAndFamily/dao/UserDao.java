package com.friendAndFamily.dao;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.util.CollectionUtils;
import com.friendAndFamily.entity.User;
import com.friendAndFamily.utils.GenerateUtils;

// Data Access Object
public class UserDao {


  public static ArrayList<User> users = new ArrayList<>();

  public UserDao() {
    // TODO Auto-generated constructor stub
    if (CollectionUtils.isEmpty(UserDao.users)) {
      GenerateUtils.generateRandomUsers();
    }
  }

  public ArrayList<User> get() {
    return UserDao.users;
  }

  public User get(String msisdn) {
    for (User user : UserDao.users) {
      if (user.getMsisdn() == Long.parseLong(msisdn)) {
        return user;
      }
    }
    return null;
  }

  public boolean update(String msisdn, User user) {

    String fisrtName = user.getFirstName();
    String lastName = user.getLastName();

    boolean updated = false;

    for (User usr : UserDao.users) {
      if (usr.getId() == Integer.parseInt(msisdn)) {

        // update first name
        if (!usr.getFirstName().equals(fisrtName)) {
          usr.setFirstName(fisrtName);
          updated = true;
        }

        // update last name
        if (!usr.getLastName().equals(lastName)) {
          usr.setFirstName(lastName);
          updated = true;
        }

        break;
      }
    }
    return updated;
  }

  public boolean create(User user) {

    final AtomicLong count = new AtomicLong();
    String firstName, lastName;
    long number;

    firstName = user.getFirstName();
    lastName = user.getLastName();
    number = user.getMsisdn();
    User newUser = User.builder().id(count.incrementAndGet()).firstName(firstName)
        .lastName(lastName).msisdn(number).build();

    UserDao.users.add(newUser);

    return true;
  }

  public boolean delete(String msisdn) {
    for (User usr : UserDao.users) {
      if (usr.getId() == Integer.parseInt(msisdn)) {
        // remove element from array list by element value
        UserDao.users.remove(usr);
        return true;
      }
    }
    return false;
  }
}
