package com.simplecrud.crud.repository;

import com.simplecrud.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Search by username (you can add more search criteria as needed)
    List<User> findByUsernameContaining(String username);

    // Example of a custom query
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword% OR u.name LIKE %:keyword%")
    List<User> searchUsers(@Param("keyword") String keyword);
}
