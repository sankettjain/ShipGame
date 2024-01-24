package com.example.ship;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Ship {

    private String id;
    private Integer size;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private List<String> area = new ArrayList<>();

    public Ship(String id, Integer size, Integer xCoordinate, Integer yCoordinate) {
        this.id = id;
        this.size = size;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void area() {
        int actualsSize = size / 2;
        area.add((xCoordinate + actualsSize) + "," + yCoordinate);
        area.add((xCoordinate - actualsSize) + "," + yCoordinate);
        area.add(xCoordinate + "," + (yCoordinate + actualsSize));
        area.add(xCoordinate + "," + (yCoordinate - actualsSize));

    }

}
