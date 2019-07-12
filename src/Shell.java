import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

public class Shell {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char quo = '"';

        while (true) {
            System.out.print("1_Java_Shell ~ $ ");
            String buffer = in.nextLine();

            if (buffer.equals("logout")) {
                System.out.println("\nlogging off...");
                break;
            }

            // Parsing(Breaking down the cli arguments)
            String[] buffer_array = function_parse(buffer);

            // Authentication of first argument.
            if (function_authenticate(buffer_array) != 1) {
                System.out.println("Java_Shell: " + quo + buffer + quo + " - Not a Valid Command");
            }

            function_execute(buffer_array);

        }


    }

    private static String[] function_parse(String line) {
        return line.split(" ");
    }

    private static int function_authenticate(String[] line_arr) {
        if (!(line_arr[0].equals("cd")
                || line_arr[0].equals("ls")
                || line_arr[0].equals("rm")
                || line_arr[0].equals("mv")
                || line_arr[0].equals("mkdir")
                || line_arr[0].equals("rmdir")
                || line_arr[0].equals("clear")
                || line_arr[0].equals("pwd")
                || line_arr[0].equals("echo")
                || line_arr[0].equals("cat")
                || line_arr[0].equals("touch"))) {
            return 0;
        }
        return 1;
    }

    private static void function_mv(String[] args) {

    }

    private static void function_mkdir(String[] args) {

    }

    private static void function_rmdir(String[] args) {

    }

    private static void function_touch(String[] args) {


    }

    private static void function_cd(String[] args) { // TODO: This still needs to be fixed
        try {
            Process proc = Runtime.getRuntime().exec("cd " + args[1]);
        } catch (Exception e) {
            System.out.println("cd failed. (line 81)");
        }


    }

    private static void function_ls() {
        File directory = new File(System.getProperty("user.dir"));
        String[] files = directory.list();
        System.out.println("Contents of " + System.getProperty("user.dir") + ": ");
        assert files != null;
        for (String file : files) {
            System.out.println("- \t" + file);
        }
    }

    private static void function_rm(String[] args) {
        File file = new File(args[1]);
        char q = '"';

        if (file.delete()) {
            System.out.println("File " + q + args[1] + q +  " has been deleted.");
        } else {
            System.out.println("File could not be deleted.");
        }
    }

    private static void function_clear() {
        int num_Lines_on_screen = 48;
        for (int i = 0; i < num_Lines_on_screen ; i++) {
            System.out.println();
        }

    }

    private static void function_pwd() {
        // This system function is retrieving the pwd
        String pwd_string = System.getProperty("user.dir");
        System.out.println("Current Working Directory: " + pwd_string);
    }

    private static void function_echo(String[] args) {
        // This system function is retrieving the pwd
        for (int i = 1; i < args.length - 1; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println(args[args.length - 1]);
    }

    private static void function_cat(String[] args) { // check if this works
        try {
            File file = new File(args[1]);

            if (!file.exists()) {
                System.out.println("File Does Not Exist.");
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(args[1] + " - Could Not be Read.");
        }

    }

    private static void function_execute(String[] line_arr) {
        if (line_arr[0].equals("cd")) {
            function_cd(line_arr);
        }
        if (line_arr[0].equals("ls")) { // TODO: Completed
            function_ls();
        }
        if (line_arr[0].equals("rm")) { // TODO: Completed
            function_rm(line_arr);
        }
        if (line_arr[0].equals("mv")) {
            function_mv(line_arr);
        }
        if (line_arr[0].equals("mkdir")) {
            function_mkdir(line_arr);
        }
        if (line_arr[0].equals("rmdir")) {
            function_rmdir(line_arr);
        }
        if (line_arr[0].equals("clear")) { //TODO: This does not reset the cli to the top of the screen
            function_clear();
        }
        if (line_arr[0].equals("pwd")) { // TODO: Completed
            function_pwd();
        }
        if (line_arr[0].equals("echo")) { // TODO: Completed
            function_echo(line_arr);
        }
        if (line_arr[0].equals("cat")) { // TODO: This needs to be checked.
            function_cat(line_arr);
        }
        if (line_arr[0].equals("touch")) { // TODO: Check if this works.
            function_touch(line_arr);
        }

    }
}
