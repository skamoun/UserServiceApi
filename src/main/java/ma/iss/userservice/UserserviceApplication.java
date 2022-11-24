package ma.iss.userservice;

import ma.iss.userservice.domain.AppUser;
import ma.iss.userservice.domain.Role;
import ma.iss.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	@Bean
 CommandLineRunner run (UserService userService){
		return  args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));

			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
			userService.saveUser(new AppUser(null,"said kmn","skamoun","1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"amine kmn","akamoun","12345",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"ahmed kmn","ahkamoun","123456",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"karim kmn","krkamoun","1234567",new ArrayList<>()));
			userService.addRoleToUser("skamoun","ROLE_USER");
			userService.addRoleToUser("skamoun","ROLE_MANAGER");
			userService.addRoleToUser("skamoun","ROLE_ADMIN");
			userService.addRoleToUser("akamoun","ROLE_MANAGER");
			userService.addRoleToUser("ahkamoun","ROLE_ADMIN");
			userService.addRoleToUser("krkamoun","ROLE_SUPER_ADMIN");

		};
 }

}
