package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Studentrege {

	private JFrame frame;
	private JTextField id;
	private JTextField Name_1;
	private JTextField Address;
	private JTextField Contact;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Studentrege window = new Studentrege();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection cn = null;

	/**
	 * Create the application.
	 */
	public Studentrege() {
		initialize();
		connect();
		tabledata();
	}

	public void connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection("jdbc:mysql://localhost:3307/regestration", "root", "");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tabledata() {

		try {
			PreparedStatement ps = cn.prepareStatement("select * from student");

			ResultSet rs = ps.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1042, 723);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTRATION FORM");
		lblNewLabel.setBounds(139, 30, 201, 35);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(41, 113, 74, 26);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setBounds(41, 172, 74, 26);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("GENDER");
		lblNewLabel_1_1_1.setBounds(41, 230, 74, 26);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("ADDRESS");
		lblNewLabel_1_1_1_1.setBounds(41, 291, 74, 26);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("CONTACT");
		lblNewLabel_1_1_1_1_1.setBounds(41, 351, 74, 26);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);

		id = new JTextField();
		id.setBounds(139, 113, 201, 25);
		frame.getContentPane().add(id);
		id.setColumns(10);

		Name_1 = new JTextField();
		Name_1.setBounds(139, 173, 201, 25);
		Name_1.setColumns(10);
		frame.getContentPane().add(Name_1);

		Address = new JTextField();
		Address.setBounds(139, 297, 201, 25);
		Address.setColumns(10);
		frame.getContentPane().add(Address);

		Contact = new JTextField();
		Contact.setBounds(139, 351, 201, 25);
		Contact.setColumns(10);
		frame.getContentPane().add(Contact);

		JRadioButton male = new JRadioButton("MALE");
		male.setBounds(139, 235, 103, 21);
		male.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(male);

		JRadioButton female = new JRadioButton("FEMALE");
		female.setBounds(237, 233, 103, 21);
		female.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(female);

		ButtonGroup bg = new ButtonGroup();
		bg.add(male);
		bg.add(female);

		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
		btnNewButton.setBounds(41, 437, 130, 35);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnNewButton);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String i1 = id.getText();
				int id = Integer.parseInt(i1);
				String sname = Name_1.getText();
				String sad = Address.getText();
				String sco = Contact.getText();
				String gender = null;
				if (male.isSelected()) {
					gender = "male";
				} else if (female.isSelected()) {
					gender = "female";
				}

				try {
					PreparedStatement ps = cn.prepareStatement("insert into student values(?,?,?,?,?,?)");
					ps.setInt(1, 0);
					ps.setInt(2, id);
					ps.setString(3, sname);
					ps.setString(4, gender);
					ps.setString(5, sad);
					ps.setString(6, sco);

					int i = ps.executeUpdate();

					if (i > 0) {
						JOptionPane.showMessageDialog(frame, "REGISTRATION SUCSSESFULLY..");
						tabledata();
						Name_1.setText("");
						Address.setText("");
						Contact.setText("");
						Studentrege.this.id.setText("");
						bg.clearSelection();
					}
					// }
//					else
//					{
////						id=Integer.parseInt(i1);
//						//					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnRegister.setBounds(210, 437, 130, 35);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnRegister);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sid = id.getText();
				int id = Integer.parseInt(sid);

				try {
					PreparedStatement ps = cn.prepareStatement("delete from student where id=?");
					ps.setInt(1, id);

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(frame, "DATA DELETED..");
						tabledata();
						Studentrege.this.id.setText("");
						Name_1.setText("");
						Address.setText("");
						Contact.setText("");
						bg.clearSelection();

					} else {
						JOptionPane.showMessageDialog(frame, "INVALID ID!!!!!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDelete.setBounds(41, 510, 130, 35);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnDelete);

		JButton btnRegister_1_1 = new JButton("UPDATE");
		btnRegister_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sid = id.getText();
				int id = Integer.parseInt(sid);
				String sname = Name_1.getText();
				String sad = Address.getText();
				String sco = Contact.getText();
				String gender = null;
				if (male.isSelected()) {
					gender = "male";
				} else if (female.isSelected()) {
					gender = "female";
				}

				try {
					

						PreparedStatement ps1 = cn.prepareStatement("update student set Name_1=?,Gender=?,Address=?,Contact=? where id=?");
						ps1.setString(1, sname);
						ps1.setString(2, gender);
						ps1.setString(3, sad);
						ps1.setString(4, sco);
						ps1.setInt(5, id);

						int i = ps1.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(frame, "DATA UPDATED..");
							Name_1.setText("");
							Address.setText("");
							Contact.setText("");
							Studentrege.this.id.setText("");
							tabledata();
							bg.clearSelection();
						}

						// Studentrege.this.id.setText("");
						
					
					}
					
					 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnRegister_1_1.setBounds(210, 510, 130, 35);
		btnRegister_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnRegister_1_1);

		JButton btnRegister_1_1_1 = new JButton("RESET");
		btnRegister_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Name_1.setText("");
				Address.setText("");
				Contact.setText("");
				Studentrege.this.id.setText("");
				bg.clearSelection();

			}
		});
		btnRegister_1_1_1.setBounds(120, 570, 130, 35);
		btnRegister_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnRegister_1_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(417, 41, 601, 503);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnRegister_1_1_1_1 = new JButton("REFRESH TABLE");
		btnRegister_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					PreparedStatement ps = cn.prepareStatement("select * from student");

					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(frame, "REFERSH TABLE");
						tabledata();
					} else {
						JOptionPane.showMessageDialog(frame, "DATA NOT FOUND!!!!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnRegister_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegister_1_1_1_1.setBounds(517, 570, 377, 35);
		frame.getContentPane().add(btnRegister_1_1_1_1);
		
	}
}