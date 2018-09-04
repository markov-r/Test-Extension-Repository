package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Authority;
import com.telerik.extension_repository.entities.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority getByAuthority(String authority);

}
