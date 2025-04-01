import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ResultsScreen extends SubScreen {
    
    public ResultsScreen(FilterScreen mainScreen) {
        super(mainScreen);
        
        headerLabel = new JLabel("Your Results:", SwingConstants.CENTER);
        headerLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 45));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                scrollPanel.removeAll();
                moveToMainScreen();
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
        
            @Override
            public void windowClosing(WindowEvent e) {}
        
            @Override
            public void windowClosed(WindowEvent e) {RestaurantFileScraper.screenToFile(scrollPanel);}
        
            @Override
            public void windowIconified(WindowEvent e) {}
        
            @Override
            public void windowDeiconified(WindowEvent e) {}
        
            @Override
            public void windowActivated(WindowEvent e) {}
        
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    protected void organizeCompoments() {
        //frame.setSize(705,600);
        frame.setContentPane(cp);
        
        setDefaultGridbagLayout();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.0;

        allowMultiplePerRow(true);
        setUpComponent(exitButton);
        allowMultiplePerRow(false);
        setUpComponent(headerLabel);

        scrollPanel.add(new ResultsPanel(mainScreen.getSavedRestaurants()));
        setUpComponent(scrollList);
        scrollList.setPreferredSize(new Dimension(500, 500));
        scrollList.setMinimumSize(new Dimension(500, 500));
    }

}