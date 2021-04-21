package com.harshalmayee.controller;

import com.harshalmayee.dto.CovidAPIData;
import com.harshalmayee.dto.StateData;
import com.harshalmayee.dto.SummaryData;
import com.harshalmayee.exception.ResourceNotFoundException;
import com.harshalmayee.service.Covid19DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Covid19StatsController {

    @Autowired
    private Covid19DataProvider covid19DataProvider;

    @GetMapping(value = "/getCovid19StatsForAllStates",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CovidAPIData> getCovid19StatsForAllStates() throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(covid19DataProvider.getCovid19StatsForAllStates());
    }

    @GetMapping(value = "/getCovid19StatsByState/{state}")
    public ResponseEntity<StateData> getCovid19StatsByState(@PathVariable(value = "state") final String state) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(covid19DataProvider.getCovid19StatsByState(state));
    }

    @GetMapping(value = "/getSummaryData",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SummaryData> getSummaryData() throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(covid19DataProvider.getSummaryData());
    }

}
