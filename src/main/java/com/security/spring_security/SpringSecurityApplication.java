package com.security.spring_security;

import java.security.Permission;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;

import com.security.spring_security.persistence.entity.PermissionEntity;
import com.security.spring_security.persistence.entity.RoleEntity;
import com.security.spring_security.persistence.entity.RoleEnum;
import com.security.spring_security.persistence.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);

	}

	/* CREANDO UN BEANS */
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			/* CREANDO UN PERMISSION */
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACCTOR")
					.build();

			/* CREATE ROLES */
			RoleEntity roleAdmin = RoleEntity.builder().roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			RoleEntity roleUser = RoleEntity.builder().roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();
			RoleEntity roleInvited = RoleEntity.builder().roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();
			RoleEntity roleDeveloper = RoleEntity.builder().roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

		};
	}

}
