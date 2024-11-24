import java.util.Stack;

public class CosineOperation implements Operation {
    @Override
    public double perform(Stack<Double> numbers, Mode mode) {
        double a = numbers.pop();
        double radians = convertToRadians(a, mode);
        return Math.cos(radians);
    }

    private double convertToRadians(double angle, Mode mode) {
        switch (mode) {
            case DEGREE:
                return Math.toRadians(angle);
            case GRADIAN:
                return angle * Math.PI / 200;
            default:
                return angle;
        }
    }

}