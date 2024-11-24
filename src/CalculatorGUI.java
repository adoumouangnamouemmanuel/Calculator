import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private CalculatorEngine engine;
    private JPanel buttonPanel;
    private Mode currentMode;

    public CalculatorGUI() {
        engine = new CalculatorEngine();
        currentMode = Mode.DEGREE;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 620);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(243, 243, 243));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(243, 243, 243));

        JPanel topPanel = new JPanel(new BorderLayout(0, 10));
        topPanel.setBackground(new Color(243, 243, 243));

        JLabel titleLabel = new JLabel("Scientific");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Segoe UI", Font.BOLD, 48));
        display.setBorder(new EmptyBorder(10, 10, 10, 10));
        display.setBackground(new Color(243, 243, 243));
        display.setPreferredSize(new Dimension(350, 100));
        topPanel.add(display, BorderLayout.CENTER);

        JPanel memoryPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        memoryPanel.setBackground(new Color(243, 243, 243));
        String[] memoryButtons = {"MC", "MR", "M+", "M-", "MS"};
        for (String label : memoryButtons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            memoryPanel.add(button);
        }
        topPanel.add(memoryPanel, BorderLayout.SOUTH);

        buttonPanel = new JPanel(new GridLayout(7, 5, 5, 5));
        buttonPanel.setBackground(new Color(243, 243, 243));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        addButtons();

        setLocationRelativeTo(null);
    }

    private void addButtons() {
        String[] buttonLabels = {
                "2nd", "π", "e", "CE", "←",
                "x²", "1/x", "|x|", "exp", "mod",
                "√", "(", ")", "n!", "÷",
                "xʸ", "7", "8", "9", "×",
                "10ˣ", "4", "5", "6", "-",
                "log", "1", "2", "3", "+",
                "ln", "±", "0", ".", "="
        };

        for (String label : buttonLabels) {
            CalculatorButton button = new CalculatorButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "=":
                calculateResult();
                break;
            case "CE":
                clearDisplay();
                break;
            case "←":
                backspace();
                break;
            case "±":
                negateNumber();
                break;
            case "x²":
                updateDisplay(" ^ 2 ");
                break;
            case "xʸ":
                updateDisplay(" ^ ");
                break;
            case "10ˣ":
                updateDisplay("x 10^");
                break;
            case "π":
                updateDisplay("π");
                break;
            case "e":
                updateDisplay(String.valueOf(Math.E));
                break;
            case "log":
            case "ln":
            case "sin":
            case "cos":
            case "tan":
            case "√":
            case "|x|":
            case "n!":
                updateDisplay(command + "( ");
                break;
            case "×":
            case "÷":
            case "+":
            case "-":
            case "mod":
                updateDisplay(" " + command + " ");
                break;
            default:
                updateDisplay(command);
                break;
        }
    }

    private void calculateResult() {
        try {
            String result = engine.calculate(display.getText(), currentMode);
            display.setText(result);
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private void clearDisplay() {
        display.setText("0");
    }

    private void backspace() {
        String currentText = display.getText();
        if (!currentText.isEmpty() && !currentText.equals("0")) {
            display.setText(currentText.substring(0, currentText.length() - 1));
            if (display.getText().isEmpty()) {
                display.setText("0");
            }
        }
    }

    private void negateNumber() {
        String currentText = display.getText();
        if (!currentText.isEmpty() && !currentText.equals("0")) {
            if (currentText.startsWith("-")) {
                display.setText(currentText.substring(1));
            } else {
                display.setText("-" + currentText);
            }
        }
    }

    private void updateDisplay(String value) {
        if (display.getText().equals("0") && !value.equals(".")) {
            display.setText(value);
        } else {
            display.setText(display.getText() + value);
        }
    }
}

