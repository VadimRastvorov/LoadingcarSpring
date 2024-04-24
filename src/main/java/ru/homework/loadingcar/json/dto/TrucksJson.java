package ru.homework.loadingcar.json.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.List;

@Getter
@JsonAutoDetect
public class TrucksJson {
    List<TruckJson> truckList;

    public TrucksJson() {
        super();
    }

    public TrucksJson(List<TruckJson> truckList) {
        this.truckList = truckList;
    }
}

