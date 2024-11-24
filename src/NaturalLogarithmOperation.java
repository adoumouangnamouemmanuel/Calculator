import java.util.Stack;

public class NaturalLogarithmOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        if (a <= 0) {
            throw new ArithmeticException("Invalid input for logarithm");
        }
        return Math.log(a);
    }
}

