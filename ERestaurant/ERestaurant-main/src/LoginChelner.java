import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginChelner extends JPanel {

    private JLabel messageLabel;
    protected JLabel text;
    protected JTextField id;
    protected JButton login, inapoi;

    private Listener listener;

    LoginChelner() {
        setLayout(new GridBagLayout());
        listener = new Listener();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        messageLabel = new JLabel();
        messageLabel.setPreferredSize(new Dimension(300,50));
        messageLabel.setFont(new Font("SansSerif",Font.BOLD,30));
        messageLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        gbc.gridx=0;
        gbc.gridy=0;
        add(messageLabel,gbc);

        text = new JLabel("ID:");
        text.setPreferredSize(new Dimension(200, 50));
        text.setFont(new Font("SansSerif", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 1;
        text.setHorizontalAlignment(SwingConstants.CENTER);
        add(text, gbc);

        id = new JTextField();
        id.setPreferredSize(new Dimension(175, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(id, gbc);

        login = new JButton("Log In");
        login.setFocusable(false);
        login.setPreferredSize(new Dimension(175, 50));
        login.addActionListener(listener);
        login.setBackground(Color.lightGray);
        login.setFont(new Font("SansSerif", Font.BOLD, 25));
        login.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(login, gbc);

        inapoi = new JButton("Inapoi");
        inapoi.setFocusable(false);
        inapoi.setPreferredSize(new Dimension(175, 50));
        inapoi.addActionListener(listener);
        inapoi.setBackground(Color.lightGray);
        inapoi.setFont(new Font("SansSerif", Font.BOLD, 25));
        inapoi.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(inapoi, gbc);

    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == inapoi) {
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin/Chelner");
            }
            if (e.getSource() == login) {
                if (id.getText().equals("")) return;
                for (Chelner i : Main.Staff.angajati) {
                    if (i.ID == Integer.parseInt(id.getText().trim())) {
                        ((PanouMese) Interfata.getInstance().panouMese).chelnerID = Integer.parseInt(id.getText().trim());
                        id.setText(null);
                        Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Mese");
                        messageLabel.setVisible(false);
                        break;
                    }
                    else{
                        messageLabel.setVisible(true);
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("ID gresit");
                        JOptionPane.showMessageDialog(null, "ID gresit", "Eroare!", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        }
    }
}
