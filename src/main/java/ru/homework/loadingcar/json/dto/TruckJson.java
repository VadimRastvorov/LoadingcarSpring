package ru.homework.loadingcar.json.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.util.List;

@Getter
@JsonAutoDetect
public class TruckJson {
    private List<Integer> cargo;

    public TruckJson() {
        super();
    }

    public TruckJson(List<Integer> cargo) {
        this.cargo = cargo;
    }
}
