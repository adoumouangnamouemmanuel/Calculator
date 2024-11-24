import java.util.Stack;

public class PowerOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double b = numbers.pop();
        double a = numbers.pop();
        return Math.pow(a, b);
    }
}

