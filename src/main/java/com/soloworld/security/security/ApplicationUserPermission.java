package com.soloworld.security.security;

public enum ApplicationUserPermission {
  USER_READ("user:read"),
  USER_DELETE("user:delete"),
  USER_UPDATE("user:update"),
  USER_CREATE("user:create")
  ;

  private final   String permission;
  ApplicationUserPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
