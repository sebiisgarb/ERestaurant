import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Tabel extends JFrame {

    protected JTable tabel;
    protected DefaultTableModel model;
    protected JComboBox categorie;
    protected JButton adauga;
    private final Listener listener;

    Tabel(){
        setLayout(new GridBagLayout());
        setVisible(true);
        listener = new Listener();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,10,5,10);
        constraints.gridx=0;
        constraints.gridy=0;

        tabel = new JTable();
        model = new DefaultTableModel(new Object[]{"Nume","Cantitate","Unitate masura", "Pret","Categorie"}, 0);
        tabel.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tabel);

        for(Produs i: Main.catalog.lista){
            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret, i.categorie});
        }
        add(scrollPane,constraints);

        categorie = new JComboBox(new Object[]{"Toate","Racoritoare","Pizza","Burger","Salata","Desert"});
        constraints.gridy=1;

        categorie.addActionListener((ActionEvent e) -> {
            String optiune = (String) categorie.getSelectedItem();
            model.setRowCount(0);
            switch(optiune){

                case "Racoritoare"-> {
                    for(Produs i: Main.catalog.lista)
                        if(i.categorie.toString().equals("Racoritoare"))
                            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
                }
                case "Pizza" ->{
                    for(Produs i: Main.catalog.lista)
                        if(i.categorie.toString().equals("Pizza"))
                            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});

                }
                case "Burger" ->{
                    for(Produs i: Main.catalog.lista)
                        if(i.categorie.toString().equals("Burger"))
                            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});

                }
                case "Salata" ->{
                    for(Produs i: Main.catalog.lista)
                        if(i.categorie.toString().equals("Salata"))
                            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});

                }
                case "Desert" ->{
                    for(Produs i: Main.catalog.lista)
                        if(i.categorie.toString().equals("Desert"))
                            model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});

                }
                case "Toate" ->{
                    for(Produs i: Main.catalog.lista)
                        model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});

                }

            }
        });

        adauga = new JButton("Adauga");
        adauga.setFocusable(false);
        adauga.setBackground(Color.lightGray);
        adauga.addActionListener(listener);

        JPanel panel = new JPanel();
        panel.add(adauga);
        panel.add(categorie);

        add(panel, constraints);
        pack();

    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == adauga){
                int selectedRow = tabel.getSelectedRow();
                int selectedColumn = tabel.getSelectedColumn();

                if(selectedRow == -1 || selectedColumn == -1)
                    return;
                Main.comenzi.get(PanouComanda.comanda).adaugaProdus(tabel.getValueAt(selectedRow, 0).toString());

            }
        }
    }
}
