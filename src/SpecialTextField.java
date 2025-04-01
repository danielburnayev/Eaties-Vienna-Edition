import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.ContainerEvent;
import javax.accessibility.AccessibleText;
import javax.swing.JTextField;

public class SpecialTextField extends JTextField {
    protected String emptyText;
    protected String actualText = "";

    public SpecialTextField(String emptyText) {
        this.emptyText = emptyText;
        setForeground(Color.GRAY);
        setText(emptyText);

        addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setForeground(Color.BLACK);
                System.out.println(actualText);
                setText(actualText);
            }

            public void focusLost(FocusEvent e) {
                if (actualText.length() == 0) {
                    setForeground(Color.GRAY);
                    setText(emptyText);
                }
            }
        });
    }

    //in order to distinguish this between its subclass
    //you also need to run this before you do anything with it
    public void startTextField() {
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {
                actualText = getText();
                if ((e.getKeyChar() > 64 && e.getKeyChar() < 91) || 
                    (e.getKeyChar() > 96 && e.getKeyChar() < 123) || 
                    (e.getKeyChar() > 47 && e.getKeyChar() < 58) || 
                    (e.getKeyChar() == ' ') || (e.getKeyChar() == ',') || (e.getKeyChar() == '\'') || 
                    (e.getKeyChar() == 'Ä') || (e.getKeyChar() == 'Ö') || (e.getKeyChar() == 'Ü') || 
                    (e.getKeyChar() == 'ä') || (e.getKeyChar() == 'ö') || (e.getKeyChar() == 'ü') || 
                    (e.getKeyChar() == 'ß')) {
                    actualText += e.getKeyChar();
                }
            }
        });
    }

    public String getActualText() {return actualText;}

}