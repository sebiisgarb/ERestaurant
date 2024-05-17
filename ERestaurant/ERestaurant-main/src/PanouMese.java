import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class  PanouMese extends JPanel {

    protected static JButton inapoi;
    private final Listener listener;
    protected static ArrayList<JButton> butoane;
    protected int chelnerID;
    protected PanouComanda panouComanda;
    protected static boolean flag;
    protected static JButton lastClicked;


    PanouMese() {
        setLayout(new BorderLayout());
        listener = new Listener();
        setSize(880,660);

        inapoi = new JButton("Inapoi");
        inapoi.setFocusable(false);
        inapoi.addActionListener(listener);
        inapoi.setPreferredSize(new Dimension(125, 50));
        inapoi.setBackground(Color.LIGHT_GRAY);
        inapoi.setBorder(BorderFactory.createRaisedBevelBorder());
        inapoi.setFont(new Font("SansSerif", Font.BOLD, 20));
        butoane = new ArrayList<>();
        ImageIcon icon = new ImageIcon("icon.png");
        for (int i = 0; i < 10; i++) {
            butoane.add(new JButton(Integer.toString(i + 1)));
        }
        JPanel Mese = new JPanel();
        for (JButton i : butoane) {
            boolean found = false;
            i.setIcon(icon);
            i.setFocusable(false);
            i.setPreferredSize(new Dimension(110, 100));

            if (Main.comenzi == null) {
                i.setBackground(Color.GREEN);
                Mese.add(i);
            } else {
                for (int j = 0; j < Main.comenzi.size(); j++)
                    if (Main.comenzi.get(j).masa == Integer.parseInt(i.getText())) {
                        i.setBackground(Color.red);
                        Mese.add(i);
                        found = true;
                    }
                if (!found) {
                    i.setBackground(Color.GREEN);
                    Mese.add(i);
                }
            }
        }

        JPanel panouInapoi = new JPanel();
        panouInapoi.add(inapoi);
        add(panouInapoi, BorderLayout.SOUTH);
        add(Mese);

        for (JButton button : butoane) {
            button.addActionListener(new FirstActionListener());
            button.addActionListener(new SecondActionListener());
        }
    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == inapoi) {
                if (panouComanda != null) remove(panouComanda);
                Interfata.getInstance().cardLayout.show(Interfata.getInstance().cardPanel, "Admin/Chelner");

            }
        }
    }

    class FirstActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (flag) return;
            JButton clickedButton = (JButton) e.getSource();
            PanouComanda.comanda = -1;

            for (int i = 0; i < Main.comenzi.size(); i++) {
                if (Main.comenzi.get(i).masa == Integer.parseInt(clickedButton.getText())) {
                    PanouComanda.comanda = i;
                    break;
                }
            }
            if (PanouComanda.comanda != -1) {
                populeazaTabel();
            } else {
                if (panouComanda != null) remove(panouComanda);
                panouComanda = new PanouComanda();
                panouComanda.setPreferredSize(new Dimension(500, 380));
                add(panouComanda, BorderLayout.SOUTH);

                PanouComanda.model.setRowCount(0);

                revalidate();
                repaint();
            }
        }
    }

    class SecondActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            lastClicked = (JButton) e.getSource();
            if (flag) {
                if (lastClicked.getBackground().equals(Color.GREEN)) {
                    butoane.get(Main.comenzi.get(PanouComanda.comanda).masa - 1).setBackground(Color.GREEN);
                    Main.comenzi.get(PanouComanda.comanda).schimbaMasa(Integer.parseInt(lastClicked.getText()));
                    FileManager.encodeFile();
                    butoane.get(Integer.parseInt(lastClicked.getText()) - 1).setBackground(Color.RED);
                    revalidate();
                    repaint();
                }
            }
        }
    }

    public void populeazaTabel() {
        if (panouComanda != null) remove(panouComanda);
        panouComanda = new PanouComanda();
        panouComanda.setPreferredSize(new Dimension(550, 400));
        add(panouComanda, BorderLayout.SOUTH);

        revalidate();
        repaint();
        PanouComanda.model.setRowCount(0);

        for (Produs i : Main.comenzi.get(PanouComanda.comanda).lista.keySet())
            PanouComanda.model.addRow(new Object[]{i.nume, Main.comenzi.get(PanouComanda.comanda).lista.getOrDefault(Main.catalog.lista.get(Main.catalog.getIndex(i.nume)), -1), i.pret});
    }
}


