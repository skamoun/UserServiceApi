package ma.iss.userservice.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.iss.userservice.domain.AppUser;
import ma.iss.userservice.domain.Role;
import ma.iss.userservice.repo.RoleRepo;
import ma.iss.userservice.repo.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService
{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user= userRepo.findByUsername(username);
        if(user==null){
            log.error("user not found in the database");
            throw  new UsernameNotFoundException("user not found in the database");
        }
        else{
            log.info("user found in the datbase {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public AppUser saveUser(AppUser user) {
        log.info(" saving new user {} to database ",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public void deleteUser(AppUser user) {
        log.info("removing user {} ",user.getName());
        userRepo.delete(user);
    }

    @Override
    public void deleteRole(Role role) {
        log.info("removing role {} ",role.getName());
        userRepo.findAll().forEach(appUser -> {
            appUser.getRoles().remove(role);
        });
        roleRepo.delete(role);
    }


}
