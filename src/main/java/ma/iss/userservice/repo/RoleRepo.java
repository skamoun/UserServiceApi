package ma.iss.userservice.repo;

import ma.iss.userservice.domain.AppUser;
import ma.iss.userservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
