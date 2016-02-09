package nth.introspect.ui.style.fontawesome;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestFontAwsome {

    public static void main(String[] args) {
        new TestFontAwsome();
    }

    public TestFontAwsome() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                try (InputStream is = TestFontAwsome.class.getResourceAsStream("/META-INF/resources/webjars/FONT-awesome/4.5.0/fonts/fontawesome-webfont.ttf")) {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                    font = font.deriveFont(Font.PLAIN, 24f);

                    JLabel label = new JLabel("\uf0c0");
                    label.setFont(font);

                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new GridBagLayout());
                    frame.add(label);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (IOException | FontFormatException exp) {
                    exp.printStackTrace();
                }
            }
        });
    }

}