package it.academy.rest;

import it.academy.service.VisitorCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitorCountRestService {

    @Autowired
    private VisitorCountService visitorCountService;

    @PutMapping("/visitor_count")
    public Integer updateVisitorCount() {
        return visitorCountService.updateCount();
    }

    @GetMapping("/visitor_count")
    public Integer readVisitorCount() {
        return visitorCountService.readCount();
    }

}
