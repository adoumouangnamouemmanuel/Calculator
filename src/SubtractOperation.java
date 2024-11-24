import java.util.Stack;

public class SubtractOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double b = numbers.pop();
        double a = numbers.pop();
        return a - b;
    }
}
