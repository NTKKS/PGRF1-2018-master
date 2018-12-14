package cz.uhk.fim.pixeltest.view;

import javax.swing.*;
import java.awt.*;

public class PGRFWindow extends JFrame {

    private Raster raster;

    public PGRFWindow() {
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Raster.WIDTH, Raster.HEIGHT+65); // velikost okna
        setLocationRelativeTo(null);// vycentrovat okno
        setTitle("PGRF1 cvičení"); // titulek okna
        setLayout(new BorderLayout());

        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus();

        JPanel pnlObj = new JPanel();
        pnlObj.setLayout(new FlowLayout());
        pnlObj.setBackground(Color.BLACK);

        JLabel lblPrimitive = new JLabel();
        lblPrimitive.setForeground(Color.ORANGE);
        lblPrimitive.setText("Primitives - Pyramid,Cube | ");
        pnlObj.add(lblPrimitive);
        JLabel lblCubic = new JLabel();
        lblCubic.setForeground(Color.CYAN);
        lblCubic.setText("Cubic3D - Coons,Bezier,Ferguson | ");
        pnlObj.add(lblCubic);
        JLabel lblSpherical = new JLabel();
        lblSpherical.setForeground(Color.GREEN);
        lblSpherical.setText("SphericalHelix | ");
        pnlObj.add(lblSpherical);
        JLabel lblCylindrical = new JLabel();
        lblCylindrical.setForeground(Color.MAGENTA);
        lblCylindrical.setText("Cylindrical | ");
        pnlObj.add(lblCylindrical);
        JLabel lblSin = new JLabel();
        lblSin.setForeground(Color.YELLOW);
        lblSin.setText("Cartheasian Sinusoid");
        pnlObj.add(lblSin);

        JPanel pnlTips = new JPanel();
        pnlTips.setBackground(Color.BLACK);
        JLabel lblTips = new JLabel();
        lblTips.setForeground(Color.GRAY);
        lblTips.setText("Look: LMB | Move: WSAD | Rotate: RMB | Zoom: Scroll | ScaleXY: CTRL+RMB | Translate: MMB | Persp/Ortho : P | Cam reset: R");
        pnlTips.add(lblTips);

        add(pnlObj,BorderLayout.NORTH);
        add(raster,BorderLayout.CENTER);
        add(pnlTips,BorderLayout.SOUTH);
    }

    public Raster getRaster() {
        return raster;
    }
}
