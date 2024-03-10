package com.pivotree.ecom.Services;

import com.pivotree.ecom.Dto.LocationDto;
import com.pivotree.ecom.Model.Dimensions;
import com.pivotree.ecom.Model.Location;
import com.pivotree.ecom.Model.Product;
import com.pivotree.ecom.Repository.LocationDBmanager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationDBmanager dbManager;

    @Transactional(rollbackOn = Exception.class,value = Transactional.TxType.REQUIRED)
    public Location createLoc(LocationDto loc) {
        Location newLoc=new Location(loc.getLocationReference(), loc.getLocationName(), loc.getTotalCapacity(), loc.getTotalCapacity());
        dbManager.save(newLoc);
        return newLoc;
    }

    public List<Location> getAllLocs() {
        return dbManager.findAll();
    }

    public Location getloc(String id) {
        return dbManager.getById(Long.parseLong(id));
    }

    public void deleteByid(String id) {
        dbManager.delete(Long.parseLong(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public Optional<Location> update(String id, LocationDto loc) {
        Location fetched=dbManager.getById(Long.parseLong(id));
        if (Objects.isNull(fetched)){
            return Optional.empty();
        }else{
            long newRem=fetched.getRemainingCapacity()+(loc.getTotalCapacity() - fetched.getTotalCapacity());
            if (newRem<0){
                throw new RuntimeException();
            }
            fetched.setRemainingCapacity(newRem);
            fetched.setLocationReference(loc.getLocationReference());
            fetched.setTotalCapacity(loc.getTotalCapacity());
            fetched.setLocationName(loc.getLocationName());
            //

            //
            dbManager.save(fetched);
        }
        return Optional.of(fetched);
    }
}
