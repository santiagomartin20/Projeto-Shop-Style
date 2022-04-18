package com.ss.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.auth.model.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

	Optional<AuthUser>findByUserName(String username);

}
