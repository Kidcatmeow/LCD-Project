import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;


public class LCD implements Comparable<LCD> {


// an LCD object represents an LCD device that console output can be
// sent to it for display application output
    int width;
    int height;
    int funcCount;
    String prompt;
    String mode;
    String camera;
    String theft;
    float temp;
    boolean reverseBackground;

    final static char escCode = 0x1B;

    final char topLeft = 9484;
    final char topRight = 9488;
    final char bottomLeft = 9492;
    final char bottomRight = 9496;
    final char dash = 9472;
    final char midLeft = 9500;
    final char midRight = 9508;
    final char bar = 9474;

    public interface Comparable<T> {
        public int compareTo(T other_lcd);
    }

    public int compareTo(LCD other_lcd) {
        int dimension = width * height;
        int other_dimension = other_lcd.width * other_lcd.height;
        if (dimension < other_dimension) {
            return -1;
        } else if (dimension == other_dimension) {
            return 0;
        } else { // (dimension > other_dimension)
            return 1;
        }
    }


    //Default constructor
    //Read from settings.dat
    public LCD() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("C:/Users/User/IdeaProjects/OOP/setting.dat"));
        while(reader.hasNext()) {
            String data = reader.next();
            if (data.charAt(0) == 'N' || data.charAt(0) == 'E') {//check if it's data for 'mode'
                if (data.charAt(0) == 'N') {
                    this.mode = "N";
                } else {
                    this.mode = "E";
                }
            }
            else if (data.charAt(0) == '1' || data.charAt(0) == '2') {//check if it's the data for bg color
                if (data.charAt(0) == '1') {// 1 for normal,2 for reversed
                    this.reverseBackground = false;
                } else {
                    this.reverseBackground = true;
                }
            }
        }

        this.width = 80;
        this.height = 25;
        this.funcCount = 0;
        this.prompt = "Select a function";
        this.camera = "Enabled";
        this.temp = 25;
        this.theft = "Enabled";
    }

    // Constructor:
    // before constructor we change height and width by either this:
    // LCD lcd1 = new LCD();
    // lcd1.setWidth(25);
    // lcd1.setHeight(80);
    // Or:
    // LCD lcd1 = new LCD();
    // lcd1.height = 25;
    // lcd1.width = 80;
    // but with constructor we can pass initial values as parameters:
    // LCD lcd1 = new LCD(25, 80);
    //However, LCD lcd1 = new LCD(); Will not compile anymore

    public LCD (int init_width,int init_height){
        this.width = init_width;
        this.height = init_height;
        this.funcCount = 0;
        this.prompt = "Select a function";
        this.mode = "N"; // N = Normal, E = Extended
        this.camera = "Enabled";
        this.temp = 25;
        this.theft = "Enabled";
        reverseBackground = false;
    }

    // Mutators:
    // instance method that modifies the object’s internal state.
    // Generally, a mutator assigns a new value to one of the object’s fields.
    // mutator method’s name to begin with “set"
    public void setWidth(int new_width) {
        width = new_width;
    }
    public void setHeight(int new_height) {
        height = new_height;
    }

    // Accessors:
    // provides information about the state of an object without modifying it.
    // Generally, an accessor returns the value of one of the object’s fields.
    // accessor method’s name to begin with “get"
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public int getResolution(){
        return width * height;
    }
    public String toString(){
        return "LCD object with dimension = (" + width + "," + height + ")";
    }

    public void setFuncCount(int funcCount) {
        this.funcCount = funcCount;
    }

    public void setPrompt (String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt(){
        return prompt;
    }

    public void setMode (String mode){
        this.mode = mode;
    }

    public String getMode(){
        return this.mode;
    }

    public void setcamera (String camera){
        this.camera = camera;
    }

    public String getcamera(){
        return this.camera;
    }

    public void settemp (float temp){
        this.temp = temp;
    }

    public float gettemp(){
        return this.temp;
    }

    public void settheft (String theft){
        this.theft = theft;
    }

    public String gettheft(){
        return this.theft;
    }

    public void reverseBackgroundMode () {
        this.reverseBackground = ! this.reverseBackground ;
    }

    public boolean getBackgroundMode () {
        return this.reverseBackground;
    }

    public void showMessageBox (messageBox mBox) {



        String midRight = mode.equals("E")? "┤" : "|";
        String midLeft = mode.equals("E")? "├" : "|";
        String dash = mode.equals("E")? "─" : "-";
        String topLeft = mode.equals("E")? "┌" : "-";
        String topRight = mode.equals("E")? "┐" : "-";
        String bottomLeft = mode.equals("E")? "└" : "-";
        String bottomRight = mode.equals("E")? "┘" : "-";

        clearScreen();
        GotoXY(1, 1);
        System.out.printf("%s", topLeft);
        for (int i = 0; i < width - 2 ; i++) {
            System.out.print(dash);
        }
        System.out.print(topRight);
        GotoXY(2, 0);
        printBoxLine(mBox.getHeader(), mBoxLine.Alignment.CENTER);
        GotoXY(3, 0);
        printBoxLine(" ", mBoxLine.Alignment.LEFT);
        GotoXY(4, 0);
        System.out.print(midLeft);
        for (int i = 0; i < 78; i++) {
            System.out.print(dash);
        }
        System.out.print(midRight);
        GotoXY(5, 0);
        printBoxLine(" ", mBoxLine.Alignment.LEFT);
        Iterator<mBoxLine> lines_iterator = mBox.getContent().iterator();

        Integer n = 1;
        while (lines_iterator.hasNext()) {
            GotoXY(5 + n, 0);
            mBoxLine line = lines_iterator.next();
            printBoxLine(n.toString() + ") " + line.text, line.alignment);n++;
        }

        for (int i = n + 5; i < 23; i++) {
            GotoXY(i, 0);
            printBoxLine(" ", mBoxLine.Alignment.LEFT);
        }
        GotoXY(22, 0);
        System.out.print(midLeft);
        for (int i = 0; i < 78; i++) {
            System.out.print(dash);}
        System.out.print(midRight);
        GotoXY(24, 0);
        printBoxLine(" ", mBoxLine.Alignment.LEFT);
        GotoXY(23, 0);
        printStatusBar();
        GotoXY(25, 0);
        System.out.print(bottomLeft);
        for (int i = 0; i < 78; i++) {
            System.out.print(dash);
        }
        System.out.println(bottomRight);
        int prompt_size = prompt.length() + 11;
        if (mode == "E"){
        GotoXY(23, prompt_size);}

    }

    public void hideCursor () {
        System.out.printf("%s[?25l", escCode);
    }
    public void showCursor () {
        System.out.printf("%s[?25h", escCode);
    }

    public static void GotoXY (int x, int y) {
        System.out.printf("%s[%d;%df", escCode, x, y);
    }

    public void invertColor () {
        if (mode == "E") {
            System.out.printf("%s[7m", escCode);
        } else {
            System.out.printf("%s[0m", escCode);
        }
    }

    public static void normalColor () {
        System.out.printf("%s[0m", escCode);
    }

    // instance methods working on data:
    public void clearScreen(){
        if (mode == "E") {
            hideCursor();
            if (reverseBackground) {
                invertColor();
            } else {
                normalColor();
            }
            for (int i = 1; i <= this.height; i++){
                for (int j = 1; j <= this.width; j++){
                    GotoXY(i, j);
                    System.out.print(' ');
                }
            }
        }

        else{
            normalColor();
        }

        for (int i = 1; i <= this.height; i++){
            for (int j = 1; j <= this.width; j++){
                GotoXY(i, j);
                System.out.print(' ');
            }
        }
    }

    public static void printSpace(int n) {
        for (int i = 0; i <= n; i++) {
            System.out.print(' ');
        }
    }

    private void printBoxLine(String text, mBoxLine.Alignment alignment) {
        String bar = mode.equals("E")? "│" : "|";

        if (reverseBackground)
            invertColor();
        else
            normalColor();
        int textLength = text.length();
        int padding;
        boolean isEven;
        if(textLength % 2 == 0) { // 21 % 2 = 1 20 / 2 = 10
            // length is even
            padding = (80 - (6 + textLength)) / 2;
            isEven = true;
        }
        else {
            // length is odd
            padding = (80 - (6 + textLength + 1)) / 2;
            isEven = false;
        }
        switch (alignment) {
            case RIGHT:
                // alignment right
                System.out.print(bar + " ");
                // 79 - 2 - 2 - textlength
                printSpace(75 - textLength);
                System.out.print(text);
                System.out.println(" " + bar);
                break;

                case CENTER:
                    // alignment center
                    if (isEven) {
                    System.out.print(bar + " ");
                    printSpace(padding);
                    System.out.print(text);
                    printSpace(padding);
                    System.out.println(" " + bar);
                    break;
                }
                    else {
                        // odd
                        System.out.print(bar + " ");
                        printSpace(padding);
                        System.out.print(text);
                        printSpace(padding + 1);
                        System.out.println(" "+ bar);
                        break;
                    }

            default:
                // 0: alignment left
                System.out.print(bar + " ");
                System.out.print(text);
                // 79 - 2 - 2 - textlength
                printSpace(75 - textLength);
                System.out.println(" " + bar);
                 break;
        }
    }

    private void printStatusBar() {

        if (mode == "E"){
        String bar = mode.equals("E")? "│" : "|";
        System.out.print(bar + " ");
        System.out.print(prompt + "(1-" + funcCount + ") >");
        GotoXY(23, 78);
        System.out.print(mode + " " + bar);
    }
        else {
            printBoxLine(prompt,mBoxLine.Alignment.LEFT);
            printBoxLine(mode,mBoxLine.Alignment.RIGHT);
        }
    }

    public void printInvalidInput() {
        String bar = mode.equals("E")? "│" : "|";
        GotoXY(23, 0);
        System.out.print(bar + " ");
        System.out.print(prompt + "(1-" + funcCount + ") Invalid input. Please try again. >");
        GotoXY(23, 78);
        System.out.print(mode + " " + bar);
        int prompt_size = prompt.length() + 11 + 33;
        GotoXY(23, prompt_size);
    }

    public void printProgress() {
        // clear row 23
        for (int j = 1; j <= width - 1; j++) {
            GotoXY(23, j);
            System.out.print(' ');
        }

        String bar = mode.equals("E")? "│" : "|";
        GotoXY(23, 0);
        System.out.print(bar + " ");
        System.out.print(prompt + " #");

        // faking a restart that takes 5 seconds
        for (int i =0; i < 6; i++) {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            System.out.print("#");
        }
        System.out.print(" Done! Press any integer to back to main menu.");

    }
    public static void main(String[] args) throws FileNotFoundException {
        new LCD();
    }


    }



