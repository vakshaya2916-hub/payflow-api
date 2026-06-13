package com.payflow.repository;

import com.payflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUpiId(String upiId);

    @Query("SELECT u FROM User u WHERE u.balance > :minBalance")
    List<User> findUsersWithBalanceGreaterThan(@Param("minBalance") BigDecimal minBalance);
}
