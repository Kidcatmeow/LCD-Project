import java.util.ArrayList;

// A messageBox object represents a message box that can be drawn on LCD.
// A message box has a header and a content.
// Content consist of several lines that can be added by calling addLine() method.

public class messageBox {
    String header;
    ArrayList<mBoxLine> content;

    // default constructor
    public messageBox(){
        header = "No header";
        content = new ArrayList<>();
    }

    //constructor
    public messageBox(String header){
        this.header = header;
        content = new ArrayList<>();
    }

    //mutators
    public void setHeader(String text){
        header = text;
    }

    //Accessors
    public String getHeader(){
        return header;
    }

    public void addLine(mBoxLine line) {
        content.add(line);
    }

    public void clearContent() {
        content.clear();
    }

    public ArrayList <mBoxLine> getContent() {
        return content;
    }


}