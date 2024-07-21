package org.dongguk.camputhon.Repository;

import org.dongguk.camputhon.domain.User;
import org.dongguk.camputhon.domain.enums.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    @Query("SELECT s.activity FROM Short s WHERE s.user.id = :userId GROUP BY s.activity ORDER BY COUNT(s.activity) DESC")
    Activity findMostCommonActivityByUserId(@Param("userId") Long userId);
}
