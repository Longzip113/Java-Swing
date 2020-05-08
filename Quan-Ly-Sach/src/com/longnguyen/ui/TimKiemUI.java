package com.longnguyen.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.longnguyen.Service.SachService;
import com.longnguyen.model.Sach;

public class TimKiemUI extends JFrame{

	private static final long serialVersionUID = 1L;

	JTextField txtTimKiem;
	JButton btnBatDauTim;
	DefaultTableModel dtmSach;
	JTable tblSach;
	public  TimKiemUI(String title) {
		
		super(title);
		addControls();
		addEvents();
		
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnBatDauTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyTimKiem();
			}
		});
	}

	protected void xuLyTimKiem() {
		SachService sachService = new SachService();
		ArrayList<Sach> dsSach = sachService.findByMaNXB(txtTimKiem.getText());
		dtmSach.setRowCount(0);
		for (Sach sach : dsSach) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(sach.getMaSach());
			vector.add(sach.getTenSach());
			vector.add(sach.getMaNhaXuatBan());
			dtmSach.addRow(vector);
		}
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNhap = new JLabel("Nhập: ");
		txtTimKiem = new JTextField(20);
		btnBatDauTim = new JButton("Tìm kiếm");
		pnNorth.add(lblNhap);
		pnNorth.add(txtTimKiem);
		pnNorth.add(btnBatDauTim);
		con.add(pnNorth, BorderLayout.NORTH);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		dtmSach = new DefaultTableModel();
		dtmSach.addColumn("Mã sách: ");
		dtmSach.addColumn("Tên sách: ");
		dtmSach.addColumn("Nhà sản xuất: ");
		tblSach = new JTable(dtmSach);
		JScrollPane scTable = new JScrollPane(tblSach,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
											, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scTable, BorderLayout.CENTER);
		con.add(pnCenter, BorderLayout.CENTER);
	}
	
	public void showWindow() {
		this.setSize(500, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
