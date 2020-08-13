package com.friendAndFamily.utils;

import java.util.concurrent.atomic.AtomicLong;
import com.friendAndFamily.dao.UserDao;
import com.friendAndFamily.entity.User;
import com.github.javafaker.Faker;

public class GenerateUtils {
  public static void generateRandomUsers() {
    Faker faker = new Faker();

    final AtomicLong count = new AtomicLong();
    String firstName, lastName;
    long number;

    for (int i = 0; i < 10; i++) {

      firstName = faker.name().firstName();
      lastName = faker.name().lastName();
      number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

      User userTest = User.builder().id(count.incrementAndGet()).firstName(firstName)
          .lastName(lastName).msisdn(number).build();

      UserDao.users.add(userTest);
    }
  }
}
