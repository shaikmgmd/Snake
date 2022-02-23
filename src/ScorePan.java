import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ScorePan extends GamePan {
    JLabel text;
    JButton play;
    JPanel span = new JPanel();
    ScorePan() throws IOException, FontFormatException {
        text = new JLabel("Jeu du snake");
        InputStream is = GamePan.class.getResourceAsStream("LLPIXEL3.ttf");
        Font arcade = Font.createFont(Font.TRUETYPE_FONT, is);
        Font arcadeFont = arcade.deriveFont(40f);
        text.setForeground(new Color(0, 0, 0));
        text.setFont(new Font("Rixel", Font.BOLD, 30 ));
        text.setFont(arcadeFont);
        span.setBackground(new Color(169, 231, 180));
        play = new JButton("PLAY");
        play.setFont(new Font("Rixel", Font.BOLD, 25 ));
        play.setFont(arcadeFont);
        span.add(play);
    }
}
