package com.soloworld.security.controller;

import com.soloworld.security.model.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {



  @GetMapping("/")
  @PreAuthorize("hasAuthority('user:read')")
  public List<User> generateUsers() {
    return Stream
        .of(new User("solo", "soloworld"), new User("hello", "helloworld"))
        .collect(Collectors.toList());
  }

  @PostMapping("/")
  @PreAuthorize("hasAuthority('user:create')")
  public void registerUser(@RequestBody User user) {
  }

  @PutMapping("/{userId}")
  @PreAuthorize("hasAuthority('user:update')")
  public void updateUser(@PathVariable String userId, @RequestBody User user) {

  }

  @DeleteMapping("/{userId}")
  @PreAuthorize("hasAuthority('user:delete')")
  public void deleteUser(@PathVariable String userId) {

  }
}
