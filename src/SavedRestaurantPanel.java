import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.lang.IllegalArgumentException;

public class SavedRestaurantPanel extends ElementInListPanel {
    
    private static int totalPanels = 0;
    private int assignedIndex;
    private JPanel panelIn;
    private final JLabel notableFoodsLabel = new JLabel("Notable Foods: ");
    private final JButton addFoodButton = new JButton("<html>Add<br>Food<html>");
    private final JButton removeButton = new JButton("Remove");
    private final JPanel foodPanel = new JPanel(/*new GridLayout(1, 1)*/ new GridBagLayout());
    private final GridBagConstraints foodConstraints = new GridBagConstraints();

    public SavedRestaurantPanel(JPanel panel) {
        super();

        // placeField.setEditable(true);
        // addressField.setEditable(true);

        panelIn = panel;

        assignedIndex = totalPanels;
        totalPanels++;

        panelSetup();
    }

    public SavedRestaurantPanel(JPanel panel, String placeText, String addressText, String[] notableFood) {
        super(placeText, addressText);

        // placeField.setEditable(true);
        // addressField.setEditable(true);

        panelIn = panel;

        assignedIndex = totalPanels;
        totalPanels++;

        panelSetup();

        for (String food : notableFood) {
            addFoodButton.doClick();
        }
    }

    public int getAssignedIndex() {return assignedIndex;}
    public void setAssignedIndex(int newIndex) throws IllegalArgumentException {
        if (newIndex < 0) {throw new IllegalArgumentException("newIndex must be greater than 0.");}
        else if (newIndex > panelIn.getComponentCount() - 2) {throw new IllegalArgumentException("newIndex must be less than " + (panelIn.getComponentCount() - 1) + ", the index of the last element.");}
        assignedIndex = newIndex;
    }

    @Override
    protected void addElements() {
        add(notableFoodsLabel);
        ((GridBagLayout)(foodPanel.getLayout())).setConstraints(addFoodButton, foodConstraints);
        foodPanel.add(addFoodButton);
        add(foodPanel);
        add(removeButton);
    }

    private void setDefaultGridbagLayout() {
        foodConstraints.weightx = 1.0;
        foodConstraints.weighty = 1.0;
        foodConstraints.gridwidth = 1;
        foodConstraints.fill = GridBagConstraints.BOTH;
        foodConstraints.anchor = GridBagConstraints.CENTER;
    }

    private void updateIndicies(int deletedIndex) {
        for (int i = deletedIndex; i < totalPanels; i++) {
            SavedRestaurantPanel elem = (SavedRestaurantPanel)(panelIn.getComponent(i));
            elem.setAssignedIndex(elem.getAssignedIndex() - 1);
        }
    }

    public String getNotableFoodsLabelText() {return notableFoodsLabel.getText();}

    public JPanel getFoodPanel() {return foodPanel;}

    private void panelSetup() {
        setDefaultGridbagLayout();

        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                GridBagLayout theLayout = ((GridBagLayout)(foodPanel.getLayout()));
                SpecialTextField foodField = new SpecialTextField("A notable food");
                JButton noButton = new JButton("No");
                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent a) {
                        foodPanel.remove(foodField);
                        foodPanel.remove(noButton);
                        Screen.refreshFrameVisuals();
                    }
                });

                foodPanel.remove(foodPanel.getComponentCount() - 1);
                theLayout.setConstraints(foodField, foodConstraints);
                foodPanel.add(foodField);

                foodConstraints.gridwidth = GridBagConstraints.REMAINDER;

                theLayout.setConstraints(noButton, foodConstraints);
                foodPanel.add(noButton);
                theLayout.setConstraints(addFoodButton, foodConstraints);
                foodPanel.add(addFoodButton);

                setDefaultGridbagLayout();

                Screen.refreshFrameVisuals();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                GridLayout theLayout = ((GridLayout)(panelIn.getLayout()));
                panelIn.remove(assignedIndex);
                theLayout.setRows(theLayout.getRows() - 1);

                totalPanels--;
                updateIndicies(assignedIndex);

                Screen.refreshFrameVisuals();
            }
        });

        addElements();
    }

}