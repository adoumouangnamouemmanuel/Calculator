import java.util.Stack;

public class DivideOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double b = numbers.pop();
        double a = numbers.pop();
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}

