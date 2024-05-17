import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanouAdmin extends JPanel{

    private JButton catalog, angajati, inapoi;

    private Listener listener;

    PanouAdmin(){
        setLayout(new GridBagLayout());
        listener = new Listener();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(100,15,10,15);

        //~~~~ PRIMA LINIE ~~~~//

        catalog = new JButton("Catalog");
        catalog.setFocusable(false);
        catalog.setPreferredSize(new Dimension(400,75));
        catalog.addActionListener(listener);
        catalog.setBackground(Color.lightGray);
        catalog.setFont(new Font("SansSerif", Font.BOLD,40));
        catalog.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor= GridBagConstraints.CENTER;
        add(catalog,gbc);

        //~~~~ A DOUA LINIE ~~~~//

        angajati = new JButton("Angajati");
        angajati.setFocusable(false);
        angajati.setPreferredSize(new Dimension(400, 75));
        angajati.addActionListener(listener);
        angajati.setBackground(Color.lightGray);
        angajati.setFont(new Font("SansSerif",Font.BOLD,40));
        angajati.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(angajati,gbc);

        //~~~~ A TREIA LINIE ~~~~//

        inapoi = new JButton("Inapoi");
        inapoi.setFocusable(false);
        inapoi.setPreferredSize(new Dimension(125,50));
        inapoi.addActionListener(listener);
        inapoi.setBackground(Color.lightGray);
        inapoi.setFont(new Font("SansSerif", Font.BOLD,25));
        inapoi.setBorder(BorderFactory.createRaisedBevelBorder());
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.weighty=12;
        gbc.weightx=4;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        add(inapoi,gbc);

    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

                if(e.getSource() == catalog){
                    adaugaProdusTabel();
                    Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Catalog");

                }
                if(e.getSource() == angajati){
                    adaugaAngajatiTabel();
                    Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Chelneri");
                }
                if(e.getSource() == inapoi){
                    Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin/Chelner");
                }
            }
        }
    public static void adaugaAngajatiTabel(){
        for(Angajat angajat: Main.Staff.angajati){
            Chelner chelner = (Chelner) angajat;
            PanouChelneri.model.addRow(new Object[] {chelner.nume,chelner.prenume,chelner.ID,"Chelner"});
        }

    }

    public static void adaugaProdusTabel(){
        for(Produs i: Main.catalog.lista){
            PanouCatalog.model.addRow(new Object[]{i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie});
        }
    }

    public static void resetTabel(DefaultTableModel model){
        model.setRowCount(0);
    }
}
