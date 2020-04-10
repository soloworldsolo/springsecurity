package com.soloworld.security.controller;

import com.soloworld.security.model.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/")
  public List<User> generateUsers() {
   return Stream.of(new User("solo","soloworld"), new User("hello","helloworld")).collect(Collectors.toList());
  }
}
