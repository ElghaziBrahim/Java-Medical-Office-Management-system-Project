package App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedicalAppMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicalAppMain frame = new MedicalAppMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MedicalAppMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 128);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton database = new JButton("base de données");
		database.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseOptionFrame fr=new DatabaseOptionFrame();
				fr.setVisible(true);
				fr.setLocationRelativeTo(null);
			}
		});
		contentPane.add(database);
		
		JButton serialisation = new JButton("sérialisation");
		serialisation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(serialisation);
	}

}
