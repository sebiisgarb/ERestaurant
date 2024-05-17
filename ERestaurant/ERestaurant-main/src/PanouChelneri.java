import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PanouChelneri extends JPanel {

    protected JTable tabel;
    protected static DefaultTableModel model;
    protected final JButton adauga, reset, sterge, print, inapoi;
    protected final JTextField nume, prenume;
    private Listener listener;

    PanouChelneri(){

        setLayout(new GridBagLayout());
        listener=new Listener();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 10,5,10);
        gbc.gridx=0;
        gbc.gridy=2;

        tabel = new JTable();

        String[] coloane = {"Nume", "Prenume", "ID"};
        model = new DefaultTableModel(coloane, 0);
        model.addTableModelListener((TableModelEvent e)-> {
            if (e.getType() == TableModelEvent.UPDATE)
                Main.Staff.modificaAngajat(e.getFirstRow(),e.getColumn());
        });

        tabel.setModel(model);
        tabel.setBackground(Color.lightGray);
        tabel.setFont(new Font("SansSerif", Font.ITALIC, 15));

        JScrollPane scrollPane = new JScrollPane(tabel);
        add(scrollPane, gbc);

        JPanel field = new JPanel();
        nume = new JTextField(10);
        prenume = new JTextField(10);
        field.add(nume);
        field.add(prenume);
        field.setBackground(Color.LIGHT_GRAY);
        field.setFont(new Font("SansSerif", Font.ITALIC, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(field, gbc);

        JPanel butoane = new JPanel();

        adauga = new JButton("Adauga");
        adauga.setFocusable(false);
        adauga.addActionListener(listener);
        adauga.setBackground(Color.LIGHT_GRAY);
        adauga.setFont(new Font("SansSerif", Font.BOLD, 22));
        adauga.setBorder(BorderFactory.createRaisedBevelBorder());
        butoane.add(adauga);

        reset = new JButton("Reset");
        reset.setFocusable(false);
        reset.addActionListener(listener);
        reset.setBackground(Color.lightGray);
        reset.setFont(new Font("SansSerif", Font.BOLD,22));
        reset.setBorder(BorderFactory.createRaisedBevelBorder());
        butoane.add(reset);

        gbc.gridx=0;
        gbc.gridy=1;
        add(butoane, gbc);

        JPanel panel = new JPanel();

        sterge = new JButton("Sterge");
        sterge.setFocusable(false);
        sterge.addActionListener(listener);
        sterge.setBackground(Color.LIGHT_GRAY);
        sterge.setFont(new Font("SansSerif", Font.BOLD,22));
        panel.add(sterge);

        print = new JButton("Print");
        print.setFocusable(false);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tabel.print();
                }catch(Exception a) {
                    System.out.println("Exception Occured: " +a);
                }
            }
        });
        print.setBackground(Color.LIGHT_GRAY);
        print.setFont(new Font("SansSerif", Font.BOLD, 22));
        panel.add(print);
        gbc.gridx=0;
        gbc.gridy=3;

        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        add(panel,gbc);

        inapoi = new JButton("Inapoi");
        inapoi.setFocusable(false);
        inapoi.addActionListener(listener);
        inapoi.setBackground(Color.lightGray);
        inapoi.setFont(new Font("SansSerif", Font.BOLD,15));
        inapoi.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty=12;
        gbc.weightx=4;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;

        add(inapoi, gbc);
    }

    private class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == inapoi){
                FileManager.writeFile(new File("Angajati.txt"),"Angajati");
                PanouAdmin.resetTabel(model);
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin");
            }
            if(e.getSource() == adauga) {
                if (nume.getText().isBlank() || prenume.getText().isBlank())
                    JOptionPane.showMessageDialog(null, "Detalii angajat insuficiente!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                else {
                    Main.Staff.adaugaAngajat(nume.getText().trim(), prenume.getText().trim());
                    model.addRow(new Object[]{nume.getText().trim(), prenume.getText().trim(), (Main.Staff.angajati.get(Main.Staff.angajati.size() - 1)).ID});
                }
            }
            if(e.getSource() == reset){
                nume.setText("");
                prenume.setText("");
            }
            if(e.getSource() == sterge){
                if(tabel.getSelectedRow() == -1)
                    JOptionPane.showMessageDialog(null, "Selecteaza angajat!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                Main.Staff.stergereAngajat(tabel.getSelectedRow());
            }
        }
    }
}
