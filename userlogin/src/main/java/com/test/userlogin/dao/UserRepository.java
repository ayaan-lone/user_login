package com.test.userlogin.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.userlogin.entity.Users;

//Created a Repository for the database(acts as a middle man)

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	//Created an Abstract Method that will take the Argument as the UserName and will find them.
	    Optional<Users> findByUsername(String username);
	



}