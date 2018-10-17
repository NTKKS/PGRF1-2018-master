package cz.uhk.fim.pixeltest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PixelTest {

    private JFrame window; // hlavní okno
    private BufferedImage img; // objekt pro zápis pixelů
    private Canvas canvas; // plátno pro vykreslení BufferedImage
    private Renderer renderer; //implementace vykreslovacich algoritmu
    private VertexPos vertexPos; //pro ukladani vrcholu n-uhelniku
    private Circle circle; // pro ukladani vrcholu vepsanych kruznici
    private SeedFiller seedFiller;

    public PixelTest() {
        window = new JFrame();
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(800, 600); // velikost okna
        window.setLocationRelativeTo(null);// vycentrovat okno
        window.setTitle("PGRF1 cvičení"); // titulek okna

        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        // inicializace plátna, do kterého budeme kreslit img
        canvas = new Canvas();

        window.add(canvas); // vložit plátno do okna
        window.setVisible(true); // zobrazit okno

        //inicializace
        renderer = new Renderer(img, canvas);
        vertexPos = new VertexPos();
        circle = new Circle();
        seedFiller = new SeedFiller();
        seedFiller.setBufferedImage(img);

        //zkusebni vykresleni
        //renderer.drawPixel(100, 50, Color.GREEN.getRGB());
        // 0x00ff00 == Color.GREEN.getRGB()
        //renderer.drawLine(0,1,8,4,0xffff00);

        //nGon klikanim
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //nastaveni pro klikani levym tlacitkem mysi
                if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {

                    //vykresleni jednoho pixelu kliknutím
                    renderer.drawPixel(e.getX(), e.getY(), 0xffffff);
                    //pridej vrchol do listu
                    vertexPos.addPos(e.getX(), e.getY());
                    //po zadani druheho vrcholu
                    if (vertexPos.ready()) {
                        renderer.clear();
                        //dalsi vrchol
                        renderer.drawLineDDA(vertexPos.getX(), vertexPos.getY(), e.getX(), e.getY(), 0xffffff);
                        //uzavri n-uhelnik
                        renderer.drawLineDDA(e.getX(), e.getY(), vertexPos.getX(0), vertexPos.getY(1), 0xffffff);
                        //prekresli dosavadni cestu
                        int cycle = ((vertexPos.getSize() / 2) - 1);
                        for (int i = 0; i < cycle; i++) {
                            renderer.drawLineDDA(vertexPos.getX(0 + i * 2), vertexPos.getY(1 + i * 2), vertexPos.getX(2 + i * 2), vertexPos.getY(3 + i * 2), 0xffffff);
                        }

                    }
                }
            }
        });

        //nGon tazenim
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent f) {
                //nastaveni pro klikani levym tlacitkem mysi
                if (f.getModifiers() == MouseEvent.BUTTON1_MASK) {
                    //prvni hrana
                    if (vertexPos.getSize() == 0) {
                        vertexPos.addPos(f.getX(), f.getY());
                    }
                }
                canvas.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        //nastaveni pro klikani levym tlacitkem mysi
                        if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                            renderer.clear();
                            if (vertexPos.getSize() <= 2) {
                                renderer.drawLineDDA(vertexPos.getX(0), vertexPos.getY(1), e.getX(), e.getY(), 0xffffff);
                            } else {
                                //natahni dalsi lajnu
                                renderer.drawLineDDA(vertexPos.lastX(), vertexPos.lastY(), e.getX(), e.getY(), 0xff0000);
                                //uzavri n-uhelnik
                                renderer.drawLineDDA(e.getX(), e.getY(), vertexPos.getX(0), vertexPos.getY(1), 0xff0000);
                                //prekresli dosavadni hrany
                                int cycle = ((vertexPos.getSize() / 2) - 1);
                                for (int i = 0; i < cycle; i++) {
                                    renderer.drawLineDDA(vertexPos.getX(0 + i * 2), vertexPos.getY(1 + i * 2), vertexPos.getX(2 + i * 2), vertexPos.getY(3 + i * 2), 0xffffff);
                                }
                            }
                        }
                    }
                });
            }
        });

        //nGon zadany trema body klikanim
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent f) {
                //nastaveni pro klikani pravym tlacitkem mysi
                if (f.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    //pridani pocatku
                    if (circle.clickCount() == 0) {
                        circle.addPoint(f.getX(), f.getY());
                        circle.click();
                    }
                    //nastaveni polomeru a uhlu
                    else if (circle.clickCount() == 1) {
                        circle.click();
                        circle.addPoint(f.getX(), f.getY());
                        renderer.drawLineDDA(circle.getX(0), circle.getY(0), circle.getX(1), circle.getY(1), 0x0000ff);
                    }
                    //vykresleni nGonu
                    else if (circle.clickCount() == 2) {
                        //smaze caru polomeru, vypocte vrcholy
                        circle.getVert();
                        renderer.clear();
                        //vykresleni vrcholu
                        for (int i = 2; i <= 2; i++) {
                            renderer.drawPixel(circle.getX(i), circle.getY(i), 0xffffff);
                        }
                        //spojeni vrcholu carou
                        for (int i = 1; i < circle.getSize() - 1; i++) {
                            renderer.drawLineDDA(circle.getX(i), circle.getY(i), circle.getX(i + 1), circle.getY(i + 1), 0xff00ff);
                        }
                        renderer.drawLineDDA(circle.getX(circle.getSize() - 1), circle.getY(circle.getSize() - 1), circle.getX(1), circle.getY(1), 0xff00ff);

                    } /*else {
                        //SeedFiller vyplneni barvou (wip)
                        seedFiller.init(f.getX(), f.getY(), 0xff00ff);
                        seedFiller.fill();
                    }*/

                }
                //prekreslovani cary pohybem mysi
                canvas.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        if (circle.clickCount() == 1) {
                            renderer.clear();
                            renderer.drawLineDDA(circle.getX(0), circle.getY(0), e.getX(), e.getY(), 0xff00ff);
                        }
                    }
                });
            }
        });

        //cisteni canvasu a seznamu vrcholu
        canvas.addKeyListener(new

                                      KeyAdapter() {
                                          @Override
                                          public void keyPressed(KeyEvent e) {
                                              //pri zmacknuti klavesy C se vymaže canvas
                                              if (e.getKeyCode() == KeyEvent.VK_C) {
                                                  renderer.clear();
                                                  vertexPos.clear();
                                                  circle.clear();
                                              }
                                          }
                                      });

        //zvyseni poctu vrcholu sipkou nahoru
        canvas.addKeyListener(new

                                      KeyAdapter() {
                                          @Override
                                          public void keyPressed(KeyEvent e) {
                                              //pri zmacknuti klavesy C se vymaže canvas
                                              if (e.getKeyCode() == KeyEvent.VK_UP) {
                                                  circle.setN(1);
                                              }
                                          }
                                      });
        //snizeni poctu vrcholu sipkou dolu
        canvas.addKeyListener(new

                                      KeyAdapter() {
                                          @Override
                                          public void keyPressed(KeyEvent e) {
                                              //pri zmacknuti klavesy C se vymaže canvas
                                              if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                                  if (circle.getN() > 3) {
                                                      circle.setN(-1);
                                                  }
                                              }
                                          }
                                      });
        canvas.requestFocus();
    }

    private void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
        // říct plátnu, aby zobrazil aktuální img
        canvas.getGraphics().drawImage(img, 0, 0, null);
        // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(PixelTest::new);
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
        // https://www.google.com/search?q=java+double+colon
    }
}
