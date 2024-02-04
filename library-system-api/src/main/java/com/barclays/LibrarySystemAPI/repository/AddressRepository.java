package com.barclays.LibrarySystemAPI.repository;

import com.barclays.LibrarySystemAPI.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository  extends CrudRepository<Address, Long > {
     List<Address> findAll();
}
