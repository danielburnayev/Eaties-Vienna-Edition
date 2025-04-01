import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public abstract class Screen {
    
    public static final JFrame frame = new JFrame("Eaties: Vienna Edition");
    protected Container cp;
    protected GridBagLayout layout;
    protected GridBagConstraints c;

    public Screen() {
        frame.setSize(500,600);
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
    protected void setDefaultGridbagLayout() {
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
    }

    protected void allowMultiplePerRow(boolean allow) {
        c.gridwidth = (allow) ? 1 : GridBagConstraints.REMAINDER;
    }

    protected void setUpComponent(Component comp) {
        layout.setConstraints(comp, c);
        cp.add(comp);
    }

    public static void refreshFrameVisuals() {
        frame.revalidate();
        frame.repaint();
    }

    protected abstract void organizeCompoments();

    public static void main(String[] args) {
        Screen runner = new FilterScreen();
    }
}
