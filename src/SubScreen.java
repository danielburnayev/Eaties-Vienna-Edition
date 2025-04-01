import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public abstract class SubScreen extends Screen {
    
    protected FilterScreen mainScreen;
    protected JLabel headerLabel;
    protected final JButton exitButton = new JButton("<html>Filter<br>Screen</html>");
    protected final JPanel scrollPanel = new JPanel(new GridLayout(1, 1));
    protected final JScrollPane scrollList = new JScrollPane(scrollPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public SubScreen(FilterScreen mainScreen) {
        super();

        this.mainScreen = mainScreen;

        cp = new Container();
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        cp.setLayout(layout);
    }

    protected void moveToMainScreen() {
        cp.removeAll();
        refreshFrameVisuals();
        mainScreen.organizeCompoments();
    }

    public JPanel getScrollPanel() {return scrollPanel;}



}
