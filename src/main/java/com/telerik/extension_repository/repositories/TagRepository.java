package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tags_extensions AS t WHERE t.extension_id = :id", nativeQuery = true)
    void deleteAllTagsInExtension(@Param("id") Long id);
}
