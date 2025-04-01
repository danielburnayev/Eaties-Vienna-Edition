package api;

public class Place {
    private String text;
    private String formattedAddress;

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getFormattedAddress(){
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress){
        this.formattedAddress = formattedAddress;
    }
}
