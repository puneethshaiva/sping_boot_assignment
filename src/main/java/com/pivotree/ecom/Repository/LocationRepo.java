package com.pivotree.ecom.Repository;

import com.pivotree.ecom.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location,Long> {

    @Query(value = "SELECT * FROM location l WHERE l.location_reference=:location",nativeQuery = true)
    Location findByReference(String location);
}
