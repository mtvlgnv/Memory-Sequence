import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(Runnable onStart) {
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.gridx = 0; 

        //  Title 
        JLabel title = new JLabel("Memory Tiles");
        title.setFont(title.getFont().deriveFont(28f));
        add(title, c);

        //  Start Button 
        c.gridy = 1;
        JButton start = new JButton("Start");
        start.addActionListener(e -> onStart.run()); 
        add(start, c);

        //  How to Play Button 
        c.gridy = 2;
        JButton manual = new JButton("How to Play");
        manual.addActionListener(e -> {
            JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
            ManualDialog.show(owner);   
        });
        add(manual, c);

        //Settings / Customization Button 
        c.gridy = 3;
        JButton settings = new JButton("Settings / Customization");
        settings.addActionListener(e -> {
     // TODO: settings 
        });
        add(settings, c);
    }
}
