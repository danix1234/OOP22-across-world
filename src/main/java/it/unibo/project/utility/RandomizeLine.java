package it.unibo.project.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizeLine {
    private final Map<Integer, Integer> lineRandomValue = new HashMap<>();
    private final Random random = new Random();

    private void calculateLineRandomValue(final int line) {
        if (!lineRandomValue.containsKey(line)) {
            lineRandomValue.put(line, random.nextInt(0, Integer.MAX_VALUE));
        }
    }

    /**
     * calculate a random element of the collect dependent on the line parameter.
     * 
     * @param <X>
     * @param elems
     * @param line
     * @return a random element of the collection
     */
    public <X> X getLineRandomElement(final List<X> elems, final int line) {
        calculateLineRandomValue(line);
        return elems.get(lineRandomValue.get(line) % elems.size());
    }

    /**
     * generate a random value dependent on the line parameter.
     * 
     * @param lowerBound
     * @param upperBound
     * @param line
     * @return a random number in {@code [lowerBound, upperBound]}
     */
    public double getLineRandomNumber(final double lowerBound, final double upperBound, final int line) {
        calculateLineRandomValue(line);
        if (lowerBound > upperBound) {
            return new Random(this.lineRandomValue.get(line)).nextDouble(upperBound, lowerBound);
        }
        if (lowerBound == upperBound) {
            return lowerBound;
        }
        return new Random(this.lineRandomValue.get(line)).nextDouble(lowerBound, upperBound);
    }
}
