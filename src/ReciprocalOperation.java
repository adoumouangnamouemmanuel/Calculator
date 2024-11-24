import java.util.Stack;

public class ReciprocalOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        if (a == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return 1 / a;
    }
}

