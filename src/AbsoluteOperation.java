import java.util.Stack;

public class AbsoluteOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        return Math.abs(a);
    }
}

