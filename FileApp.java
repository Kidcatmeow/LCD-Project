// Task 8: write a method called stripHtmlTags that accepts a Scanner representing an input file containing an HTML web page as its parameter, then reads that file and prints the file’s text with all HTML
//tags removed. A tag is any text between the characters < and >. For example, consider the following
//text:
//        <html>
//        <head>
//        <title>My web page</title>
//        </head>
//        <body>
//        <p>There are many pictures of my cat here,
//        as well as my <b>very cool</b> blog page,
//        which contains <font color="red">awesome
//        stuff about my trip to Vegas.</p>
//        Here’s my cat now:<img src="cat.jpg">
//        </body>
//        </html>

import java.io.*;
import java.util.*;


public class FileApp {
    public static void main(String[] args)
            throws FileNotFoundException {
        Scanner input = new Scanner(new File("C:/Users/User/IdeaProjects/OOP/src/Program.java"));
        stripComments(input);
    }

    public static void stripHtmlTags(Scanner input) {
        while (input.hasNext()) {
            String line = input.nextLine();

            while (line.contains("<") || (line.contains(">"))) {
                line = line.substring(0, line.indexOf("<")) + line.substring(line.indexOf(">") + 1);
            }
            System.out.println(line);
        }
    }

    //Task 9: Write a method called stripComments that accepts a Scanner representing an input file
    //containing a Java program as its parameter, reads that file, and then prints the file’s text with all comments
    //removed. A comment is any text on a line from // to the end of the line, and any text between /* and */
    //characters. For example, consider the following text:

    public static void stripComments(Scanner input) {
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if ((line.contains("/*")) && (line.contains("*/"))) {
            line = line.substring(0,line.indexOf("/*"))+line.substring(line.indexOf("*/") + 2);
            }
            if (line.contains("*/")){
                line = line.substring(line.indexOf("*/")+2);
            }
            if (line.contains("/*")){
                line = line.substring(0,line.indexOf("/*"));
            }
            if (line.contains("//")){
                line = line.substring(0,line.indexOf("//"));
            }
            if (!line.contains(";") && !line.contains("/*") && !line.contains("*/") && !line.contains("//") && !line.contains("}") && !line.contains("{")){
                line = "";
            }
            System.out.println(line);
        }
    }

    //substring(start index,end index) - print before
    //substring(beginning index) - print after

    //Create even spaces between the text
    public static void echoFixed(String text, PrintStream output) {
        Scanner data = new Scanner(text);
        if (data.hasNext()) {
            output.print(data.next());
            while (data.hasNext()) {
                output.print(" " + data.next());
            }
        }
        output.println();
    }

    // Prompts the user for a legal file name
    // creates and returns a Scanner tied to the file
    public static Scanner getInput(Scanner console)
            throws FileNotFoundException {
        System.out.print("input file name? ");
        File f = new File(console.nextLine());
        while (!f.canRead()) {
            System.out.println("File not found. Try again."); System.out.print("input file name? ");
            f = new File(console.nextLine());
        }
        // now we know that f is a file that can be read
        return new Scanner(f);
    }


}



