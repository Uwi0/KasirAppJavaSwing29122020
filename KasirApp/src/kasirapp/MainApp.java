/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String angkaPersen = "20%";
        String[] angka = angkaPersen.split("%");
        System.out.println(angka[0]);
    }
}
