import java.util.Stack;

public class SquareOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        return a * a;
    }
}

