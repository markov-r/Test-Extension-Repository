package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.*;
import com.telerik.extension_repository.entities.enums.Status;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Blob;
import java.util.List;

//TODO -> documentation

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    Extension findByName(String name);

    void deleteByName(String name);

    void deleteById(Long id);

    Extension save(Extension extension);

    @Query(value =
            "SELECT e FROM Extension AS e " +
            "ORDER BY e.name")
    List<Extension> findAll();


       @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.status = :pending")
    List<Extension> findAllByStatus(@Param("pending") Status pending);

    @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.isFeatured = :isFeatured")
    List<Extension> findAllFeatured(@Param("isFeatured") boolean isFeatured);

    @Query(value =
            "SELECT e FROM Extension AS e " +
                    "JOIN GitHubData g " +
                    "ON e.id = g.id " +
                    "ORDER BY g.lastCommit")
    List<Extension> getAllSortedByDate();





    List<Extension> getAllByTags(String tag);

    List<Extension> getAllByNameOrderByNameAsc(String name);

    Extension findFirstByName(String name);

    @Modifying
    @Query("UPDATE Extension " +
            "SET name = :name, description = :description, status =:status WHERE id = :id")
    void update(@Param("name") String name, @Param("description") String description,
                @Param("status") Status status, @Param("id") Long id);


//
//
//
//
//    @Query(value =
//            "SELECT e.file FROM Extension AS e " +
//                    "WHERE e.id = :id")
//    Blob downloadFile(@Param("id") Long id);

    @Query(value =
            "SELECT e.fileName FROM Extension AS e " +
                    "WHERE e.id = :id")
    String findByFilename(@Param("id") Long id);

}
