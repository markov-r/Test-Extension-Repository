package com.telerik.extension_repository.repositories;


import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findOneByUsername(@Param("username") String username);

    User findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    User findById(Long id);

    @Modifying
    @Query(value = "UPDATE User " +
            "SET username = :username, email = :email, password =:password WHERE id = :id")
    void update(@Param("username") String username, @Param("email") String email,
                @Param("password") String password, @Param("id") Long id);

    // Admin
    @Query(value = "SELECT u FROM User AS u")
    List<User> findAll();


    @Query(value = "SELECT u FROM User AS u " +
            "WHERE u.username LIKE CONCAT('%', :searchWord, '%')")
    Page<User> findAllByUsername(@Param("searchWord") String searchWord, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE User " +
            "SET isEnabled = false WHERE id =:id")
    void disableUser(@Param("id") Long id);

    @Query(value = "SELECT u.extensions FROM User AS u")
    Set<Extension> findAllByExtensionsAndId();


}
