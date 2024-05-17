import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class AdminSauChelner extends JPanel {

    private JButton admin, chelner;
    private Listener listener;
    AdminSauChelner() {
        setLayout(new GridBagLayout());
        listener = new Listener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10,5,10);

        admin = new JButton("Admin");
        admin.setFocusable(false);
        admin.setPreferredSize(new Dimension(600, 125));
        admin.addActionListener(listener);
        admin.setBackground(Color.lightGray);
        admin.setFont(new Font("SansSerif",Font.BOLD,35));
        admin.setBorder(BorderFactory.createRaisedBevelBorder());
        constraints.gridx=0;
        constraints.gridy=0;
        add(admin,constraints);


        chelner = new JButton("Chelner");
        chelner.setFocusable(false);
        chelner.setPreferredSize(new Dimension(600, 125));
        chelner.addActionListener(listener);
        chelner.setBackground(Color.lightGray);
        chelner.setFont(new Font("SansSerif",Font.BOLD,35));
        chelner.setBorder(BorderFactory.createRaisedBevelBorder());
        constraints.gridx=0;
        constraints.gridy=1;
        add(chelner,constraints);
    }

    private class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==admin){
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "LoginAdmin");
            }
            if(e.getSource()==chelner){
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "LoginChelner");
            }
        }
    }
}
