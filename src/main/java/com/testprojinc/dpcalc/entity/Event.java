package com.testprojinc.dpcalc.entity;

import com.testprojinc.dpcalc.dto.EventType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Event")
public class Event {

    @Id
    private Long id;

    private String name;

    private Double A;
    private Double B;
    private Double C;

    private EventType type;

}
