import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SavedRestaurantsScreen extends SubScreen {
    
    private final JButton addPlaceButton = new JButton("Add Place");

    public SavedRestaurantsScreen(FilterScreen mainScreen) {
        super(mainScreen);
        
        headerLabel = new JLabel("Saved Restaurants", SwingConstants.CENTER);
        headerLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 45));

        addPlaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                addRestaurant();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                RestaurantFileScraper.screenToFile(scrollPanel);
                moveToMainScreen();
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
        
            @Override
            public void windowClosing(WindowEvent e) {RestaurantFileScraper.screenToFile(scrollPanel);}
        
            @Override
            public void windowClosed(WindowEvent e) {}
        
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
        frame.setContentPane(cp);
        
        setDefaultGridbagLayout();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.0;

        allowMultiplePerRow(true);
        setUpComponent(exitButton);
        allowMultiplePerRow(false);
        setUpComponent(headerLabel);

        scrollPanel.add(addPlaceButton);
        setUpComponent(scrollList);
        scrollList.setPreferredSize(new Dimension(500, 500));
        scrollList.setMinimumSize(new Dimension(500, 500));
    }

    public void addRestaurant() {
        GridLayout theLayout = ((GridLayout)(scrollPanel.getLayout()));
        theLayout.setRows(theLayout.getRows() + 1);
        if (scrollPanel.getComponentCount() > 0) {scrollPanel.remove(scrollPanel.getComponentCount() - 1);}
        scrollPanel.add(new SavedRestaurantPanel(scrollPanel));
        scrollPanel.add(addPlaceButton);
        refreshFrameVisuals();
    }

    public void addRestaurant(String placeText, String addressText, String[] notableFood) {
        GridLayout theLayout = ((GridLayout)(scrollPanel.getLayout()));
        theLayout.setRows(theLayout.getRows() + 1);
        if (scrollPanel.getComponentCount() > 0) {scrollPanel.remove(scrollPanel.getComponentCount() - 1);}
        scrollPanel.add(new SavedRestaurantPanel(scrollPanel, placeText, addressText, notableFood));
        scrollPanel.add(addPlaceButton);
        refreshFrameVisuals();
    }
}