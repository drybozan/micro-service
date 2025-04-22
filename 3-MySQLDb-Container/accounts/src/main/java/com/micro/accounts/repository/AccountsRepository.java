package com.micro.accounts.repository;

import com.micro.accounts.entity.Accounts;
import com.micro.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional //yasana herhangi bir problemde yaptığı değişikliği geri aldırır.
    @Modifying //spring data jpa çatısına bu yöntemin verileri değiştireceğini söyler
    void deleteByCustomerId(Long customerId);
}
