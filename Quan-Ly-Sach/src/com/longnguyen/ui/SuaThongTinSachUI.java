package com.longnguyen.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.longnguyen.Service.SachService;
import com.longnguyen.model.Sach;

public class SuaThongTinSachUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaSach, txtTenSach, txtMaNXB;
	
	private JButton btnSua, btnLuu;
	private Boolean check;
	public String maSach = "";
	SachService sachService;
	
	public  SuaThongTinSachUI(String title,  Boolean check) {
		super(title);
		this.check = check;
		addControls();
		addEvents();
		
	}

	public void hienThiThongTinChiTiet() {
		sachService = new SachService();
		Sach sach = sachService.findByMaSach(maSach);
			
		txtMaSach.setText(sach.getMaSach());
		txtTenSach.setText(sach.getTenSach());
		txtMaNXB.setText(sach.getMaNhaXuatBan());
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		
		

		if (check) {
			btnSua.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					xuLySua();
					dispose();
				}
			});
		} else {
			btnLuu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					xuLyThem();
					dispose();
				}
			});
		}
		
	}

	protected void xuLyThem() {
		sachService = new SachService();	
		Sach sach = new Sach();
		
		sach.setMaSach(txtMaSach.getText());
		sach.setTenSach(txtTenSach.getText());
		sach.setMaNhaXuatBan(txtMaNXB.getText());
		
		Boolean check = sachService.insert(sach);
		if(check) {
			JOptionPane.showMessageDialog(null, "Thêm thành công !");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm thất bại !");
		}
		
	}

	protected void xuLySua() {
		sachService = new SachService();	
		Sach sach = new Sach();
		
		sach.setMaSach(txtMaSach.getText());
		sach.setTenSach(txtTenSach.getText());
		sach.setMaNhaXuatBan(txtMaNXB.getText());
		
		Boolean check = sachService.update(sach);
		if(check) {
			JOptionPane.showMessageDialog(null, "Sửa thành công !");
		} else {
			JOptionPane.showMessageDialog(null, "Sửa thất bại !");
		}
		
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		JPanel pnChiTiet = new JPanel();
		con.add(pnChiTiet);
		pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
		JPanel pnNxB = new JPanel();
		JLabel lblNxB = new JLabel("Thông tin Sách ");
		lblNxB.setForeground(Color.BLUE);
		Font ft = new Font("arial", Font.BOLD, 20);
		lblNxB.setFont(ft);
		pnNxB.add(lblNxB);
		pnChiTiet.add(pnNxB);
		
		JPanel pnMaSach = new JPanel();
		JLabel lblMaSach = new JLabel("Mã Sách: ");
		txtMaSach = new JTextField(25);
		pnMaSach.add(lblMaSach);
		pnMaSach.add(txtMaSach);
		pnChiTiet.add(pnMaSach);
		
		JPanel pnTenSach = new JPanel();
		JLabel lblTenSach = new JLabel("Tên Sách: ");
		txtTenSach = new JTextField(25);
		pnTenSach.add(lblTenSach);
		pnTenSach.add(txtTenSach);
		pnChiTiet.add(pnTenSach);
		
		JPanel pnMaNXB = new JPanel();
		JLabel lblMaNXB = new JLabel("Mã NXB: ");
		txtMaNXB = new JTextField(25);
		pnMaNXB.add(lblMaNXB);
		pnMaNXB.add(txtMaNXB);
		pnChiTiet.add(pnMaNXB);
		
//		JPanel pnButtonSua = new JPanel();
//		btnSua = new JButton("Sửa");
//		pnButtonSua.add(btnSua);
//		pnChiTiet.add(pnButtonSua);
		
		if (check) {
			JPanel pnButtonSua = new JPanel();
			btnSua = new JButton("Sửa");
			pnButtonSua.add(btnSua);
			pnChiTiet.add(pnButtonSua);
		} else {
			JPanel pnButtonSua = new JPanel();
			btnLuu = new JButton("Lưu");
			pnButtonSua.add(btnLuu);
			pnChiTiet.add(pnButtonSua);
		}
		
		
		
		//Chinh sua
		lblMaSach.setPreferredSize(lblTenSach.getPreferredSize());
		lblTenSach.setPreferredSize(lblTenSach.getPreferredSize());
		
	}
	
	public void showWindow() {
		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
