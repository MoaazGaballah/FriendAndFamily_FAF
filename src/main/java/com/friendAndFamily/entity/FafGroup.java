package com.friendAndFamily.entity;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class FafGroup {
  private final long groupID;
  private ArrayList<User> users = new ArrayList<>();

}
