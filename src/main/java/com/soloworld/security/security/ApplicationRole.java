package com.soloworld.security.security;

import static com.soloworld.security.security.ApplicationUserPermission.USER_CREATE;
import static com.soloworld.security.security.ApplicationUserPermission.USER_DELETE;
import static com.soloworld.security.security.ApplicationUserPermission.USER_READ;
import static com.soloworld.security.security.ApplicationUserPermission.USER_UPDATE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationRole {
  USER(new HashSet<>()),
  ADMIN(Stream.of(USER_READ, USER_CREATE, USER_DELETE, USER_UPDATE).collect(Collectors.toSet())),
  ADMIN_TRAINEE(Stream.of(USER_READ).collect(Collectors.toSet()));

  private Set<ApplicationUserPermission> userPermission ;

  ApplicationRole(Set<ApplicationUserPermission> userPermission) {
    this.userPermission = userPermission;
  }

  public Set<ApplicationUserPermission> getUserPermission() {
    return userPermission;
  }

  public Set<GrantedAuthority> grantedAuthoritySet() {
    return getUserPermission().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(
            Collectors.toSet());
  }
}
