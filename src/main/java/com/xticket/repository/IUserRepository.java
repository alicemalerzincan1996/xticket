package com.hrproject.repository;

import com.hrproject.repository.entity.UserProfile;
import com.hrproject.repository.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserProfile, Long> {

    Boolean existsByUsername(String username);

    Optional<UserProfile> findByAuthId(Long authId);

    Optional<UserProfile> findByUsername(String username);

    List<UserProfile> findByStatus(EStatus status);
    Optional<UserProfile> findOptionalByUsernameAndPassword(String username, String password);
}