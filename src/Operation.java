import java.util.Stack;

@FunctionalInterface
public interface Operation {
    double perform(Stack<Double> numbers, Mode mode);
}

