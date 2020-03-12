/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fac.gui.helper;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author susmi
 */
public class TableRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!table.isRowSelected(row)) {
            if (!table.getModel().getValueAt(row, column).equals("0") && column > 0) {
                c.setBackground(new java.awt.Color(153, 102, 0));
            } else {
                c.setBackground(table.getBackground());
            }
        }

        return c;
    }
}
