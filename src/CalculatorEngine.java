import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class CalculatorEngine {
    private Map<String, Operation> operations;

    public CalculatorEngine() {
        initializeOperations();
    }

    private void initializeOperations() {
        operations = new HashMap<>();
        operations.put("+", (numbers, mode) -> {
            double b = numbers.pop();
            double a = numbers.pop();
            return a + b;
        });
        operations.put("-", (numbers, mode) -> {
            double b = numbers.pop();
            double a = numbers.pop();
            return a - b;
        });
        operations.put("×", (numbers, mode) -> {
            double b = numbers.pop();
            double a = numbers.pop();
            return a * b;
        });
        operations.put("÷", (numbers, mode) -> {
            double b = numbers.pop();
            double a = numbers.pop();
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });
        operations.put("mod", (numbers, mode) -> {
            double b = numbers.pop();
            double a = numbers.pop();
            if (b == 0) throw new ArithmeticException("Modulo by zero");
            return a % b;
        });
        operations.put("^", (numbers, mode) -> {
            double b = numbers.pop();
            double a = numbers.pop();
            return Math.pow(a, b);
        });
        operations.put("√", (numbers, mode) -> Math.sqrt(numbers.pop()));
        operations.put("log", (numbers, mode) -> Math.log10(numbers.pop()));
        operations.put("ln", (numbers, mode) -> Math.log(numbers.pop()));
        operations.put("sin", (numbers, mode) -> {
            double angle = numbers.pop();
            return Math.sin(mode == Mode.DEGREE ? Math.toRadians(angle) : angle);
        });
        operations.put("cos", (numbers, mode) -> {
            double angle = numbers.pop();
            return Math.cos(mode == Mode.DEGREE ? Math.toRadians(angle) : angle);
        });
        operations.put("tan", (numbers, mode) -> {
            double angle = numbers.pop();
            return Math.tan(mode == Mode.DEGREE ? Math.toRadians(angle) : angle);
        });
        operations.put("exp", (numbers, mode) -> Math.exp(numbers.pop()));
        operations.put("1/x", (numbers, mode) -> 1 / numbers.pop());
        operations.put("|x|", (numbers, mode) -> Math.abs(numbers.pop()));
        operations.put("n!", (numbers, mode) -> {
            int n = (int) Math.round(numbers.pop());
            if (n < 0) throw new ArithmeticException("Factorial of negative number");
            long result = 1;
            for (int i = 2; i <= n; i++) result *= i;
            return (double) result;
        });
    }

    public String calculate(String expression, Mode mode) {
        try {
            Stack<Double> numbers = new Stack<>();
            Stack<String> operators = new Stack<>();

            String[] tokens = expression.split("\\s+");

            for (String token : tokens) {
                if (isNumber(token)) {
                    numbers.push(Double.parseDouble(token));
                } else if (token.equals("(")) {
                    operators.push(token);
                } else if (token.equals(")")) {
                    while (!operators.isEmpty() && !operators.peek().equals("(")) {
                        processOperation(numbers, operators, mode);
                    }
                    if (!operators.isEmpty() && operators.peek().equals("(")) {
                        operators.pop();
                    }
                } else if (isOperator(token)) {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                        processOperation(numbers, operators, mode);
                    }
                    operators.push(token);
                }
            }

            while (!operators.isEmpty()) {
                processOperation(numbers, operators, mode);
            }

            return formatResult(numbers.pop());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    private void processOperation(Stack<Double> numbers, Stack<String> operators, Mode mode) {
        String operator = operators.pop();
        Operation operation = operations.get(operator);
        if (operation != null) {
            double result = operation.perform(numbers, mode);
            numbers.push(result);
        }
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return operations.containsKey(token);
    }

    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "×":
            case "÷":
            case "mod":
                return 2;
            case "^":
            case "√":
            case "log":
            case "ln":
            case "sin":
            case "cos":
            case "tan":
            case "exp":
            case "1/x":
            case "|x|":
            case "n!":
                return 3;
            default:
                return 0;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.8f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
}

