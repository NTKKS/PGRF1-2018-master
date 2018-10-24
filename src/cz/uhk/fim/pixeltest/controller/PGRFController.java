package cz.uhk.fim.pixeltest.controller;

import cz.uhk.fim.pixeltest.Circle;
import cz.uhk.fim.pixeltest.VertexPos;
import cz.uhk.fim.pixeltest.fill.SeedFiller;
import cz.uhk.fim.pixeltest.renderer.Renderer;
import cz.uhk.fim.pixeltest.view.Raster;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PGRFController {

    private VertexPos vertexPos; //pro ukladani vrcholu n-uhelniku
    private Circle circle; // pro ukladani vrcholu vepsanych kruznici
    private Raster raster;
    private Renderer renderer; //implementace vykreslovacich algoritmu
    private SeedFiller seedFiller;

    public PGRFController(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();

        //inicializace
        renderer = new Renderer(raster);
        vertexPos = new VertexPos();
        circle = new Circle();
        seedFiller = new SeedFiller();
        //seedFiller.setBufferedImage(img);

        //zkusebni vykresleni
        //renderer.drawPixel(100, 50, Color.GREEN.getRGB());
        // 0x00ff00 == Color.GREEN.getRGB()
        //renderer.drawLine(0,1,8,4,0xffff00);

    }

    private void initListeners() {
        //nGon klikanim
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //nastaveni pro klikani levym tlacitkem mysi
                if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {

                    //vykresleni jednoho pixelu kliknutím
                    raster.drawPixel(e.getX(), e.getY(), 0xffffff);
                    //pridej vrchol do listu
                    vertexPos.addPos(e.getX(), e.getY());
                    //po zadani druheho vrcholu
                    if (vertexPos.ready()) {
                        raster.clear();
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
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent f) {
                //nastaveni pro klikani levym tlacitkem mysi
                if (f.getModifiers() == MouseEvent.BUTTON1_MASK) {
                    //prvni hrana
                    if (vertexPos.getSize() == 0) {
                        vertexPos.addPos(f.getX(), f.getY());
                    }
                }
                //neni vhodne cpat listenery do sebe, dat je pod sebe
                //vytvorit promenout bool
                raster.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        //nastaveni pro klikani levym tlacitkem mysi
                        if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                            raster.clear();
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
        raster.addMouseListener(new MouseAdapter() {
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
                        circle.click();
                        circle.getVert();
                        raster.clear();
                        //vykresleni vrcholu
                        for (int i = 2; i <= 2; i++) {
                            raster.drawPixel(circle.getX(i), circle.getY(i), 0xffffff);
                        }
                        //spojeni vrcholu carou
                        for (int i = 1; i < circle.getSize() - 1; i++) {
                            renderer.drawLineDDA(circle.getX(i), circle.getY(i), circle.getX(i + 1), circle.getY(i + 1), 0xff00ff);
                        }
                        renderer.drawLineDDA(circle.getX(circle.getSize() - 1), circle.getY(circle.getSize() - 1), circle.getX(1), circle.getY(1), 0xff00ff);

                    } else if (circle.clickCount() == 3) {
                        //SeedFiller vyplneni barvou (wip)
                        circle.click();
                        seedFiller.init(f.getX(), f.getY(), 0xff00ff);
                        seedFiller.fill();
                    }

                }
                //prekreslovani cary pohybem mysi
                raster.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        if (circle.clickCount() == 1) {
                            raster.clear();
                            renderer.drawLineDDA(circle.getX(0), circle.getY(0), e.getX(), e.getY(), 0xff00ff);
                        }
                    }
                });
            }
        });

        //cisteni canvasu a seznamu vrcholu
        raster.addKeyListener(new

                                      KeyAdapter() {
                                          @Override
                                          public void keyPressed(KeyEvent e) {
                                              //pri zmacknuti klavesy C se vymaže canvas
                                              if (e.getKeyCode() == KeyEvent.VK_C) {
                                                  raster.clear();
                                                  vertexPos.clear();
                                                  circle.clear();
                                              }
                                          }
                                      });

        //zvyseni poctu vrcholu sipkou nahoru
        raster.addKeyListener(new

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
        raster.addKeyListener(new

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
        raster.requestFocus();
    }



    private void initObjects() {
        renderer = new Renderer(raster);

        seedFiller = new SeedFiller();
        seedFiller.setRaster(raster);
    }
/*
    private void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
        // říct plátnu, aby zobrazil aktuální img
        canvas.getGraphics().drawImage(img, 0, 0, null);
        // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
    }
*/

}
