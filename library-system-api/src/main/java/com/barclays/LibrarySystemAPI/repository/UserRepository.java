package com.barclays.LibrarySystemAPI.repository;

import com.barclays.LibrarySystemAPI.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT new User(u.id, u.name, u.address,u.phoneNumber, u.email) FROM User u WHERE u.name LIKE %:name%")
    List<User> searchByName(@Param("name") String name);


}
