import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Fen implements ActionListener{
    JFrame fen = new JFrame();
    JPanel east = new JPanel();
    JPanel west = new JPanel();
    JPanel pan = new JPanel();
    JPanel East = new JPanel();
    JPanel West = new JPanel();
    JPanel ScorePan = new JPanel();
    JPanel OtherPan = new JPanel();
    GamePan jeu;
    JButton playText = new JButton("RESET");
    //JLabel playText = new JLabel("SPACE pour PAUSE/RESUME | ECHAP pour RESET");
    JLabel text = new JLabel("JEU DU SNAKE");
    InputStream is = GamePan.class.getResourceAsStream("LLPIXEL3.ttf");
    Font arcade = Font.createFont(Font.TRUETYPE_FONT, is);
    Font arcadeFont = arcade.deriveFont(40f);
    Font arcadeFont2 = arcade.deriveFont(20f);

    public Fen() throws IOException, FontFormatException {
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(700,700);
        fen.setTitle("Jeu du serpent - Snake game");
        fen.setResizable(false);
        //this.setLayout(new BorderLayout());
        //this.add(new ScorePan(), BorderLayout.SOUTH);
        jeu = new GamePan();
        fen.add(jeu, BorderLayout.CENTER);
        east.setBackground(new Color(169, 231, 180));
        west.setBackground(new Color(169, 231, 180));
        fen.add(South(), BorderLayout.SOUTH);
        fen.add(East(), BorderLayout.EAST);
        fen.add(West(), BorderLayout.WEST);
        fen.add(North(), BorderLayout.NORTH);
        fen.pack();
        fen.setVisible(true);
        fen.setLocationRelativeTo(null);
    }
    private JPanel North() throws IOException, FontFormatException {
        text = new JLabel("Jeu du snake");
        text.setForeground(new Color(0, 0, 0));
        text.setFont(new Font("None", Font.BOLD, 30 ));
        text.setFont(arcadeFont);
        ScorePan.setBackground(new Color(169, 231, 180));
        ScorePan.add(text);
        return ScorePan;
    }

    private JPanel West() {
        West.setBackground(new Color(169, 231, 180));
        return West;
    }

    private JPanel East() {
        East.setBackground(new Color(169, 231, 180));
        return East;
    }

    private JPanel South() throws IOException, FontFormatException {
        OtherPan.setBackground(new Color(169, 231, 180));
        playText.setForeground(new Color(0,0,0));
        playText.setFont(arcadeFont2);
        //playText.setFont(new Font(null, Font.ITALIC, 20));
        playText.setBackground(new Color(169, 231, 180));

        playText.addActionListener(this);
        OtherPan.add(playText);
        return OtherPan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==playText) {
            //fen.setFocusable(true);

            System.out.println("--- Game reset");
            fen.remove(jeu);
            try {
                jeu = new GamePan();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (FontFormatException fontFormatException) {
                fontFormatException.printStackTrace();
            }
            fen.add(jeu, BorderLayout.CENTER);

            SwingUtilities.updateComponentTreeUI(fen);
            playText.setFocusable(false);

        }
    }
}
