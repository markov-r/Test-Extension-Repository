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

    @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.id = :id")
    Extension findExtensionById(@Param("id") Long id);

    @Query(value =
            "DELETE FROM extensions AS e " +
                    "WHERE e.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

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
            "JOIN users AS u " +
            "ON (u.id  = e.user_id) " +
            "WHERE u.username = :name", nativeQuery = true)
    List<Extension> findExtensionsByOwner(@Param("name") String name);
    // NOTE when returning collection of Entities, it should be in the corresponding Entity Repository


    // FEATURED
    @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.isFeatured = true AND " +
                    "e.status = com.telerik.extension_repository.entities.enums.Status.APPROVED")
    List<Extension> findAllFeatured();

    // NEW
    @Query(value =
            "SELECT * FROM extensions AS e " +
                    "JOIN git_hub_data AS g " +
                    "ON e.id = g.id " +
                    "WHERE e.status = 'APPROVED' " +     //the proper place for WHERE clause
                    "ORDER BY g.latest_commit DESC " +
                    "LIMIT 8",
                    nativeQuery = true)
    List<Extension> getAllSortedByDate();

    // POPULAR
    @Query(value = "SELECT * FROM extensions e " +
            "WHERE e.status = 'APPROVED' " +
            "ORDER BY e.number_of_downloads DESC " +
            "LIMIT 8",
            nativeQuery = true)
    List<Extension> getAllSortedByPopularity();


    @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.status = :approved")
    List<Extension> findAllByStatusApproved(@Param("approved") Status pending);


    List<Extension> getAllByTags(String tag);


    @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.status = 'APPROVED' " +
                    "AND e.name LIKE :name " +
                    "ORDER BY e.name")
    List<Extension> getAllMatchingKeywordOrderByName(@Param("name") String name);


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

    @Modifying
    @Query(value = "UPDATE Extension e " +
            "SET e.isFeatured = true WHERE id = :id")
    void setFeatured(@Param("id") long id);

    @Modifying
    @Query(value = "UPDATE Extension e " +
            "SET e.isFeatured = false WHERE id = :id")
    void removeFeatured(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE Extension e " +
            "SET e.source_repository_link = :source_repository_link  WHERE id = :id")
    void updateSourceLink(@Param("source_repository_link") String source_repository_link, @Param("id") Long id);

    List<Extension> getAllByNameOrderByNameAsc(String name);
}

