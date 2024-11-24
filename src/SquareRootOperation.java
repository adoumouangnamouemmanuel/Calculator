import java.util.Stack;

public class SquareRootOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        if (a < 0) {
            throw new ArithmeticException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(a);
    }
}

