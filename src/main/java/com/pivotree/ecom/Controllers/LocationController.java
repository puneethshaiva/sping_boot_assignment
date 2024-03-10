package com.pivotree.ecom.Controllers;

import com.pivotree.ecom.Dto.LocationDto;
import com.pivotree.ecom.Model.Location;
import com.pivotree.ecom.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService service;
    @PostMapping("/create")
    ResponseEntity<Object> createLocation(@RequestBody LocationDto loc){
        Location newLoc=service.createLoc(loc);
        return new ResponseEntity<>(newLoc, HttpStatusCode.valueOf(201));
    }
    @GetMapping("/getAll")
    ResponseEntity<Object> getAllLoc(){
        List<Location> loc=service.getAllLocs();
        return new ResponseEntity<>(loc,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getById/{id}")
    ResponseEntity<Object> getLocById(@PathVariable String id){
        Location loc=service.getloc(id);

        if (Objects.isNull(loc)){
            return new ResponseEntity<>("location not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>(loc,HttpStatusCode.valueOf(200));
        }
    }

    @PutMapping("/updateById/{id}")
    ResponseEntity<Object> updateLocById(@PathVariable String id, @RequestBody LocationDto loc){
        Optional<Location> fetched=service.update(id,loc);
        if (fetched.isEmpty()){
            return new ResponseEntity<>("Location not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>("updated location id: "+id,HttpStatusCode.valueOf(200));
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> deleteByid(@PathVariable String id){
        service.deleteByid(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
