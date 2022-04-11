import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    //Initiates string prompts
    final static String WELCOMEPROMPT = "Please choose a login type\n1. Teacher\n2. Student";
    final static String ERROR = "There was an error!";
    final static String ACCOUNTPROMPT = "How would you like to proceed?\n1. Make a new account" +
            "\n2. Login with existing account\n3. Edit existing account";
    final static String MAKEUSERNAME = "Please enter your desired username";
    final static String MAKEPASSWORD = "Please enter your desired password";
    final static String USERNAMEERROR = "Username can not contain space!";
    final static String PASSWORDERROR = "Password can not contain space!";
    final static String LOGINUSERNAME = "Please enter your username";
    final static String LOGINPASSWORD = "Please enter your password";
    final static String EDITACCOUNT = "What would you like to edit?\n1. Account name\n2. Password";
    final static String WRONGPASSWORD = "That's the wrong password!";
    final static String USERNAMEEXIST = "This username already existed!";
    final static String USERNAMEDOESNOTEXIST = "This username does not existed!";

    /*
    method that runs the quiz and takes the scanner as a parameter
     */
    public static String runQuiz(Scanner scanner) {
        int usernameStatus;
        int welcome = 0;
        int account;
        int[] loginType = new int[1];
        int[] accountChoice = new int[1];
        int[] login = new int[1];

        checkFile();
        String username1; //initiates username of current user
        while (welcome == 0) {
            System.out.println(WELCOMEPROMPT);
            try {
                loginType[0] = Integer.parseInt(scanner.nextLine());
                if (loginType[0] != 1 && loginType[0] != 2) { //throws error if login is invalid option
                    System.out.println(ERROR);
                } else {
                    welcome++; //breaks out of loop
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (loginType[0] == 1) {
            while (login[0] == 0) {
                account = 0;
                usernameStatus = 0;
                while (account == 0) {
                    System.out.println(ACCOUNTPROMPT);
                    try {
                        accountChoice[0] = Integer.parseInt(scanner.nextLine());
                        if (accountChoice[0] != 1 && accountChoice[0] != 2 && accountChoice[0] != 3) {
                            //throws error if account choice is invalid option
                            System.out.println(ERROR);
                        } else {
                            account++; //breaks out of loop
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (accountChoice[0] == 1) {
                    System.out.println(MAKEUSERNAME);
                    while (usernameStatus == 0) {
                        username1 = scanner.nextLine(); //updates username to user input
                        while (username1.contains(" ")) {
                            System.out.println(USERNAMEERROR);
                            System.out.println(MAKEUSERNAME);
                            username1 = scanner.nextLine();
                        }
                        try {
                            File f = new File("TeacherAccount.txt");
                            FileReader fr = new FileReader(f);
                            BufferedReader bfr = new BufferedReader(fr);
                            String line = bfr.readLine();
                            usernameStatus = 1;

                            while (line != null) { //checks if username already exists
                                if (username1.equals(line)) {
                                    System.out.println(USERNAMEEXIST);
                                    usernameStatus = 2;
                                    break;
                                }
                                line = bfr.readLine();
                            }
                            bfr.close();
                            if (usernameStatus == 1) { //if user creates new account
                                File teacherAccount = new File("TeacherAccount.txt");
                                FileOutputStream fos = new FileOutputStream(teacherAccount, true);
                                PrintWriter pw = new PrintWriter(fos);
                                pw.println(username1);
                                System.out.println(MAKEPASSWORD);
                                String password = scanner.nextLine(); //sets password
                                while (password.contains(" ")) {
                                    System.out.println(PASSWORDERROR);
                                    System.out.println(MAKEPASSWORD);
                                    password = scanner.nextLine();
                                }
                                File teacherPassword = new File("TeacherPassword.txt");
                                FileOutputStream fos2 = new FileOutputStream(teacherPassword, true);
                                PrintWriter pw2 = new PrintWriter(fos2);
                                pw2.println(password);
                                pw2.close();
                                pw.close();
                                login[0] = 1;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (accountChoice[0] == 2) { //if user wants to login to existing account
                    System.out.println(LOGINUSERNAME);
                    while (usernameStatus == 0) {
                        try {
                            username1 = scanner.nextLine();
                            File f = new File("TeacherAccount.txt");
                            FileReader fr = new FileReader(f);
                            BufferedReader bfr = new BufferedReader(fr);
                            String line = bfr.readLine();
                            usernameStatus = 1;
                            int[] counter = new int[1];
                            ArrayList<String> accountList = new ArrayList<>(); //list of usernames
                            ArrayList<String> passwordList = new ArrayList<>(); //list of passwords

                            while (line != null) {
                                accountList.add(line);
                                if (username1.equals(line)) {
                                    counter[0]++;
                                }
                                line = bfr.readLine();
                            }
                            bfr.close();
                            if (counter[0] == 0) { //checks if usernsame exists
                                System.out.println(USERNAMEDOESNOTEXIST);
                                break;
                            } else {
                                System.out.println(LOGINPASSWORD);
                                String password = scanner.nextLine();
                                File f2 = new File("TeacherPassword.txt");
                                FileReader fr2 = new FileReader(f2);
                                BufferedReader bfr2 = new BufferedReader(fr2);
                                String line2 = bfr2.readLine();

                                while (line2 != null) {
                                    passwordList.add(line2);
                                    line2 = bfr2.readLine();
                                }
                                bfr2.close();
                                if (passwordList.get(accountList.indexOf(username1)).equals(password)) {
                                    return "Teacher " + username1;
                                } else {
                                    System.out.println(WRONGPASSWORD);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (accountChoice[0] == 3) { //if student wants to edit account
                    System.out.println(LOGINUSERNAME);
                    while (usernameStatus == 0) {
                        try {
                            username1 = scanner.nextLine();
                            File f = new File("TeacherAccount.txt");
                            FileReader fr = new FileReader(f);
                            BufferedReader bfr = new BufferedReader(fr);
                            String line = bfr.readLine();
                            usernameStatus = 1;
                            int[] counter = new int[1];
                            ArrayList<String> accountList = new ArrayList<>();
                            ArrayList<String> passwordList = new ArrayList<>();

                            while (line != null) {
                                accountList.add(line);
                                if (username1.equals(line)) {
                                    counter[0]++;
                                }
                                line = bfr.readLine();
                            }
                            bfr.close();
                            if (counter[0] == 0) {
                                System.out.println(USERNAMEDOESNOTEXIST);
                                break;
                            } else {
                                System.out.println(LOGINPASSWORD);
                                String teacherPassword = scanner.nextLine();
                                File f2 = new File("TeacherPassword.txt");
                                FileReader fr2 = new FileReader(f2);
                                BufferedReader bfr2 = new BufferedReader(fr2);
                                String line2 = bfr2.readLine();

                                while (line2 != null) {
                                    passwordList.add(line2);
                                    line2 = bfr2.readLine();
                                }
                                bfr2.close();
                                if (passwordList.get(accountList.indexOf(username1)).equals(teacherPassword)) {
                                    //run teacher
                                    try {
                                        System.out.println(EDITACCOUNT);
                                        int edit = Integer.parseInt(scanner.nextLine());
                                        if (edit == 1) {
                                            System.out.println(MAKEUSERNAME);
                                            String username = scanner.nextLine();
                                            while (username.contains(" ")) {
                                                System.out.println(USERNAMEERROR);
                                                System.out.println(MAKEUSERNAME);
                                                username = scanner.nextLine();
                                            }
                                            accountList.set(accountList.indexOf(username1), username);
                                            File teacherAccount = new File("TeacherAccount.txt");
                                            FileOutputStream fos2 = new FileOutputStream(teacherAccount, false);
                                            PrintWriter pw2 = new PrintWriter(fos2);

                                            for (int i = 0; i < accountList.size(); i++) {
                                                pw2.println(accountList.get(i));
                                            }
                                            pw2.close();
                                        } else if (edit == 2) {
                                            System.out.println(MAKEPASSWORD);
                                            String password = scanner.nextLine();
                                            while (password.contains(" ")) {
                                                System.out.println(PASSWORDERROR);
                                                System.out.println(MAKEPASSWORD);
                                                password = scanner.nextLine();
                                            }
                                            passwordList.set(passwordList.indexOf(teacherPassword), password);
                                            File teacherPasswordFile = new File("TeacherPassword.txt");
                                            FileOutputStream fos2 = new FileOutputStream(teacherPasswordFile,
                                                    false);
                                            PrintWriter pw2 = new PrintWriter(fos2);

                                            for (int i = 0; i < accountList.size(); i++) {
                                                pw2.println(passwordList.get(i));
                                            }
                                            pw2.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return "";
                                } else {
                                    System.out.println(WRONGPASSWORD);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if (loginType[0] == 2) { //student login
            while (login[0] == 0) {
                account = 0;
                usernameStatus = 0;
                while (account == 0) {
                    System.out.println(ACCOUNTPROMPT); //prompts user
                    try {
                        accountChoice[0] = Integer.parseInt(scanner.nextLine());
                        if (accountChoice[0] != 1 && accountChoice[0] != 2 && accountChoice[0] != 3) {
                            System.out.println(ERROR);
                        } else {
                            account++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (accountChoice[0] == 1) { //makes account for either student or teacher
                    System.out.println(MAKEUSERNAME); //prompts user
                    while (usernameStatus == 0) {
                        username1 = scanner.nextLine();
                        while (username1.contains(" ")) {
                            System.out.println(USERNAMEERROR);
                            System.out.println(MAKEUSERNAME);
                            username1 = scanner.nextLine();
                        }
                        try {
                            File f = new File("StudentAccount.txt");
                            FileReader fr = new FileReader(f);
                            BufferedReader bfr = new BufferedReader(fr);
                            String line = bfr.readLine();
                            usernameStatus = 1;

                            while (line != null) {
                                if (username1.equals(line)) { //catches if the username already exists
                                    System.out.println(USERNAMEEXIST);
                                    usernameStatus = 2;
                                    break;
                                }
                                line = bfr.readLine();
                            }
                            bfr.close();
                            if (usernameStatus == 1) {
                                File studentAccount = new File("StudentAccount.txt");
                                FileOutputStream fos = new FileOutputStream(studentAccount, true);
                                PrintWriter pw = new PrintWriter(fos);
                                pw.println(username1);
                                System.out.println(MAKEPASSWORD);
                                String password = scanner.nextLine();
                                while (username1.contains(" ")) {
                                    System.out.println(PASSWORDERROR);
                                    System.out.println(MAKEPASSWORD);
                                    password = scanner.nextLine();
                                }
                                File studentPassword = new File("StudentPassword.txt");
                                FileOutputStream fos2 = new FileOutputStream(studentPassword, true);
                                PrintWriter pw2 = new PrintWriter(fos2);
                                pw2.println(password);
                                pw2.close();
                                pw.close();
                                login[0] = 1;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (accountChoice[0] == 2) { //create account depending on whether student or teacher
                    System.out.println(LOGINUSERNAME);
                    while (usernameStatus == 0) {

                        try {
                            username1 = scanner.nextLine();
                            File f = new File("StudentAccount.txt");
                            FileReader fr = new FileReader(f);
                            BufferedReader bfr = new BufferedReader(fr);
                            String line = bfr.readLine();
                            usernameStatus = 1;
                            int[] counter = new int[1];
                            ArrayList<String> accountList = new ArrayList<>();
                            ArrayList<String> passwordList = new ArrayList<>();

                            while (line != null) {
                                accountList.add(line);
                                if (username1.equals(line)) {
                                    counter[0]++;
                                }
                                line = bfr.readLine();
                            }
                            bfr.close();
                            if (counter[0] == 0) { //when logging in, username doesn't exist
                                System.out.println(USERNAMEDOESNOTEXIST);
                                break;
                            } else {
                                System.out.println(LOGINPASSWORD);
                                String password = scanner.nextLine();
                                File f2 = new File("StudentPassword.txt");
                                FileReader fr2 = new FileReader(f2);
                                BufferedReader bfr2 = new BufferedReader(fr2);
                                String line2 = bfr2.readLine();

                                while (line2 != null) {
                                    passwordList.add(line2);
                                    line2 = bfr2.readLine();
                                }
                                bfr2.close();
                                if (passwordList.get(accountList.indexOf(username1)).equals(password)) {
                                    return "Student " + username1;
                                } else { //catches the wrong password for an account
                                    System.out.println(WRONGPASSWORD);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (accountChoice[0] == 3) { //change existing username
                    System.out.println(LOGINUSERNAME);
                    while (usernameStatus == 0) {
                        try {
                            username1 = scanner.nextLine();
                            File f = new File("StudentAccount.txt");
                            FileReader fr = new FileReader(f);
                            BufferedReader bfr = new BufferedReader(fr);
                            String line = bfr.readLine();
                            usernameStatus = 1;
                            int[] counter = new int[1];
                            ArrayList<String> accountList = new ArrayList<>();
                            ArrayList<String> passwordList = new ArrayList<>();

                            while (line != null) {
                                accountList.add(line);
                                if (username1.equals(line)) {
                                    counter[0]++;
                                }
                                line = bfr.readLine();
                            }
                            bfr.close();
                            if (counter[0] == 0) { //catches a username that doesn't exist
                                System.out.println(USERNAMEDOESNOTEXIST);
                                break;
                            } else {
                                System.out.println(LOGINPASSWORD);
                                String password = scanner.nextLine();
                                File f2 = new File("StudentPassword.txt");
                                FileReader fr2 = new FileReader(f2);
                                BufferedReader bfr2 = new BufferedReader(fr2);
                                String line2 = bfr2.readLine();

                                while (line2 != null) {
                                    passwordList.add(line2);
                                    line2 = bfr2.readLine();
                                }
                                bfr2.close();
                                if (passwordList.get(accountList.indexOf(username1)).equals(password)) {
                                    try {
                                        System.out.println(EDITACCOUNT); //prompts user to edit account
                                        int edit = Integer.parseInt(scanner.nextLine());
                                        if (edit == 1) {
                                            System.out.println(MAKEUSERNAME);
                                            String username = scanner.nextLine();
                                            while (username.contains(" ")) {
                                                System.out.println(USERNAMEERROR);
                                                System.out.println(MAKEUSERNAME);
                                                username = scanner.nextLine();
                                            }
                                            accountList.set(accountList.indexOf(username1), username);
                                            File studentAccount = new File("StudentAccount.txt");
                                            FileOutputStream fos2 = new FileOutputStream(studentAccount, false);
                                            PrintWriter pw2 = new PrintWriter(fos2);

                                            for (int i = 0; i < accountList.size(); i++) {
                                                pw2.println(accountList.get(i));
                                            }
                                            pw2.close();
                                        } else if (edit == 2) {
                                            System.out.println(MAKEPASSWORD);
                                            String newpassword = scanner.nextLine();
                                            while (newpassword.contains(" ")) {
                                                System.out.println(PASSWORDERROR);
                                                System.out.println(MAKEPASSWORD);
                                                newpassword = scanner.nextLine();
                                            }
                                            passwordList.set(passwordList.indexOf(password), newpassword);
                                            File studentPassword = new File("StudentPassword.txt");
                                            FileOutputStream fos2 = new FileOutputStream(studentPassword,
                                                    false);
                                            PrintWriter pw2 = new PrintWriter(fos2);

                                            for (int i = 0; i < accountList.size(); i++) {
                                                pw2.println(passwordList.get(i));
                                            }
                                            pw2.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return "";
                                } else {
                                    System.out.println(WRONGPASSWORD);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        return "";

    }
    /*
    method that checks the teacher and student account/passwords
     */
    private static void checkFile() {
        File tAccount = new File("TeacherAccount.txt");
        try {
            if (tAccount.exists()) {

            } else {
                tAccount.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File tPassword = new File("TeacherPassword.txt");
        try {
            if (tPassword.exists()) {

            } else {
                tPassword.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File sAccount = new File("StudentAccount.txt");
        try {
            if (sAccount.exists()) {

            } else {
                sAccount.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File sPassword = new File("StudentPassword.txt");
        try {
            if (sPassword.exists()) {

            } else {
                sPassword.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
