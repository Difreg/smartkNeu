package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ger�te.Ger�te;


import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JOptionPane;


import java.awt.Font;

import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Frame_K�hlschrankEntfernen extends JFrame {

	/**
	 * Klasse f�r das Fenster K�hlschrank entfernen
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Ger�te ger�te;

	

	/**
	 * Create the frame.
	 */
	public Frame_K�hlschrankEntfernen(Ger�te inventar) {
		this.ger�te = new Ger�te();
		this.ger�te.setAnzahl(inventar.getAnzahl());
		this.ger�te.setEinkaufen(inventar.getEinkaufen());
		this.ger�te.setK�hlschr�nke(inventar.getK�hlschr�nke());
		setTitle("K\u00FChlschrank entfernen");
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 10, 10));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		DefaultListModel<String> model = createModel();
		
		
		
		JList <String>list = new JList<String>();
		list.setModel(model);
		
		
	
		list.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		
		panel.add(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230,230,250));
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnEntfernen = new JButton("Entfernen");
		btnEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				int i = list.getSelectedIndex();
				if(i < ger�te.getAnzahl()){
					inventar.k�hlschrankEntfernen(i);
					JOptionPane.showMessageDialog(null, "K�hlschrank entfernt. ");
				}
				inventar.ger�teSpeichern();
				ger�te.ger�teLaden();
				list.setModel(createModel());
				
			}
		});
		
		JLabel lblWhlenSieEinen = new JLabel("<html><body>W\u00E4hlen Sie einen K\u00FChlschrank aus der obigen Liste und klicken <br> Sie auf \"Entfernen\"</body></html>");
		lblWhlenSieEinen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblWhlenSieEinen.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblWhlenSieEinen);
		panel_1.add(btnEntfernen);
		
		JButton btnZur�ck = new JButton("Zur\u00FCck");
		btnZur�ck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fensterSchlie�en();
			}
		});
		panel_1.add(btnZur�ck);
	}
	
	private void fensterSchlie�en(){
		this.setVisible(false);
	}
	
	private DefaultListModel<String> createModel(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		if(ger�te.getAnzahl() > 0) {
			String[] values = new String[ger�te.getAnzahl()];
			for (int i = 0; i<ger�te.getAnzahl(); i++){
				values[i] = ger�te.getK�hlschr�nke().get(i).getName() + "   " + ger�te.getK�hlschr�nke().get(i).getStandort() + "   " +
						ger�te.getK�hlschr�nke().get(i).getGr��e();
			}
			for(int i = 0; i<values.length;i++){
				model.addElement(values[i]);
			}
		}
		else {
			model.addElement("<html><body>Es sind keine K�hlschr�nke vorhanden. <br>Bitte erstellen Sie einen K�hlschrank. </body> </html>");
		}
		return model;
	}

}
