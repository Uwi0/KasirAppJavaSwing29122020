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
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        String uniqueId = dateFormat.format(date);
        System.out.println("D"+uniqueId+"A" + 1000);
        
        int max = 100;
        int min = 50;
        
        int angka = (int)(Math.random() * (max - min + 1) + min);
        
        System.out.println(angka);
    }
}
