/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClass;

import javax.swing.SwingUtilities;
import view.MainFOrm;

/**
 *
 * @author USER
 */
public class MainClass {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MainFOrm().setVisible(true);
        });

    }
}
