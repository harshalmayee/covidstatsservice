package com.harshalmayee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SummaryData extends BaseData {

    private int total;
    private int confirmedButLocationUnidentified;
    private ZonedDateTime lastUpdatedTime;

}
