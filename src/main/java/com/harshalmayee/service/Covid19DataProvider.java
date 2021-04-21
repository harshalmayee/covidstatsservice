package com.harshalmayee.service;

import com.harshalmayee.dto.CovidAPIData;
import com.harshalmayee.dto.StateData;
import com.harshalmayee.dto.SummaryData;
import com.harshalmayee.exception.ResourceNotFoundException;

public interface Covid19DataProvider {
    CovidAPIData getCovid19StatsForAllStates() throws ResourceNotFoundException;
    StateData getCovid19StatsByState(String state) throws ResourceNotFoundException;
    SummaryData getSummaryData() throws ResourceNotFoundException;
}
