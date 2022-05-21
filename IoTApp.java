import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

 public class IoTApp {
        public enum MenuType {
                MAIN,
                LOGIN,
                CONSOLE_SETTING,
                CONSOLE_SETTING_MODE,
                RESTART,
                SHUTDOWN,
                IPCAMERA,
                ACTEMP,
                THEFTSYSTEM

        }


        public static MenuType currentMenu = MenuType.MAIN;
         public static void main(String[] args) throws IOException {


                LCD LCD = new LCD();
                LCD.hideCursor();
                LCD.clearScreen();
                showMainMenu(LCD);
                Scanner console = new Scanner(System.in);

                //write to settings.dat
                while (true) {
                        FileWriter file = new FileWriter("C:/Users/User/IdeaProjects/OOP/setting.dat");
                        if (LCD.mode == "E") {//if mode is extended
                                file.write("\nMode: E");
                                if (LCD.reverseBackground == false) {
                                        file.write("\nColor: 1");
                                } else if (LCD.reverseBackground == true) {
                                        file.write("\nColor: 2");
                                }
                                file.flush();
                        }

                        if (LCD.mode == "N") {//if mode is normal
                                file.write("\nMode: N");
                                if (!LCD.reverseBackground) {
                                        file.write("\nColor: 1");
                                } else if (LCD.reverseBackground) {
                                        file.write("\nColor: 2");
                                }
                                file.flush(); //write all .write into the file
                        }
                        int user_input = console.nextInt();
                        switch (currentMenu) {
                                case MAIN:
                                        // mainMenu
                                        if (user_input == 1 || user_input == 2 || user_input == 3 ||
                                                user_input == 4 || user_input == 5) {
                                                // Input is valid for main menu
                                                switch (user_input) {
                                                        case 1:
                                                                currentMenu = MenuType.LOGIN;
                                                                showLogin(LCD);
                                                                break;
                                                        case 2:
                                                                currentMenu = MenuType.CONSOLE_SETTING;
                                                                showConsoleSetting(LCD);
                                                                break;
                                                        case 3:
                                                                currentMenu = MenuType.RESTART;
                                                                showRestart(LCD);
                                                                break;
                                                        case 4:
                                                                currentMenu = MenuType.SHUTDOWN;
                                                                showShutdown(LCD);
                                                                break;
                                                        case 5:
                                                                LCD.clearScreen();
                                                                /*showQuit();*/
                                                                System.exit(0);
                                                                break;
                                                        default:
                                                                LCD.printInvalidInput();
                                                }
                                        } else {
                                                // input is invalid
                                                LCD.printInvalidInput();
                                                break;
                                        }
                                        break;

                                case LOGIN:
                                        // Input is valid for main menu
                                        switch (user_input) {
                                                case 1:
                                                        currentMenu = MenuType.ACTEMP;
                                                        showACTEMP(LCD);
                                                        break;
                                                case 2:
                                                        currentMenu = MenuType.IPCAMERA;
                                                        showIPcamera(LCD);
                                                        break;

                                                case 3:
                                                        currentMenu = MenuType.THEFTSYSTEM;
                                                        showTheftsystem(LCD);
                                                        break;
                                                case 4:
                                                        currentMenu = MenuType.MAIN;
                                                        showMainMenu(LCD);
//                                                default:
//                                                        lcd1.printInvalidInput();
                                        }
                                        break;


                                case IPCAMERA:
                                        switch (user_input) {
                                                case 2:
                                                        LCD.setcamera("Enabled");
                                                        currentMenu = MenuType.IPCAMERA;
                                                        showIPcamera(LCD);
                                                        break;
                                                case 3:
                                                        LCD.setcamera("Disabled");
                                                        currentMenu = MenuType.IPCAMERA;
                                                        showIPcamera(LCD);
                                                        break;
                                                case 4:
                                                        currentMenu = MenuType.LOGIN;
                                                        showLogin(LCD);
//                                                default:
//                                                        lcd1.printInvalidInput();
                                        }
                                        break;


                                case ACTEMP:
                                        switch (user_input) {
                                                case 2:
                                                        LCD.settemp(20);
                                                        currentMenu = MenuType.ACTEMP;
                                                        showACTEMP(LCD);
                                                        break;
                                                case 3:
                                                        LCD.settemp(25);
                                                        currentMenu = MenuType.ACTEMP;
                                                        showACTEMP(LCD);
                                                        break;
                                                case 4:
                                                        LCD.settemp(30);
                                                        currentMenu = MenuType.ACTEMP;
                                                        showACTEMP(LCD);
                                                        break;
                                                case 5:
                                                        currentMenu = MenuType.LOGIN;
                                                        showLogin(LCD);
//                                                default:
//                                                        lcd1.printInvalidInput();
                                        }
                                        break;

                                case THEFTSYSTEM:
                                        switch (user_input) {
                                                case 2:
                                                        LCD.settheft("Enabled");
                                                        currentMenu = MenuType.THEFTSYSTEM;
                                                        showTheftsystem(LCD);
                                                        break;
                                                case 3:
                                                        LCD.settheft("Disabled");
                                                        currentMenu = MenuType.THEFTSYSTEM;
                                                        showTheftsystem(LCD);
                                                        break;
                                                case 4:
                                                        currentMenu = MenuType.LOGIN;
                                                        showLogin(LCD);
//                                                default:
//                                                        lcd1.printInvalidInput();
                                        }
                                        break;

                                case SHUTDOWN:
                                        currentMenu = MenuType.MAIN;
                                        showMainMenu(LCD);
                                        break;

                                case RESTART:
                                        currentMenu = MenuType.MAIN;
                                        showMainMenu(LCD);
                                        break;

                                case CONSOLE_SETTING:
                                        switch (user_input) {
                                                case 1:
                                                        currentMenu = MenuType.CONSOLE_SETTING_MODE;
                                                        showConsoleSettingMode(LCD);
                                                        break;
                                                case 2:
                                                        if (LCD.mode == "E") {
                                                                LCD.reverseBackgroundMode();
                                                                currentMenu = MenuType.MAIN;
                                                                showMainMenu(LCD);
                                                                break;
                                                        } else {
                                                                System.out.print("This function is not available in normal mode.");
                                                                break;
                                                        }

                                                case 3:
                                                        currentMenu = MenuType.MAIN;
                                                        showMainMenu(LCD);
                                                        break;
                                                default:
                                                        LCD.printInvalidInput();
                                        }
                                        break;


                                case CONSOLE_SETTING_MODE:

                                        switch (user_input) {
                                                case 0:
                                                        LCD.setMode("N");
                                                        currentMenu = MenuType.MAIN;
                                                        showMainMenu(LCD);
                                                        break;
                                                case 1:
                                                        LCD.setMode("E");
                                                        currentMenu = MenuType.MAIN;
                                                        showMainMenu(LCD);
                                                        break;
                                                default:
                                                        LCD.printInvalidInput();
                                        }
                                        break;

                                default:
                                        LCD.printInvalidInput();
                                        break;

                        }

                }
        }


        public static messageBox createFunctionMenu () {
                messageBox msgBox = new messageBox ("- Stamford Function Menu - ");
                mBoxLine mainMenu_l1 = new mBoxLine("Change AC Temperature set point", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
                mBoxLine mainMenu_l2 = new mBoxLine("Enable/Disable IP Camera", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l2);
                mBoxLine mainMenu_l3 = new mBoxLine("Turn anti-theft system on/off", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l3);
                mBoxLine mainMenu_l4 = new mBoxLine("Back", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l4);
                return msgBox;
        }

        public static messageBox createMainMenu () {
                messageBox msgBox = new messageBox ("- Stamford Menu System - ");
                mBoxLine mainMenu_l1 = new mBoxLine("Login", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
                mBoxLine mainMenu_l2 = new mBoxLine("Console Setting", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l2);
                mBoxLine mainMenu_l3 = new mBoxLine("Restart", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l3);
                mBoxLine mainMenu_l4 = new mBoxLine("Shutdown", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l4);
                mBoxLine mainMenu_l5 = new mBoxLine("Exit", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l5);
                return msgBox;
        }

        public static messageBox createConsoleSettingMenu () {
                messageBox msgBox = new messageBox ("- Console Setting - ");
                mBoxLine mainMenu_l1 = new mBoxLine("Change Console mode", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
                mBoxLine mainMenu_l2 = new mBoxLine("Reverse background color", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l2);
                mBoxLine mainMenu_l3 = new mBoxLine("Back", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l3);
                return msgBox;
        }

        public static messageBox createConsoleSettinModegMenu (LCD lcd) {
                messageBox msgBox = new messageBox ("- Console Setting, Mode - ");
                String mode = lcd.getMode();
                String final_mode = mode.equals("E")?"Extended" : "Normal";
                mBoxLine mainMenu_l1 = new mBoxLine("Current Mode is " + final_mode , mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
                return msgBox;
        }

        public static messageBox createRestartMenu () {
                messageBox msgBox = new messageBox ("- System Restart - ");
                mBoxLine mainMenu_l1 = new mBoxLine("System is being restarted, please be patient.", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
                return msgBox;
        }

        public static messageBox createShutdownMenu () {
                messageBox msgBox = new messageBox ("- System Shutdown - ");
                mBoxLine mainMenu_l1 = new mBoxLine("System is being shutdown, please be patient.", mBoxLine.Alignment.LEFT);
                msgBox.addLine(mainMenu_l1);
                return msgBox;
        }

        public static messageBox createACTEMP (LCD lcd) {
                float temp = lcd.gettemp();
                float final_temp = temp;
                messageBox msgBox = new messageBox ("- AC Temperature set point - ");
                mBoxLine ACTEMP_l1 = new mBoxLine("Current temperature is " + final_temp + "(°C)", mBoxLine.Alignment.LEFT);
                msgBox.addLine(ACTEMP_l1);
                mBoxLine ACTEMP_l2 = new mBoxLine("Set to 20°C", mBoxLine.Alignment.LEFT);
                msgBox.addLine(ACTEMP_l2);
                mBoxLine ACTEMP_l3 = new mBoxLine("Set to 25°C", mBoxLine.Alignment.LEFT);
                msgBox.addLine(ACTEMP_l3);
                mBoxLine ACTEMP_l4 = new mBoxLine("Set to 30°C", mBoxLine.Alignment.LEFT);
                msgBox.addLine(ACTEMP_l4);
                mBoxLine ACTEMP_l5 = new mBoxLine("Return to main menu", mBoxLine.Alignment.LEFT);
                msgBox.addLine(ACTEMP_l5);
                return msgBox;
        }

        public static void showACTEMP (LCD lcd){
                messageBox ACTEMP_msgBox = createACTEMP(lcd);
                lcd.setPrompt("Select function: ");
                lcd.setFuncCount(ACTEMP_msgBox.getContent().size());
                lcd.showMessageBox(ACTEMP_msgBox);
        }

        public static messageBox createIPcamera (LCD lcd) {
                String camera = lcd.getcamera();
                String final_camera = camera.equals("Enabled")?"Enabled" : "Disabled";
                messageBox msgBox = new messageBox ("- IP camera - ");
                mBoxLine IPcamera_l1 = new mBoxLine("Current mode is "+final_camera, mBoxLine.Alignment.LEFT);
                msgBox.addLine(IPcamera_l1);
                mBoxLine IPcamera_l2 = new mBoxLine("Enable camera",mBoxLine.Alignment.LEFT);
                msgBox.addLine(IPcamera_l2);
                mBoxLine IPcamera_l3 = new mBoxLine("Disable camera",mBoxLine.Alignment.LEFT);
                msgBox.addLine(IPcamera_l3);
                mBoxLine IPcamera_l4 = new mBoxLine("Go back to main menu",mBoxLine.Alignment.LEFT);
                msgBox.addLine(IPcamera_l4);


                return msgBox;
        }

        public static void showIPcamera (LCD lcd){
                messageBox IPcamera_msgBox = createIPcamera(lcd);
                lcd.setPrompt("Select function: ");
                lcd.setFuncCount(IPcamera_msgBox.getContent().size());
                lcd.showMessageBox(IPcamera_msgBox);

        }

        public static messageBox createTheftsystem (LCD lcd) {
                String theft = lcd.gettheft();
                String final_theft = theft.equals("Enabled")?"Enabled" : "Disabled";
                messageBox msgBox = new messageBox ("- Theft system - ");
                mBoxLine Theftsystem_l1 = new mBoxLine("Current mode is "+final_theft, mBoxLine.Alignment.LEFT);
                msgBox.addLine(Theftsystem_l1);
                mBoxLine Theftsystem_l2 = new mBoxLine("Enable Theft system",mBoxLine.Alignment.LEFT);
                msgBox.addLine(Theftsystem_l2);
                mBoxLine Theftsystem_l3 = new mBoxLine("Disable Theft system",mBoxLine.Alignment.LEFT);
                msgBox.addLine(Theftsystem_l3);
                mBoxLine Theftsystem_l4 = new mBoxLine("Go back to main menu",mBoxLine.Alignment.LEFT);
                msgBox.addLine(Theftsystem_l4);


                return msgBox;
        }

        public static void showTheftsystem (LCD lcd){
                messageBox Theftsystem_msgBox = createTheftsystem(lcd);
                lcd.setPrompt("Select function: ");
                lcd.setFuncCount(Theftsystem_msgBox.getContent().size());
                lcd.showMessageBox(Theftsystem_msgBox);

        }


        public static void showMainMenu(LCD lcd) {
                messageBox mainMenu_msgBox = createMainMenu ();
                lcd.setPrompt("Select a function");
                lcd.setFuncCount(mainMenu_msgBox.getContent().size());
                lcd.showMessageBox(mainMenu_msgBox);
        }

        public static void showLogin(LCD lcd) {
                messageBox loginMenu_msgBox = createFunctionMenu ();
                lcd.setPrompt("Select a function");
                lcd.setFuncCount(loginMenu_msgBox.getContent().size());
                lcd.showMessageBox(loginMenu_msgBox);
        }

        public static void showRestart(LCD lcd) {
                messageBox restartMenu_msgBox = createRestartMenu ();
                lcd.setPrompt("Restarting");
                lcd.setFuncCount(restartMenu_msgBox.getContent().size());
                lcd.showMessageBox(restartMenu_msgBox);
                lcd.printProgress();
        }

        public static void showShutdown(LCD lcd) {
                messageBox shutdownMenu_msgBox = createShutdownMenu ();
                lcd.setPrompt("Shutting down");
                lcd.setFuncCount(shutdownMenu_msgBox.getContent().size());
                lcd.showMessageBox(shutdownMenu_msgBox);
                lcd.printProgress();
        }

        public static void showConsoleSetting(LCD lcd) {
                messageBox consoleSettingMenu_msgBox = createConsoleSettingMenu ();
                lcd.setPrompt("Select a function");
                lcd.setFuncCount(consoleSettingMenu_msgBox.getContent().size());
                lcd.showMessageBox(consoleSettingMenu_msgBox);
        }

        public static void showConsoleSettingMode(LCD lcd) {
                messageBox consoleSettingModeMenu_msgBox = createConsoleSettinModegMenu (lcd);
                lcd.setPrompt("Choose console mode: 0 for Normal, 1 for Extended");
                lcd.setFuncCount(consoleSettingModeMenu_msgBox.getContent().size());
                lcd.showMessageBox(consoleSettingModeMenu_msgBox);
        }



}

