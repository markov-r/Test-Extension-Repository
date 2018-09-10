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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional(readOnly = false)
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User AS u WHERE u.username = :username")
    User findOneByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User AS u WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT u FROM User AS u WHERE username = :username")
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User AS u WHERE u.id = :id")
    User findById(@Param("id") Long id);

    User getUserById(Long id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE User " +
            "SET username = :username, email = :email, password =:password WHERE id = :id")
    void update(@Param("username") String username, @Param("email") String email,
                @Param("password") String password, @Param("id") Long id);

    // Admin
    @Query(value = "SELECT u FROM User AS u")
    List<User> findAll();


    // Search
    @Query(value = "SELECT u FROM User AS u WHERE u.username =:username")
    List<User> findAllByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User AS u " +
            "WHERE u.username LIKE CONCAT('%', :searchWord, '%')")
    Page<User> findAllByUsername(@Param("searchWord") String searchWord, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User " +
            "SET isEnabled = false WHERE id =:id")
    void disableUser(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User " +
            "SET isEnabled = true WHERE id =:id")
    void activateUser(@Param("id") Long id);

    @Query(value = "SELECT u.extensions FROM User AS u")
    Set<Extension> findAllByExtensionsAndId();


    @Modifying
    @Transactional
    @Query(value =
            "DELETE FROM User AS u " +
                    "WHERE u.id = :id")
    void deleteById(@Param("id") Long id);



}
