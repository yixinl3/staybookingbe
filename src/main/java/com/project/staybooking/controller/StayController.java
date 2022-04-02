package com.project.staybooking.controller;

import com.project.staybooking.model.Stay;
import com.project.staybooking.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StayController {
    private StayService stayService;


    @Autowired
    public StayController(StayService stayService) {
        this.stayService = stayService;
    }

    @GetMapping(value = "/stays")
    public List<Stay> listStays(@RequestParam(name = "host") String hostName) {
        return stayService.listByUser(hostName);
    }

    @GetMapping(value = "/stays/id")
    public Stay getStay(@RequestParam(name = "stay_id")Long stayId, @RequestParam(name = "host") String hostName) {
        return stayService.findByIdAndHost(stayId, hostName);

    }

    @PostMapping(value = "/stays")
    public void addStay(@RequestBody Stay stay) {
        stayService.add(stay);

    }

    @DeleteMapping(value = "/stays")
    public void deleteStay(@RequestParam(name = "stay_id")Long stayId, @RequestParam(name = "host") String hostName) {
        stayService.delete(stayId, hostName);
    }
}
