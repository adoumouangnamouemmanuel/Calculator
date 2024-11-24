import java.util.Stack;

public class FactorialOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        int a = numbers.pop().intValue();
        if (a < 0) {
            throw new ArithmeticException("Factorial of negative number");
        }
        return factorial(a);
    }

    private double factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
}

