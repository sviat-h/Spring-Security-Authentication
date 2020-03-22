package com.cursor.repository;

import com.cursor.models.Role;
import com.cursor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(value = "update users set username=:username, password =:password, role =:role, active=:active where id=:id", nativeQuery = true)
    void updateUserById(@Param("id") Integer id, @Param("username") String username, @Param("password") String password, @Param("role") Role role, @Param("active") boolean active);

    @Query(value = "update users set active=:active where id=:id", nativeQuery = true)
    void updateActiveById(@Param("id") Integer id, @Param("active") boolean active);

    @Query(value = "update users set role=:role where id=:id", nativeQuery = true)
    void updateRoleById(@Param("id") Integer id, @Param("role") Role role);
}
