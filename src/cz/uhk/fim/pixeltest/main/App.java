package cz.uhk.fim.pixeltest.main;

import cz.uhk.fim.pixeltest.controller.Controller3D;
import cz.uhk.fim.pixeltest.view.PGRFWindow;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PGRFWindow window = new PGRFWindow();
            //new PGRFController(window);
            new Controller3D(window.getRaster());
            window.setVisible(true);
        });
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
        // https://www.google.com/search?q=java+double+colon
    }
}
