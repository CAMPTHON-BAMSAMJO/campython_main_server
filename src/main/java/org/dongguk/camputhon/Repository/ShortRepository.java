package org.dongguk.camputhon.Repository;

import org.dongguk.camputhon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortRepository extends JpaRepository<Short, Long> {
    Optional<Short> findById(Long id);
    List<Short> findByUser(User user);
}
