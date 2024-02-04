package com.barclays.LibrarySystemAPI.repository;

import com.barclays.LibrarySystemAPI.model.ReservedItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends CrudRepository<ReservedItem,Long> {
}
