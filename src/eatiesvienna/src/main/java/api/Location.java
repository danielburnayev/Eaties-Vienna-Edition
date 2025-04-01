package api;

public class Location {
    private Rectangle rectangle = new Rectangle();
    class Low{
        double latitude = 40.477398;
        double longitude = -74.259087;
    }
    class High{
        double latitude = 40.91618;
        double longitude = -73.70018;
    }
    class Rectangle{
        High high = new High();
        Low low = new Low();
    }
}
