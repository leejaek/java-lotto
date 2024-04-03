package lotto.domain;

import java.util.Arrays;
import java.util.Map;

public enum Rewards {
    FOURTH(3, 5000),
    THIRD(4, 50000),
    SECOND(5, 1500000),
    FIRST(6, 2000000000);

    private final int matches;
    private final int prize;

    Rewards(int matches, int prize) {
        this.matches = matches;
        this.prize = prize;
    }

    public int getPrize() {
        return this.prize;
    }

    public static Integer getWholePrize(Map<Integer, Integer> result) {
        int wholePrize = 0;

        for (Integer matches: result.keySet()) {
            wholePrize += getRewards(matches).getPrize() * result.get(matches);
        }

        return wholePrize;
    }

    public static Rewards getRewards(int matches) {
        return Arrays.stream(values())
                .filter(m -> m.matches == matches)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("상금 범위가 아닙니다."));
    }
}