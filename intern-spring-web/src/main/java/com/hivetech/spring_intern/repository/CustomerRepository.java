package com.hivetech.spring_intern.repository;

import com.hivetech.spring_intern.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.phoneNumber) LIKE %?1%")
    List<Customer> search(String keyword);
}
