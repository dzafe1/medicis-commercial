package com.medicis.commercial.repository;

import com.medicis.commercial.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User  u where u.email = :email")
    User getByEmail(@Param("email") String email);

    @Query("SELECT u FROM User  u where u.email = :email")
    User getOneByEmail(@Param("email") String email);

    User findOneById(Long id);
    @Query("SELECT u FROM User u where is_active=true and role='ADMIN'")

    List<User> getAllAdmins();
    @Query("SELECT u FROM User u where u.role <> 'ADMIN'")

    List<User> getAllNonAdmins();
    @Query(nativeQuery = true,value = "SELECT count(id) FROM users WHERE is_active=true AND role!='ADMIN'")

    Long getAllNonAdminsCount();
    @Query(nativeQuery = true,value = "SELECT count(id) FROM users WHERE is_active=true")
    Long getAllUsers();
}
