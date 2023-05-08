package util;

import javax.swing.JTable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hafidz Fadhillah
 */
public class ViewUtil {
    public static <T extends JTable> void hideTableColumn(T table, int index) {
        table.getColumnModel().getColumn(index).setWidth(0);
        table.getColumnModel().getColumn(index).setMaxWidth(0);
        table.getColumnModel().getColumn(index).setMinWidth(0);
        table.getColumnModel().getColumn(index).setPreferredWidth(0);
    }
}
