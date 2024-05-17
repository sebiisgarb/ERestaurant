import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAdmin extends JPanel {

    private JLabel messageLabel;
    protected final JLabel text;
    protected final JPasswordField parola;
    protected final JButton login, inapoi;
    private final Listener listener;

    LoginAdmin(){
        setLayout(new GridBagLayout());
        listener = new Listener();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,10,5,10);

        messageLabel = new JLabel();
        messageLabel.setPreferredSize(new Dimension(300,50));
        messageLabel.setFont(new Font("SansSerif",Font.BOLD,30));
        messageLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        gbc.gridx=0;
        gbc.gridy=0;
        add(messageLabel,gbc);

        text = new JLabel("Parola:");
        text.setPreferredSize(new Dimension(150,50));
        gbc.gridx=0;
        gbc.gridy=1;
        text.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        text.setFont(new Font("SansSerif",Font.BOLD,30));
        add(text, gbc);

        parola = new JPasswordField();
        parola.setPreferredSize(new Dimension(150, 50));
        parola.setBackground(Color.white);
        gbc.gridx=0;
        gbc.gridy=2;
        add(parola,gbc);

        login = new JButton("Log In");
        login.setFocusable(false);
        login.setPreferredSize(new Dimension(150,50));
        login.setBackground(Color.lightGray);
        login.setFont(new Font("SansSerif", Font.BOLD, 30));
        login.setBackground(Color.lightGray);
        login.addActionListener(listener);
        login.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx=0;
        gbc.gridy=3;
        add(login,gbc);

        inapoi = new JButton("Inapoi");
        inapoi.setFocusable(false);
        inapoi.setPreferredSize(new Dimension(150,50));
        inapoi.setBackground(Color.lightGray);
        inapoi.setFont(new Font("SansSerif", Font.BOLD, 30));
        inapoi.addActionListener(listener);
        inapoi.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx=0;
        gbc.gridy=4;
        add(inapoi, gbc);



    }
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == login){
                if(String.valueOf(parola.getPassword()).equals(Main.pin)){
                    parola.setText(null);
                    Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin");
                    messageLabel.setVisible(false);
                }
                else{
                    messageLabel.setVisible(true);
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Parola gresita");
                    JOptionPane.showMessageDialog(null, "Parola gresita!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == inapoi){
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin/Chelner");
            }
        }
    }
}
