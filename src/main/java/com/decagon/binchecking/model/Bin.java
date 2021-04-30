package com.decagon.binchecking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bin;
    private Integer checkCounter;
    private ZonedDateTime time;

    public Bin(Integer checkCounter, ZonedDateTime time) {
        this.checkCounter = checkCounter;
        this.time = time;
    }
    public Bin(String bin, Integer checkCounter, ZonedDateTime time) {
        this.bin = bin;
        this.checkCounter = checkCounter;
        this.time = time;
    }
}
