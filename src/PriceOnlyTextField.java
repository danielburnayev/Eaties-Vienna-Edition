import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PriceOnlyTextField extends SpecialTextField {
    
    int periodIndex = -1;
    int numsAfterPeriod;

    public PriceOnlyTextField(String emptyText) {
        super(emptyText);
    }

    @Override
    public void startTextField() {
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {
                actualText = getText();
                //if (numsAfterPeriod < 2) {
                    if ((e.getKeyChar() > 47 && e.getKeyChar() < 58) || e.getKeyChar() == '.') {
                        actualText += e.getKeyChar();
                    }
                //}
            }
        });
    }

}