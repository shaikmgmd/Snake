import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePan extends JPanel implements ActionListener{
    final int LARG = 600;
    final int LONG = 600;
    final int TAILLE_P = 25;
    final int lenY = LONG/TAILLE_P;
    final int lenX = LARG/TAILLE_P;
    final int TAILLE_PF = ((LARG*LONG)/TAILLE_P);
    int VITESSE = 75;
    final int[] x = new int[TAILLE_PF];
    final int[] y = new int[TAILLE_PF];
    int serp = 6;
    int points;
    int pointsX;
    int pointsY;
    char direction = 'D';
    boolean etat = false;
    Timer timer;
    Random random;
    static boolean gameOn = false;
    InputStream is = GamePan.class.getResourceAsStream("LLPIXEL3.ttf");
    Font arcade = Font.createFont(Font.TRUETYPE_FONT, is);
    Font arcadeFont = arcade.deriveFont(40f);
    Font arcadeFont2 = arcade.deriveFont(20f);



    public GamePan() throws IOException, FontFormatException {
        random = new Random();
        this.setPreferredSize(new Dimension(LARG,LONG));
        this.setBackground(new Color(20, 144, 47));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setLayout(new BorderLayout());
        requestFocus();

        //requestFocusInWindow();
        debutJeu();
    }

    public void debutJeu() {
        newpoint();
        etat = true;
        timer = new Timer(VITESSE, this);
        timer.start();
    }
    public int getPoint() {
        return points;
    }
    public void newpoint() {
        pointsX= random.nextInt((int)(LARG/TAILLE_P))*TAILLE_P;
        pointsY= random.nextInt((int)(LONG/TAILLE_P))*TAILLE_P;
        System.out.println("--- Nouvelle pomme sur le terrain");
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
         if (etat) {
             int i = 0;
             g.setColor(Color.RED);
             g.fillOval(pointsX, pointsY, TAILLE_P, TAILLE_P);

             for (i = 0; i < serp; i++) {
                 if (i == 0) {
                     g.setColor(new Color(0x000000));
                     g.fillRect(x[i], y[i], TAILLE_P, TAILLE_P);
                 } else {
                     g.setColor(new Color(0x111111));
                     g.fillRect(x[i], y[i], TAILLE_P, TAILLE_P);
                 }
             }
             g.setColor(Color.BLACK);
             g.setFont(new Font("Arial", Font.BOLD, 35));
             FontMetrics metrics = getFontMetrics(g.getFont());
             g.drawString(" "+points, (LARG- metrics.stringWidth(" "+points)), g.getFont().getSize());
         }
         else {
             finJeu(g);
         }
    }

    public void mouvement() {
        int i=serp;
        while (i>0) {
            x[i]=x[i-1];
            y[i]=y[i-1];
            i--;
        }
        if(direction=='H'){
            y[0]= y[0]-TAILLE_P;
        }
        if(direction=='B'){
            y[0]= y[0]+TAILLE_P;
        }
        if(direction=='G'){
            x[0]= x[0]-TAILLE_P;
        }
        if(direction=='D'){
            x[0]= x[0]+TAILLE_P;
        }
    }

    public void verifPoint() {
        if((x[0] == pointsX) && (y[0] == pointsY)) {
            serp=serp+1;
            points=points+1;
            System.out.println("--- Pomme mangée");
            newpoint();
        }
    }

    public void verifMur() {
        int i=serp;
        while (i>0) {
            if ((x[0]==x[i]) && (y[0]==y[i])) {
                etat=false;
            }
            i--;
        }
        if(x[0]<0) {
            etat=false;
        }
        if(x[0]>LARG) {
            etat=false;
        }
        if(y[0]<0) {
            etat=false;
        }
        if(y[0]>LONG) {
            etat=false;
        }

        if (!etat) {
            timer.stop();
        }

    }
    public void pause() {
        gameOn = true;
        timer.stop();
    }
    public void resume() {
        gameOn = false;
        timer.start();
    }
    public void finJeu(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Poppins", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game over", (LARG- metrics.stringWidth("Game over"))/2, LONG/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (etat) {
            mouvement();
            verifPoint();
            verifMur();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_LEFT && direction != 'D') {
                requestFocus();
                direction = 'G';
                System.out.println("--- Gauche");
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT && direction != 'G') {
                //requestFocusInWindow();
                requestFocus();
                direction = 'D';
                System.out.println("--- Droite");
            }
            if(e.getKeyCode()==KeyEvent.VK_UP && direction != 'B') {
                //requestFocusInWindow();
                requestFocus();
                direction = 'H';
                System.out.println("--- Haut");
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN && direction != 'H') {
                //requestFocusInWindow();
                requestFocus();
                direction = 'B';
                System.out.println("--- Bas");
            }
            if(e.getKeyCode()==KeyEvent.VK_SPACE && gameOn) {
                requestFocus();
                resume();
                System.out.println("--- Jeu en pause");
            }
            else if(e.getKeyCode()==KeyEvent.VK_SPACE && !gameOn) {
                requestFocus();
                pause();
                System.out.println("--- Reprise du jeu");
            }
            /*
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
                if (!etat) {
                    try {
                        new GamePan();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (FontFormatException fontFormatException) {
                        fontFormatException.printStackTrace();
                    }
                    System.out.println("--- Reset game");
                }
            }
            */

        }
        }
    }


