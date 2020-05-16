package nth.reflect.fw.swing.component.tabpanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class TabbedPane extends JPanel {

    private static final long serialVersionUID = 1L;

    public TabbedPane() {
        setLayout(new BorderLayout());
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        JTabbedPane tb = new JTabbedPane();
        tb.setUI(new CustomTabbedPaneUI());
        tb.add("Tab1", new JTextArea(""));
        tb.add("Tab2", new JTextArea(""));
        tb.add("Tab3", new JTextArea(""));
        tb.add("Tab4", new JTextArea(""));
        tb.add("Tab5", new JTextArea(""));
        jp.add(tb, BorderLayout.CENTER);
        add(jp, BorderLayout.CENTER);
        tb.setEnabledAt(1, false);
        tb.setEnabledAt(3, false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new TabbedPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }
}