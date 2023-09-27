package com.ms.reloff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ms.reloff.dto.UserDto;
import com.ms.reloff.entity.Users;

/**
 * IUserRepository (operaciones CRUD de la entidad) - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11
 */
public interface IUserRepository extends CrudRepository<Users, Long> {

	Optional<Users> findByUsernameOrMail(String Username, String mail);
	
    Users findByNationalIdOrUsername(String nationalId, String username);
	
	Long countByNationalIdOrUsername(String nationalId, String username);
	
	@Query(   "   SELECT new com.ms.reloff.dto.UserDto (u.id ,u.nationalId ,u.names ,u.middleName ,u.lastName ,u.mail ,u.businessPosition ,u.pass, p.id, p.profileName) " 
			+ "     FROM Users u "
			+ "			 inner join u.profile p ")
	List<UserDto> findAllUsers();
	
	Users findByMailOrNationalIdOrUsername(String mail, String nationalId, String username);
	
}
