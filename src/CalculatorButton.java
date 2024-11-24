import javax.swing.*;
import java.awt.*;

public class CalculatorButton extends JButton {
    private static final int BUTTON_WIDTH = 70;
    private static final int BUTTON_HEIGHT = 30;

    public CalculatorButton(String text) {
        super(text);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setFocusPainted(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(new Color(230, 230, 230));
        } else if (getModel().isRollover()) {
            g.setColor(new Color(240, 240, 240));
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(230, 230, 230));
        g.drawRect(0, 0, getWidth() - 2, getHeight() - 2);
    }
}

