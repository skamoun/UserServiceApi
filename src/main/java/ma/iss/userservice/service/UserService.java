package ma.iss.userservice.service;

import ma.iss.userservice.domain.AppUser;
import ma.iss.userservice.domain.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username , String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    void deleteUser(AppUser user);
    void deleteRole(Role role);
}
