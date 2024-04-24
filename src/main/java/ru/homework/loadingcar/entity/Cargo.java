package ru.homework.loadingcar.entity;

import lombok.Builder;
import ru.homework.loadingcar.type.AlgorithmType;

@Builder
public record Cargo(int[] size, int number) {
}
