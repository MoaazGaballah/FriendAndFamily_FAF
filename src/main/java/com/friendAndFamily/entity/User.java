package com.friendAndFamily.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
  private final long id;
  private String firstName;
  private String lastName;
  private final long msisdn;
}
