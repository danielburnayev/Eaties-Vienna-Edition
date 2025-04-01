import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FilterScreen extends Screen {

    private final JLabel craveLabel = new JLabel("What you\'re craving:", SwingConstants.CENTER);
    private final JLabel whereLabel = new JLabel("Where you at?", SwingConstants.CENTER);
    private final JLabel budgetLabel = new JLabel("Budget:", SwingConstants.CENTER);
    private final JLabel dashLabel = new JLabel("-", SwingConstants.CENTER);
    private final JLabel euroSign1 = new JLabel("€", SwingConstants.RIGHT);
    private final JLabel euroSign2 = new JLabel("€", SwingConstants.RIGHT);
    private final JLabel serviesLabel = new JLabel("Additional Services:", SwingConstants.CENTER);
    private final SpecialTextField craveField = new SpecialTextField("Food in Vienna you're craving");
    private final SpecialTextField whereField = new SpecialTextField("Where you are in Vienna");
    private final PriceOnlyTextField minField = new PriceOnlyTextField("Min euros (>= 0)");
    private final PriceOnlyTextField maxField = new PriceOnlyTextField("Max euros (>= 0)");
    private final Checkbox noBudCheck = new Checkbox("No Budget", false);
    // private final Checkbox disCheck = new Checkbox("Disability", false);
    // private final Checkbox serAnimCheck = new Checkbox("Service Animals", false);
    // private final Checkbox miliCheck = new Checkbox("Military Discount", false);
    // private final Checkbox vegetCheckbox = new Checkbox("Vegetarian", false);
    // private final Checkbox veganCheckbox = new Checkbox("Vegan", false);
    // private final Checkbox halalCheckbox = new Checkbox("Halal", false);
    // private final Checkbox kashCheckbox = new Checkbox("Kashrut", false);
    private final JButton resultsButton = new JButton("Give \'em to me!");
    private final JButton savedButton = new JButton("<html>Saved<br>Places</html>");
    private SavedRestaurantsScreen savedRestaurants = new SavedRestaurantsScreen(this);
    private ResultsScreen results = new ResultsScreen(this);

    public FilterScreen() {
        super();
        
        cp = new Container();
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        cp.setLayout(layout);

        craveLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));
        whereLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));
        budgetLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));
        dashLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 70));
        serviesLabel.setFont(new Font("ComicSansMS", Font.PLAIN, 30));
        euroSign1.setFont(new Font("ComicSansMS", Font.PLAIN, 55));
        euroSign2.setFont(new Font("ComicSansMS", Font.PLAIN, 55));

        craveField.startTextField();
        whereField.startTextField();
        minField.startTextField();
        maxField.startTextField();

        resultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                moveToScreen(results);
            }
        });

        savedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                moveToScreen(savedRestaurants);
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
        
            @Override
            public void windowClosing(WindowEvent e) {}
        
            @Override
            public void windowClosed(WindowEvent e) {RestaurantFileScraper.screenToFile(savedRestaurants.getScrollPanel());}
        
            @Override
            public void windowIconified(WindowEvent e) {}
        
            @Override
            public void windowDeiconified(WindowEvent e) {}
        
            @Override
            public void windowActivated(WindowEvent e) {}
        
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

        organizeCompoments();
    }

    protected void organizeCompoments() {
        frame.setContentPane(cp);
        
        setDefaultGridbagLayout();
        
        allowMultiplePerRow(true);
        setUpComponent(craveLabel);
        allowMultiplePerRow(false);
        setUpComponent(savedButton);
        setUpComponent(craveField);

        setUpComponent(whereLabel);
        setUpComponent(whereField);

        setUpComponent(budgetLabel);
        setUpComponent(euroSign1);
        setUpComponent(minField);
        setUpComponent(dashLabel);
        setUpComponent(euroSign2);
        setUpComponent(maxField);
        allowMultiplePerRow(false);
        setUpComponent(noBudCheck);

        // setUpComponent(serviesLabel);
        // allowMultiplePerRow(true);
        // setUpComponent(disCheck);
        // setUpComponent(serAnimCheck);
        // setUpComponent(miliCheck);
        // setUpComponent(vegetCheckbox);
        // setUpComponent(veganCheckbox);
        // setUpComponent(halalCheckbox);
        // allowMultiplePerRow(false);
        // setUpComponent(kashCheckbox);

        setUpComponent(resultsButton);

        frame.setVisible(true); //set sizes and locations of components AFTER THIS LINE

        craveLabel.setSize(craveLabel.getMinimumSize());
        savedButton.setSize(55, 40);
        euroSign1.setSize(45, 80);
        minField.setPreferredSize(new Dimension(100, 80));
        dashLabel.setPreferredSize(new Dimension(90, 60));
        euroSign2.setSize(45, 80);
        maxField.setPreferredSize(new Dimension(100, 80));
        noBudCheck.setSize(noBudCheck.getMinimumSize());

        minField.setSize(minField.getPreferredSize());
        dashLabel.setSize(dashLabel.getPreferredSize());
        maxField.setSize(maxField.getPreferredSize());

        craveLabel.setLocation((frame.getWidth() / 2) - (craveLabel.getWidth() / 2), craveLabel.getY());
        savedButton.setLocation(frame.getWidth() - savedButton.getWidth() - 5, savedButton.getY());
        euroSign1.setLocation(15, 290);
        minField.setLocation(euroSign1.getX() + euroSign1.getWidth(), 290);
        dashLabel.setLocation(minField.getX() + minField.getWidth() + 5, 290);
        euroSign2.setLocation(dashLabel.getX() + dashLabel.getWidth() - 5, 290);
        maxField.setLocation(euroSign2.getX() + euroSign2.getWidth(), 290);
        noBudCheck.setLocation(noBudCheck.getX() - 5, noBudCheck.getY() + (maxField.getHeight() / 2) - 10);
    }

    public SavedRestaurantsScreen getSavedRestaurants() {return savedRestaurants;}
    
    public ResultsScreen getResults() {return results;}

    private void moveToScreen(SubScreen newScreen) {
        cp.removeAll();
        if (newScreen instanceof SavedRestaurantsScreen) {RestaurantFileScraper.fileToScreen(savedRestaurants);}
        newScreen.organizeCompoments();
        refreshFrameVisuals();
    }

    public static void main(String[] args) {
        FilterScreen runner = new FilterScreen();
    }
}
