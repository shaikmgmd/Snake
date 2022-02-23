import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class OtherPan extends JPanel {
    JLabel text;
    OtherPan() throws IOException, FontFormatException {
        super();
        text = new JLabel("Jeu du snake");
        InputStream is = GamePan.class.getResourceAsStream("LLPIXEL3.ttf");
        Font arcade = Font.createFont(Font.TRUETYPE_FONT, is);
        Font arcadeFont = arcade.deriveFont(40f);
        text.setForeground(new Color(0, 0, 0));
        text.setFont(new Font("Rixel", Font.BOLD, 30 ));
        text.setFont(arcadeFont);
        this.setBackground(new Color(169, 231, 180));
        this.add(text);

    }
}
