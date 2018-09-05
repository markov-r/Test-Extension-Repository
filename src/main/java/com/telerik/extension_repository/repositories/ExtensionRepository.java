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
            "SELECT * FROM extensions AS e " +
                    "WHERE e.is_featured = :isFeatured " +
                    "LIMIT 8",
                    nativeQuery = true)
    List<Extension> findAllFeatured(@Param("isFeatured") boolean isFeatured);

    @Query(value =
            "SELECT * FROM extensions AS e " +
                    "JOIN git_hub_data AS g " +
                    "ON e.id = g.id " +
                    "ORDER BY g.latest_commit DESC " +
                    "LIMIT 8",
                    nativeQuery = true)
    List<Extension> getAllSortedByDate();





    List<Extension> getAllByTags(String tag);

    List<Extension> getAllByNameOrderByNameAsc(String name);

    Extension findFirstByName(String name);

    @Modifying
    @Query("UPDATE Extension " +
            "SET name = :name, description = :description, status =:status WHERE id = :id")
    void update(@Param("name") String name, @Param("description") String description,
                @Param("status") Status status, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Extension " +
            "SET numberOfDownloads = :numberOfDownloads WHERE id = :id")
    void incrementDownloadsCount(@Param("numberOfDownloads") int numberOfDownloads, @Param("id") Long id);

    @Query(value =
            "SELECT e.fileName FROM Extension AS e " +
                    "WHERE e.id = :id")
    String findByFilename(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE Extension e " +
            "SET e.status = com.telerik.extension_repository.entities.enums.Status.APPROVED WHERE id = :id")
    void approveExtension(@Param("id") long id);

    @Query(value = "SELECT * FROM extensions e " +
                   "ORDER BY e.number_of_downloads DESC " +
                   "LIMIT 8",
                    nativeQuery = true)
    List<Extension> getAllSortedByPopularity();

    @Modifying
    @Query(value = "UPDATE Extension e " +
            "SET e.isFeatured = true WHERE id = :id")
    void setFeatured(@Param("id") long id);

    @Modifying
    @Query(value = "UPDATE Extension e " +
            "SET e.isFeatured = false WHERE id = :id")
    void removeFeatured(@Param("id") Long id);
}

