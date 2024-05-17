import javax.swing.*;
import java.awt.*;

public class Interfata extends JFrame {


    private static Interfata instance;

    protected JPanel cardPanel;

    protected CardLayout cardLayout;

    public JPanel panouAdminSauChelner, panouAdmin, panouChelneri, loginAdmin, loginChelner, panouCatalog, panouMese;

    private Interfata(){
        super("E-Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        panouAdminSauChelner = new AdminSauChelner();
        panouAdmin = new PanouAdmin();
        panouChelneri = new PanouChelneri();
        loginAdmin= new LoginAdmin();
        loginChelner = new LoginChelner();
        panouCatalog = new PanouCatalog();
        panouMese = new PanouMese();


        cardPanel.add(panouAdminSauChelner, "Admin/Chelner");
        cardPanel.add(panouAdmin,"Admin");
        cardPanel.add(panouChelneri, "Chelneri");
        cardPanel.add(loginAdmin, "LoginAdmin");
        cardPanel.add(loginChelner, "LoginChelner");
        cardPanel.add(panouCatalog, "Catalog");
        cardPanel.add(panouMese, "Mese");

        cardLayout.show(cardPanel, "Admin/Chelner");
        add(cardPanel);

        setSize(650,650);
        setResizable(false);
        setVisible(true);

    }

    public static Interfata getInstance(){
        setDefaultLookAndFeelDecorated(true);
        if(instance == null )
            instance = new Interfata();
        return instance;
    }
}
