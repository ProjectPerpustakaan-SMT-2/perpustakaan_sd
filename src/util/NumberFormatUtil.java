/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Hafidz Fadhillah
 */
public class NumberFormatUtil {

    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }
}
