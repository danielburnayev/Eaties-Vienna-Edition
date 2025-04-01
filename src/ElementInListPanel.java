import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Line2D;

public abstract class ElementInListPanel extends JPanel {

    
    /*protected final JPanel placePanel = new JPanel(new GridLayout(2, 1));
    protected final JPanel addressPanel = new JPanel(new GridLayout(2, 1));
    protected final SpecialTextField placeField = new SpecialTextField("Name of the place");
    protected final SpecialTextField addressField = new SpecialTextField("Address of the place");
    */

    protected JLabel place;
    protected JLabel address;

    public ElementInListPanel() {
        super(new GridLayout());
        place = new JLabel("Place: ");
        address = new JLabel("Address: ");
    
        /*placeField.setEditable(false);
        addressField.setEditable(false);

        placePanel.add(new JLabel("Place"));
        placePanel.add(placeField);
        addressPanel.add(new JLabel("Address"));
        addressPanel.add(addressField);

        add(placePanel);
        add(addressPanel);
        */
        add(place);
        add(address);
        
    }

    public ElementInListPanel(String placeText, String addressText) {
        super(new GridLayout());
        place = new JLabel(placeText);
        address = new JLabel(addressText);
    
        /*placeField.setEditable(false);
        addressField.setEditable(false);

        placePanel.add(new JLabel("Place"));
        placePanel.add(placeField);
        addressPanel.add(new JLabel("Address"));
        addressPanel.add(addressField);

        add(placePanel);
        add(addressPanel);
        */
        
        add(place);
        add(address);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(0, 0, 500, 0));
    }

    protected abstract void addElements();

    public String getPlaceText() {return place.getText();}

    public String getAddressText() {return address.getText();}

}