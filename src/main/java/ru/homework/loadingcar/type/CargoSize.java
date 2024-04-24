package ru.homework.loadingcar.type;

import lombok.Getter;

import java.util.stream.Stream;

/*
1

22

333

4444

55555

666
666

777
7777

8888
8888

999
999
999
 */
@Getter
public enum CargoSize {
    ONE("1", new int[]{1},  1),
    TWO("22", new int[]{2},  2),
    THREE("333", new int[]{3},  3),
    FOUR("4444", new int[]{4},  4),
    FIVE("55555", new int[]{5},  5),
    SIX("666\r\n666", new int[]{3,3}, 6),
    SEVEN("777\r\n7777", new int[]{4,3}, 7),
    EIGHT("8888\r\n8888", new int[]{4,4}, 8),
    NINE("999\r\n999\r\n999", new int[]{3,3,3}, 9);
    private final String pattern;
    private final int[] size;
    private final int number;

    CargoSize(String pattern, int[] size, int number) {
        this.pattern = pattern;
        this.size = size;
        this.number = number;
    }

    public static Stream<CargoSize> stream() {
        return Stream.of(CargoSize.values());
    }
}
