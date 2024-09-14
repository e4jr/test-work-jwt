package com.auth.test.authtest.repository;

import com.auth.test.authtest.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, String> {
}
