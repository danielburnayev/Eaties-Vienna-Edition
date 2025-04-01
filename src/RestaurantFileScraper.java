import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RestaurantFileScraper {

    private static File restaurantsFile;
    private static Scanner scanner;

    private static void setup() throws IOException {
        restaurantsFile = new File("SavedRestaurants.txt");
        restaurantsFile.setReadable(true);
        restaurantsFile.setExecutable(false);
        if (!restaurantsFile.exists()) {restaurantsFile.createNewFile();}
    } 

    public static void screenToFile(JPanel panelWithTheStuff) {
        try {
            setup();

            FileWriter fw = null;
            unlockForFileInspectors();
            fw = new FileWriter(restaurantsFile);

            Component[] components = panelWithTheStuff.getComponents();
            for (int i = 0; i < components.length - 1; i++) {
                SavedRestaurantPanel theElem = (SavedRestaurantPanel)components[i];
                Component[] foodComps = theElem.getFoodPanel().getComponents();
                String theText = theElem.getPlaceText() + "\n" + theElem.getAddressText() + "\n" + ((foodComps.length - 1) / 2) + " " + theElem.getNotableFoodsLabelText();
                
                for (int j = 0; j < foodComps.length - 1; j += 2) {
                    String food = ((SpecialTextField)foodComps[j]).getActualText() + j;
                    food += (j + 2 < foodComps.length - 1) ? ", " : "";
                    theText += food;
                }
                
                fw.write(theText + "\n\n");
            }

            fw.close();
        }
        catch (IOException io) {System.out.println(io.getMessage());}

        lockFile();
    }

    public static void fileToScreen(SavedRestaurantsScreen screen) {
        try {
            setup();

            unlockForFileInspectors();
            scanner = new Scanner(restaurantsFile);

            int count = 0;
            while (scanner.hasNextLine()) {
                String place = (count > 0) ? scanner.next() : scanner.nextLine();
                String address = scanner.nextLine();
                System.out.print(place + address + " ");
                int numOfFood = scanner.nextInt();
                System.out.println(numOfFood);
                System.out.println(scanner.next());
                System.out.println(scanner.next());
                String[] foods = new String[numOfFood];
                for (int i = 0; i < numOfFood; i++) {
                    foods[i] = scanner.next();
                    System.out.print(foods[i] + " ");
                }
                System.out.println("End of loop");
                // screen.addRestaurant(place, address, foods);
                count++;
            }

            scanner.close();
        }
        catch (IOException io) {System.out.println(io.getMessage());}

        lockFile();


    }

    private static void lockFile() {
        restaurantsFile.setWritable(false);
    }

    private static void unlockForFileInspectors() {
        restaurantsFile.setWritable(true);
    }

    

}