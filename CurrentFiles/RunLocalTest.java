import org.junit.*;

import java.lang.reflect.Field;

import org.junit.runner.JUnitCore;
import org.junit.runner.OrderWith;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.MethodSorters;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Project 04 -- RunLocalTest
 * <p>
 * This class is the runLocalTest object for the quiz taking system.
 *
 * @author TIm CHou L09
 * @version April 11, 2022
 */

import static org.junit.Assert.*;


public class RunLocalTest {
    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)

    public static class TestCase {
        private final InputStream originalInput = System.in;
        private final PrintStream originalOutput = System.out;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;


        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @Test(timeout = 1000)
        public void ATestStudentMakeAccount1() {

            String input = "2\r\n1\r\nCharlie\r\n234567\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "Please enter your desired password\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String StudentAccount = "";
            try {
                FileReader fr = new FileReader("StudentAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentAccount = "Charlie";

            assertEquals("Ensure your StudentAccount.txt file output is correct",
                    expectedStudentAccount, StudentAccount);

            String StudentPassword = "";
            try {
                FileReader fr = new FileReader("StudentPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentPassword = "234567";

            assertEquals("Ensure your StudentAccount.txt file output is correct",
                    expectedStudentPassword, StudentPassword);
        }

        @Test(timeout = 1000)
        public void BTestStudentMakeAccount2() {

            String input = "2\r\n1\r\nCharlie\r\n1\r\nKevin\r\n123456\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "This username already existed!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "Please enter your desired password\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String StudentAccount = "";
            try {
                FileReader fr = new FileReader("StudentAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentAccount = "CharlieKevin";

            assertEquals("Ensure your StudentAccount.txt file output is correct",
                    expectedStudentAccount, StudentAccount);

            String StudentPassword = "";
            try {
                FileReader fr = new FileReader("StudentPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentPassword = "234567123456";

            assertEquals("Ensure your StudentAccount.txt file output is correct",
                    expectedStudentPassword, StudentPassword);
        }

        @Test(timeout = 1000)
        public void CTestTeacherMakeAccount1() {

            String input = "1\r\n1\r\nFrank\r\n123456\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "Please enter your desired password\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String TeacherAccount = "";
            try {
                FileReader fr = new FileReader("TeacherAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherAccount = "Frank";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherAccount, TeacherAccount);

            String TeacherPassword = "";
            try {
                FileReader fr = new FileReader("TeacherPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherPassword = "123456";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherPassword, TeacherPassword);
        }

        @Test(timeout = 1000)
        public void DTestTeacherMakeAccount2() {

            String input = "1\r\n1\r\nFrank\r\n1\r\nAlyssa\r\n234567\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "This username already existed!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "Please enter your desired password\n";
            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String TeacherAccount = "";
            try {
                FileReader fr = new FileReader("TeacherAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherAccount = "FrankAlyssa";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherAccount, TeacherAccount);

            String TeacherPassword = "";
            try {
                FileReader fr = new FileReader("TeacherPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherPassword = "123456234567";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherPassword, TeacherPassword);
        }

        @Test(timeout = 1000)
        public void ETestTeacherEditAccountName() {

            String input = "1\r\n1\r\nAlyssa\r\n3\r\nSky\r\n3\r\nAlyssa\r\n123456\r\n3\r\nAlyssa\r\n234567\r\n1\r\nCloud9\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "This username already existed!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "This username does not exist!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "That's the wrong password!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to edit?\n" +
                    "1. Account name\n" +
                    "2. Password\n" +
                    "Please enter your desired username\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String TeacherAccount = "";
            try {
                FileReader fr = new FileReader("TeacherAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherAccount = "FrankCloud9";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherAccount, TeacherAccount);

            String TeacherPassword = "";
            try {
                FileReader fr = new FileReader("TeacherPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherPassword = "123456234567";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherPassword, TeacherPassword);
        }

        @Test(timeout = 1000)

        public void FTestStudentEditAccountName() {

            String input = "2\r\n1\r\nCharlie\r\n3\r\nSky\r\n3\r\nCharlie\r\n123456\r\n3\r\nCharlie\r\n234567\r\n1\r\nCharlie1\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "This username already existed!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "This username does not exist!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "That's the wrong password!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to edit?\n" +
                    "1. Account name\n" +
                    "2. Password\n" +
                    "Please enter your desired username\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String StudentAccount = "";
            try {
                FileReader fr = new FileReader("StudentAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentAccount = "Charlie1Kevin";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedStudentAccount, StudentAccount);

            String StudentPassword = "";
            try {
                FileReader fr = new FileReader("StudentPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentPassword = "234567123456";

            assertEquals("Ensure your StudentAccount.txt file output is correct",
                    expectedStudentPassword, StudentPassword);
        }

        @Test(timeout = 1000)

        public void GTestTeacherEditPassword() {

            String input = "1\r\n1\r\nCloud9\r\n2\r\nFrank1\r\n3\r\nAlyssa\r\n3\r\nFrank\r\n234567\r\n3\r\nFrank\r\n123456\r\n2\r\n234567\r\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "This username already existed!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "This username does not exist!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "This username does not exist!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "That's the wrong password!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to edit?\n" +
                    "1. Account name\n" +
                    "2. Password\n" +
                    "Please enter your desired password\n";
            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String TeacherAccount = "";
            try {
                FileReader fr = new FileReader("TeacherAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherAccount = "FrankCloud9";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherAccount, TeacherAccount);

            String TeacherPassword = "";
            try {
                FileReader fr = new FileReader("TeacherPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    TeacherPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedTeacherPassword = "234567234567";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedTeacherPassword, TeacherPassword);
        }

        @Test(timeout = 1000)

        public void HTestStudentEditPassword() {

            String input = "2\r\n1\r\nCharlie1\r\n2\r\nKevin1\r\n3\r\nCharlie\r\n3\r\nKevin\r\n234567\r\n3\r\nKevin\r\n123456\r\n2\r\n234567\r\n";
            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your desired username\n" +
                    "This username already existed!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "This username does not exist!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "This username does not exist!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "That's the wrong password!\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to edit?\n" +
                    "1. Account name\n" +
                    "2. Password\n" +
                    "Please enter your desired password\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);

            String StudentAccount = "";
            try {
                FileReader fr = new FileReader("StudentAccount.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentAccount += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentAccount = "Charlie1Kevin";

            assertEquals("Ensure your TeacherAccount.txt file output is correct",
                    expectedStudentAccount, StudentAccount);

            String StudentPassword = "";
            try {
                FileReader fr = new FileReader("StudentPassword.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    StudentPassword += line;
                    line = br.readLine();
                }
                fr.close();
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("Unexpected Exception!");
            }

            String expectedStudentPassword = "234567234567";

            assertEquals("Ensure your StudentAccount.txt file output is correct",
                    expectedStudentPassword, StudentPassword);
        }


        @Test(timeout = 1000)
        public void ITeacherCreateCourse() {
            String input = "1\n2\nCloud9\n234567\n6\nMath\n8\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Enter the name of the new course or press N to exit\n" +
                    "Course added successfully\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Goodbye!\n";
            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);
        }

        @Test(timeout = 1000)
        public void JCreateQuiz() {

            String input = "1\r\n2\r\nCloud9\r\n234567\r\n1\r\nMath\r\nQuiz 1\n1\nN\n1\nWhat is 1 + 1?\n0\n1\n2\n3\nC\n1\nWhat is 2 + 2?\n3\n4\n5\n6\nB\n2\n8\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Enter the course name or press N to exit\n" +
                    "Enter the name of the quiz or press N to exit\n" +
                    "Do you want to create a quiz or import a quiz? Press N to exit\n" +
                    "1: Create\n" +
                    "2: Import\n" +
                    "Do you want the quiz to be randomized? Y/N. Press E to exit.\n" +
                    "Do you want to add a question?\n" +
                    "1.Yes\n" +
                    "2.No\n" +
                    "Please type question 1\n" +
                    "Please type answer option A\n" +
                    "Please type answer option B\n" +
                    "Please type answer option C\n" +
                    "Please type answer option D\n" +
                    "Please enter the correct answer choice A, B, C or D\n" +
                    "Do you want to add another question?\n" +
                    "1.Yes\n" +
                    "2.No\n" +
                    "Please type question 2\n" +
                    "Please type answer option A\n" +
                    "Please type answer option B\n" +
                    "Please type answer option C\n" +
                    "Please type answer option D\n" +
                    "Please enter the correct answer choice A, B, C or D\n" +
                    "Do you want to add another question?\n" +
                    "1.Yes\n" +
                    "2.No\n" +
                    "The quiz is completed\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Goodbye!\n";

            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);
        }

        @Test(timeout = 1000)

        public void KStudentTakeQuizNormal() {

            String input = "2\r\n2\r\nKevin\r\n234567\r\nCloud9\r\n1\n1\n2\nC\nB\n1\n1\n2\n2\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What is the username of your teacher?\n" +
                    "Course Options:\n" +
                    "1. Math\n" +
                    "2. Exit\n" +
                    "Please select which course you would like to take?\n" +
                    "Quiz Options:\n" +
                    "1. Quiz 1\n" +
                    "2. Exit\n" +
                    "Please select which quiz you would like to take?\n" +
                    "Quiz 1\n" +
                    "1. What is 1 + 1?\n" +
                    "A. 0\n" +
                    "B. 1\n" +
                    "C. 2\n" +
                    "D. 3\n" +
                    "2. What is 2 + 2?\n" +
                    "A. 3\n" +
                    "B. 4\n" +
                    "C. 5\n" +
                    "D. 6\n" +
                    "Would you like to attach a file with answers or answer in the quiz?\n" +
                    "1. attach a file\n" +
                    "2. answer in the quiz\n" +
                    "What is the answer to question 1:\n" +
                    "What is the answer to question 2:\n" +
                    "Thanks for taking the quiz!\n" +
                    "Course Options:\n" +
                    "1. Math\n" +
                    "2. Exit\n" +
                    "Please select which course you would like to take?\n" +
                    "Quiz Options:\n" +
                    "1. Quiz 1\n" +
                    "2. Exit\n" +
                    "Please select which quiz you would like to take?\n" +
                    "Quiz 1\n" +
                    "Quiz already taken\n" +
                    "1. View Quiz Grade\n" +
                    "2. Exit Back to Courses\n" +
                    "Course Options:\n" +
                    "1. Math\n" +
                    "2. Exit\n" +
                    "Please select which course you would like to take?\n";
            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);
        }


        @Test(timeout = 1000)
        public void LMakeRandomizedTest() {

            try {

                File test = new File("Lazy.txt");
                test.createNewFile();
                FileOutputStream fos = new FileOutputStream(test, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println("False");
                pw.println("Who drew Mona Lisa?");
                pw.println("A: Leonardo da Vinci");
                pw.println("B: Pablo Picasso");
                pw.println("C: Vincent van Gogh");
                pw.println("D: Salvador Dalí");
                pw.println("Who drew Guernica?");
                pw.println("A: Leonardo da Vinci");
                pw.println("B: Pablo Picasso");
                pw.println("C: Vincent van Gogh");
                pw.println("D: Salvador Dalí");
                pw.println("Who drew The Starry Night?");
                pw.println("A: Leonardo da Vinci");
                pw.println("B: Pablo Picasso");
                pw.println("C: Vincent van Gogh");
                pw.println("D: Salvador Dalí");
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String input = "1\r\n2\r\nCloud9\r\n234567\r\n6\nArt\r\n1\nArt\nQuiz 2\n2\nLazy.txt\n8\n";

            receiveInput(input);

            try {
                MainMethod.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                fail("Ensure your program handles file reading and writing correctly and has the correct number of scanner calls!");
            }

            String out = getOutput();

            String expectedFull = "Please choose a login type\n" +
                    "1. Teacher\n" +
                    "2. Student\n" +
                    "How would you like to proceed?\n" +
                    "1. Make a new account\n" +
                    "2. Login with existing account\n" +
                    "3. Edit existing account\n" +
                    "Please enter your username\n" +
                    "Please enter your password\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Enter the name of the new course or press N to exit\n" +
                    "Course added successfully\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Enter the course name or press N to exit\n" +
                    "Enter the name of the quiz or press N to exit\n" +
                    "Do you want to create a quiz or import a quiz? Press N to exit\n" +
                    "1: Create\n" +
                    "2: Import\n" +
                    "What is the filepath of the quiz?\n" +
                    "The quiz has been created\n" +
                    "What would you like to do?\n" +
                    "1. Create Quiz\n" +
                    "2. Edit Quiz\n" +
                    "3. Delete Quiz\n" +
                    "4. Grade Quiz\n" +
                    "5. View Student Submission\n" +
                    "6. Create Course\n" +
                    "7. Delete Course\n" +
                    "8. Exit\n" +
                    "Goodbye!\n";
            assertEquals("Ensure your Main.java output contains the correct information!", expectedFull, out);
        }


        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }
    }
}

