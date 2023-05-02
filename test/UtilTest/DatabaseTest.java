/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UtilTest;

import java.sql.Connection;
import util.Database;

/**
 *
 * @author Hafidz Fadhillah
 */
public class DatabaseTest {
    public static void main(String[] args) {
        Connection conn = Database.getConnection();
    }
}
