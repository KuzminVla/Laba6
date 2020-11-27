package Utility;

import com.sun.deploy.security.SelectableSecurityManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    static Scanner in = new Scanner(System.in);


    public static String read(){
        System.out.print("~ ");
        return in.nextLine();
    }


}