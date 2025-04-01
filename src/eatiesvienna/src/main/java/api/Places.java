package api;

public class Places {
    protected Place[] places;
    private String textQuery;
    private String address;
    private String text;
    private String id;
    private int maxResultCount = 10;
    //private boolean openNow = true;
    private Location locationRestriction = new Location();
   
    public String getTextQuery(){
        return textQuery;
    }

    public void setTextQuery(String textQuery){
        this.textQuery = textQuery;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
