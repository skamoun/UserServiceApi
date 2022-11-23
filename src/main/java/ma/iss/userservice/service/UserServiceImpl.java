package ma.iss.userservice.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.iss.userservice.domain.AppUser;
import ma.iss.userservice.domain.Role;
import ma.iss.userservice.repo.RoleRepo;
import ma.iss.userservice.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService
{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @Override
    public AppUser saveUser(AppUser user) {
        log.info(" saving new user {} to database ",user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info(" saving new role {} to database ",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info(" adding  role {} to user {} ",roleName,username);
        AppUser user = userRepo.findByUsername(username);
    Role role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info(" getting user with  {} from  database ",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("fetching all users ");
        return userRepo.findAll();
    }
}
