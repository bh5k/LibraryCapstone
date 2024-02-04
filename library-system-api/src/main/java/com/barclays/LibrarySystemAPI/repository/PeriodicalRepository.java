package com.barclays.LibrarySystemAPI.repository;


import com.barclays.LibrarySystemAPI.model.Periodical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodicalRepository  extends CrudRepository<Periodical, Long> {

    @Query("SELECT new Periodical (p.id, p.periodicalName, p.publicationDate,p.type) FROM Periodical  p WHERE p.periodicalName LIKE %:periodicalName%")
    List<Periodical> searchByTitle(String periodicalName );

}
