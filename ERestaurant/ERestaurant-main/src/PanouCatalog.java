import java.awt.*;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class PanouCatalog extends JPanel {
    protected final JTable tabel;
    protected static DefaultTableModel model;
    protected final JButton inapoi, adauga, reset, print, sterge;
    protected final JTextField nume, cantitate,unitate,pret;
    protected final JComboBox categorie;
    private Listener listener;

    PanouCatalog(){
        setLayout(new GridBagLayout());
        listener = new Listener();


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5,10,5,10);
        gbc.gridx = 0;
        gbc.gridy = 2;

        tabel = new JTable();
        model = new DefaultTableModel(new Object[]{"Nume","Cantitate","Unitate de masura","Pret","Categorie"},0);
        model.addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE)
                Main.catalog.modificaProdus(e.getFirstRow(), e.getColumn());
        });
        tabel.setModel(model);
        tabel.setBackground(Color.lightGray);
        tabel.setFont(new Font("SansSerif",Font.ITALIC,15));

        JScrollPane scrollPane = new JScrollPane(tabel);
        add(scrollPane,gbc);

        JPanel fields = new JPanel();
        nume = new JTextField(8);
        nume.setBorder(BorderFactory.createEtchedBorder());
        cantitate = new JTextField(8);
        cantitate.setBorder(BorderFactory.createEtchedBorder());
        unitate = new JTextField(8);
        unitate.setBorder(BorderFactory.createEtchedBorder());
        pret = new JTextField(8);
        pret.setBorder(BorderFactory.createEtchedBorder());

        categorie = new JComboBox(new Object[]{"Toate","Racoritoare","Pizza","Burger","Salata","Desert"});
        categorie.setFocusable(false);
        categorie.setBackground(Color.lightGray);
        categorie.setFont(new Font("SansSerif",Font.BOLD,15));

        categorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String optiune = (String) categorie.getSelectedItem();
                model.setRowCount(0);
                switch(optiune){
                    case "Racoritoare":{
                        for(Produs i: Main.catalog.lista)
                            if(i.categorie.toString().equals("Racoritoare"))
                                model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                        break;
                    }

                    case "Pizza":{
                        for(Produs i: Main.catalog.lista)
                            if(i.categorie.toString().equals("Pizza"))
                                model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                        break;

                    }

                    case "Burger":{
                        for(Produs i: Main.catalog.lista)
                            if(i.categorie.toString().equals("Burger"))
                                model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                        break;
                    }

                    case "Salata":{
                        for(Produs i: Main.catalog.lista)
                            if(i.categorie.toString().equals("Salata"))
                                model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                        break;
                    }
                    case "Desert":{
                        for(Produs i: Main.catalog.lista)
                            if(i.categorie.toString().equals("Desert"))
                                model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                        break;
                    }

                    case "Toate":{
                        for(Produs i: Main.catalog.lista)
                            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                        break;
                    }
                }
            }
        });
        fields.add(nume);
        fields.add(cantitate);
        fields.add(unitate);
        fields.add(pret);
        fields.add(categorie);
        fields.setBackground(Color.lightGray);
        fields.setFont(new Font("SansSerif",Font.ITALIC,10));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fields, gbc);

        JPanel butoane = new JPanel();

        adauga = new JButton("Adauga");
        adauga.setFocusable(false);
        adauga.addActionListener(listener);
        adauga.setBackground(Color.lightGray);
        adauga.setFont(new Font("SansSerif", Font.BOLD,20));
        adauga.setBorder(BorderFactory.createRaisedBevelBorder());
        butoane.add(adauga);

        reset=new JButton("Reset");
        reset.setFocusable(false);
        reset.addActionListener(listener);
        reset.setBackground(Color.lightGray);
        reset.setFont(new Font("SansSerif", Font.BOLD,20));
        reset.setBorder(BorderFactory.createRaisedBevelBorder());
        butoane.add(reset);
        gbc.gridx=0;
        gbc.gridy=1;
        add(butoane,gbc);

        JPanel panel = new JPanel();

        sterge = new JButton("Sterge");
        sterge.setFocusable(false);
        sterge.addActionListener(listener);
        sterge.setBackground(Color.lightGray);
        sterge.setFont(new Font("SansSerif", Font.BOLD,20));
        sterge.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.add(sterge);

        print = new JButton("Print");
        print.setFocusable(false);
        print.setBackground(Color.lightGray);
        print.setFont(new Font("SansSerif", Font.BOLD,20));
        print.setBorder(BorderFactory.createRaisedBevelBorder());
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
        panel.add(print);
        gbc.gridx=0;
        gbc.gridy=3;
        add(panel, gbc);


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
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == inapoi){
                PanouAdmin.resetTabel(model);
                FileManager.writeFile(new File("Produse.txt"),"Catalog");
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin");
            }
            if(e.getSource() == adauga) {
                if (nume.getText().isBlank() || cantitate.getText().isBlank() || unitate.getText().isBlank() || pret.getText().isBlank() || categorie.getSelectedItem().equals("Toate"))
                    JOptionPane.showMessageDialog(null, "Detalii produs insuficiente!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                else {
                    Main.catalog.adaugaProdus(nume.getText().trim(), cantitate.getText().trim(), unitate.getText().trim(), pret.getText().trim(), categorie.getSelectedItem().toString());
                    model.addRow(new Object[]{nume.getText().trim(), cantitate.getText().trim(), unitate.getText().trim(), pret.getText().trim(), categorie.getSelectedItem()});
                }
            }
            if(e.getSource() == reset){
                nume.setText("");
                cantitate.setText("");
                unitate.setText("");
                pret.setText("");
            }

            if (e.getSource() == sterge) {
                int selectedRow = tabel.getSelectedRow();
                int selectedColumn = tabel.getSelectedColumn();

                if (selectedRow == -1 || selectedColumn == -1)
                    JOptionPane.showMessageDialog(null, "Selecteaza produs!", "Eroare!", JOptionPane.ERROR_MESSAGE);
                Main.catalog.stergeProdus(tabel.getSelectedRow());


        }
    }
}
}