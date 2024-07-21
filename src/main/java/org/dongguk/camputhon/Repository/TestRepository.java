package org.dongguk.camputhon.Repository;

import org.dongguk.camputhon.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}
