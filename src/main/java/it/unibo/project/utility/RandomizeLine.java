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
     * return always the same randomized element in the same line.
     * 
     * @param <X>   type of element
     * @param elems collection from which to get a random element
     * @param line  line on which the element is situated
     * @return a random element, which is always the same if the line is the same
     */
    public <X> X getLineRandomElement(final List<X> elems, final int line) {
        calculateLineRandomValue(line);
        return elems.get(lineRandomValue.get(line) % elems.size());
    }

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
