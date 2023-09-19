import javax.swing.*;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import java.awt.*;

public class CheckBoxUI extends BasicCheckBoxUI {
    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        if (model.isSelected()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = button.getHeight(); // rozmiar zaznaczonego CheckBox

            // Kolor tła zaznaczonego CheckBox
            g2.setColor(Color.BLUE);
            g2.fillRect(0, 0, size, size);

            g2.dispose();
        }
    }

    @Override
    protected void paintIcon(Graphics g, AbstractButton b, Rectangle iconRect) {
        // Nie rysujemy ikony (znacznika)
    }
}