package com.hivetech.spring_intern.service;

import com.hivetech.spring_intern.entity.Customer;
import com.hivetech.spring_intern.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(String keyword) {
        if (keyword != null) {
            return customerRepository.search(keyword);
        } else {
            return customerRepository.findAll();
        }
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findCustomer(int id) {
        return customerRepository.findById(id);
    }

//    public Page<Customer> findPaginated(int pageNo, int pageSize) {
//        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
//        return this.customerRepository.findAll(pageable);
//    }
}
