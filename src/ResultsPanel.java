import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ResultsPanel extends ElementInListPanel {
    
    private final JButton saveRestButton = new JButton("<html>Save<br>Restaurant<html>");

    public ResultsPanel(SavedRestaurantsScreen otherScreen) {
        super();

        saveRestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                otherScreen.addRestaurant();
                JOptionPane.showMessageDialog(null, "That restaurant has been added!");
                saveRestButton.setEnabled(false);
            }
        });

        addElements();
    }

    @Override
    protected void addElements() {
        add(new JLabel("<html>Main Food<br>Rating:<html>"));
        add(new JLabel("<html>Overall<br>Rating:<html>"));
        add(saveRestButton);
    }

}