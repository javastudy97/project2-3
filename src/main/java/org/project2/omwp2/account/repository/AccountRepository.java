package org.project2.omwp2.account.repository;

import org.project2.omwp2.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    /* 현재 잔여금 */
    @Query(value = "SELECT SUM(ac_income) - SUM(ac_expend) FROM account WHERE ac_id <=:ac_id", nativeQuery = true)
    int findByAcId(@Param("ac_id") Long acId);
}
