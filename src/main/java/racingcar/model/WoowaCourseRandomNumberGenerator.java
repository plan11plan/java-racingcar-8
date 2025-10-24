package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;

public class WoowaCourseRandomNumberGenerator implements RandomNumberGenerator {
    @Override
    public int pickNumberInRange(int startInclusive, int endInclusive) {
        return Randoms.pickNumberInRange(startInclusive, endInclusive);
    }
}
