import java.util.Stack;

public class ExponentialOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        return Math.exp(a);
    }
}