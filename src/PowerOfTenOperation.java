import java.util.Stack;

public class PowerOfTenOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        return Math.pow(10, a);
    }
}

