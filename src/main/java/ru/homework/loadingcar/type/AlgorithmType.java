package ru.homework.loadingcar.type;

import java.util.Arrays;
import java.util.Optional;

public enum AlgorithmType {
    ALGORITHM_V1("alg1"),
    ALGORITHM_V2("alg2"),
    ALGORITHM_V3("alg3");
    private final String algName;

    AlgorithmType(String algName) {
        this.algName = algName;
    }

    public static AlgorithmType get(String algName) {
        return Arrays.stream(AlgorithmType.values())
                .filter(algorithmType -> algorithmType.algName.equals(algName))
                .findFirst().get();
    }
}
