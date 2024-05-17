import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class PanouComanda extends JPanel{

    protected JButton adauga, sterge, schimbaComanda, bon, comandaNoua;
    protected JTable tabel;
    private final Listener listener;
    protected static int comanda;
    protected Border defaultBorder;
    protected static DefaultTableModel model;

    PanouComanda(){
        setLayout(new GridBagLayout());
        listener = new Listener();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 3;
        gbc.gridy = 0;

        if (comanda != -1) {
            schimbaComanda = new JButton("Muta Comanda");
            schimbaComanda.addActionListener(listener);
            schimbaComanda.setPreferredSize(new Dimension(200, 50));
            schimbaComanda.setFocusable(false);
            schimbaComanda.setBackground(Color.lightGray);
            schimbaComanda.setFont(new Font("SansSerif",Font.BOLD,15));
            schimbaComanda.setBorder(BorderFactory.createRaisedBevelBorder());
            defaultBorder = schimbaComanda.getBorder();
            add(schimbaComanda, gbc);
        } else {
            comandaNoua = new JButton("Creeaza Comanda");
            comandaNoua.addActionListener(listener);
            comandaNoua.setPreferredSize(new Dimension(200, 50));
            comandaNoua.setFocusable(false);
            comandaNoua.setBackground(Color.lightGray);
            comandaNoua.setBorder(BorderFactory.createRaisedBevelBorder());
            comandaNoua.setFont(new Font("SansSerif",Font.BOLD,15));
            add(comandaNoua, gbc);
        }

        adauga = new JButton("Adauga");
        adauga.addActionListener(listener);
        adauga.setPreferredSize(new Dimension(200, 50));
        adauga.setFocusable(false);
        adauga.setBackground(Color.lightGray);
        adauga.setFont(new Font("SansSerif",Font.BOLD,15));
        adauga.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx=3;
        gbc.gridy = 1;
        add(adauga, gbc);

        sterge = new JButton("Sterge");
        sterge.setPreferredSize(new Dimension(200, 50));
        sterge.setFocusable(false);
        sterge.addActionListener(listener);
        sterge.setBackground(Color.lightGray);
        sterge.setFont(new Font("SansSerif",Font.BOLD,15));
        sterge.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx=3;
        gbc.gridy = 2;
        add(sterge, gbc);

        bon = new JButton("Printeaza Bon");
        bon.setPreferredSize(new Dimension(200, 50));
        bon.setFocusable(false);
        bon.setBackground(Color.lightGray);
        bon.setFont(new Font("SansSerif",Font.BOLD,15));
        bon.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx = 3;
        gbc.gridy = 3;
        bon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bon){
                    if (tabel.getRowCount() !=0) {

                        try {
                            tabel.print();
                        }catch(Exception a) {
                            System.out.println("Exception Occured: " +a);
                        }

                        Main.comenzi.remove(comanda);
                        PanouMese.butoane.get(Integer.parseInt(PanouMese.lastClicked.getText()) - 1).setBackground(Color.GREEN);
                        comanda = Main.comenzi.size() + 1;

                        DefaultTableModel dm = (DefaultTableModel) tabel.getModel();
                        while (dm.getRowCount() > 0) {
                            dm.removeRow(0);
                        }

                        schimbaComanda.setVisible(false);
                        remove(schimbaComanda);

                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 10, 5, 10);
                        gbc.gridx = 3;
                        gbc.gridy = 0;
                        comandaNoua = new JButton("Comanda Noua");
                        comandaNoua.addActionListener(listener);
                        comandaNoua.setPreferredSize(new Dimension(200, 50));
                        comandaNoua.setFocusable(false);
                        comandaNoua.setBackground(Color.lightGray);
                        comandaNoua.setFont(new Font("SansSerif", Font.BOLD, 15));
                        defaultBorder = comandaNoua.getBorder();
                        add(comandaNoua, gbc);

                        revalidate();
                        repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Comanda goala!", "Eroare la printare bon!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(bon, gbc);

        tabel = new JTable();
        tabel.setDefaultEditor(Object.class, null);
        tabel.setBackground(Color.lightGray);
        tabel.setFont(new Font("SansSerif", Font.ITALIC, 15));
        model = new DefaultTableModel(new Object[]{"Produs", "Cantitate", "Pret"}, 0);

        tabel.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tabel);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.BOTH;
        scrollPane.setPreferredSize(new Dimension(300, 100));
        add(scrollPane, gbc);

    }
    private class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            if (e.getSource() == sterge) {
                int selectedRow = tabel.getSelectedRow();
                int selectedColumn = tabel.getSelectedColumn();

                if (selectedRow == -1 || selectedColumn == -1)
                    JOptionPane.showMessageDialog(null, "Selecteaza produs!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                Main.comenzi.get(comanda).stergeProdus(tabel.getValueAt(tabel.getSelectedRow(), 0).toString());


            }

            if (e.getSource() == schimbaComanda) {
                if (!schimbaComanda.getBorder().equals(defaultBorder)) {
                    schimbaComanda.setBorder(defaultBorder);
                    adauga.setEnabled(true);
                    sterge.setEnabled(true);
                    bon.setEnabled(true);
                    PanouMese.inapoi.setEnabled(true);
                    PanouMese.flag = false;
                    return;
                }
                schimbaComanda.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                adauga.setEnabled(false);
                sterge.setEnabled(false);
                bon.setEnabled(false);
                PanouMese.inapoi.setEnabled(false);
                PanouMese.flag = true;
            }
            if (e.getSource() == comandaNoua) {
                Main.comenzi.add(new Comanda(Integer.parseInt(PanouMese.lastClicked.getText())));
                FileManager.encodeFile();
                PanouMese.butoane.get(Integer.parseInt(PanouMese.lastClicked.getText()) - 1).setBackground(Color.RED);
                comanda = Main.comenzi.size() - 1;

                remove(comandaNoua);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 10, 5, 10);
                gbc.gridx = 3;
                gbc.gridy = 0;
                schimbaComanda = new JButton("Muta Comanda");
                schimbaComanda.addActionListener(listener);
                schimbaComanda.setPreferredSize(new Dimension(200, 50));
                schimbaComanda.setFocusable(false);
                schimbaComanda.setBackground(Color.lightGray);
                schimbaComanda.setFont(new Font("SansSerif",Font.BOLD,15));
                defaultBorder = schimbaComanda.getBorder();
                add(schimbaComanda, gbc);

                revalidate();
                repaint();
            }
            if (e.getSource() == adauga) {
                Tabel t = new Tabel();

            }

        }
    }

}