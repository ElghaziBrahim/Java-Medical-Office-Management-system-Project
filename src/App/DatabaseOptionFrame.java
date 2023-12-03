package App;

import Factory.DatabaseDAOFactory;
import Medecin.Medecin;
import Medecin.MedecinsDAOImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DatabaseOptionFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DatabaseDAOFactory factory;

    public DatabaseOptionFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        Label Header = new Label("Base de Données");
        Header.setAlignment(Label.CENTER);
        Header.setFont(new Font("Dialog", Font.PLAIN, 15));
        Header.setBounds(141, 10, 156, 22);
        contentPane.add(Header);

        table = new JTable();

        factory = new DatabaseDAOFactory();
        MedecinsDAOImpl c = factory.getMedecinsDAO();
        try {
        	List <Medecin> medecinList=c.getAllMedecins();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (Medecin medecin : medecinList) {
                Object[] rowData = {medecin.getPrenom(), medecin.getNom()};
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Prenom", "Nom"
                }
        ) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[]{
                    String.class, String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{
                    false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.setBounds(35, 66, 84, 134);
        contentPane.add(table);

        Label Doctores = new Label("Médecins");
        Doctores.setFont(new Font("Dialog", Font.PLAIN, 15));
        Doctores.setAlignment(Label.CENTER);
        Doctores.setBounds(35, 38, 84, 22);
        contentPane.add(Doctores);

        Label reservation = new Label("Réservation");
        reservation.setFont(new Font("Dialog", Font.PLAIN, 15));
        reservation.setAlignment(Label.CENTER);
        reservation.setBounds(129, 38, 100, 22);
        contentPane.add(reservation);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DatabaseOptionFrame());
    }
}
