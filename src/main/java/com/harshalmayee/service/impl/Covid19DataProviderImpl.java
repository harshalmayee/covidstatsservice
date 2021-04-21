package com.harshalmayee.service.impl;

import com.harshalmayee.constants.Covid19Constants;
import com.harshalmayee.dto.CovidAPIData;
import com.harshalmayee.dto.StateData;
import com.harshalmayee.dto.SummaryData;
import com.harshalmayee.exception.ResourceNotFoundException;
import com.harshalmayee.service.Covid19DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Service
public class Covid19DataProviderImpl implements Covid19DataProvider {

    @Autowired
    private RestTemplate restTemplate;

    public CovidAPIData getCovid19StatsForAllStates() throws ResourceNotFoundException {
        CovidAPIData covidAPIData=restTemplate.getForObject(Covid19Constants.COVID19_API_URL,CovidAPIData.class);
        if(covidAPIData.getData().getRegional().length==0){
            throw new ResourceNotFoundException("No Data Found");
        }
        covidAPIData.getData().getSummary().setLastUpdatedTime(covidAPIData.getLastRefreshed());
        return covidAPIData;
    }

    public StateData getCovid19StatsByState(String state) throws ResourceNotFoundException {
        CovidAPIData covidAPIData=restTemplate.getForObject(Covid19Constants.COVID19_API_URL,CovidAPIData.class);
        if(covidAPIData.getData().getRegional().length==0){
            throw new ResourceNotFoundException("No Data Found");
        }
        return Arrays.stream(covidAPIData.getData().getRegional())
                .filter(e->e.getLoc().equalsIgnoreCase(state))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Data Not Found For :"+state));
    }

    public SummaryData getSummaryData() throws ResourceNotFoundException {
        CovidAPIData covidAPIData=restTemplate.getForObject(Covid19Constants.COVID19_API_URL,CovidAPIData.class);
        if(covidAPIData.getData().getSummary().getTotal()==0){
            throw new ResourceNotFoundException("No Data Found");
        }
        SummaryData summaryData=covidAPIData.getData().getSummary();
        summaryData.setLastUpdatedTime(covidAPIData.getLastRefreshed());
        return summaryData;
    }
}
