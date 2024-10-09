package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends ListCrudRepository<User,Long> {


    List<User> findByRoleList_RoleName(String roleName);
    Optional<User> findByUsername(String username);


}