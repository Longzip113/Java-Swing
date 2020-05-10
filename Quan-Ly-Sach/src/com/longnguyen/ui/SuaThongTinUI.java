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

import com.longnguyen.Service.NhaXuatBanService;
import com.longnguyen.model.NhaXuatBan;

public class SuaThongTinUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaNxB, txtDiaChi, txtTenNxB, txtDienThoai;
	
	private JButton btnSua;
	
	public String maNXB = "";
	NhaXuatBanService nhaXuatBanService;
	
	public  SuaThongTinUI(String title) {
		
		super(title);
		addControls();
		addEvents();
		if (maNXB.length() != 0) {
			hienThiThongTinChiTiet();
		}
	}

	public void hienThiThongTinChiTiet() {
		NhaXuatBanService nhaXuatBanService = new NhaXuatBanService();
		NhaXuatBan NXB = nhaXuatBanService.findByMaNXB(maNXB);
			
		txtMaNxB.setText(NXB.getMaNhaSanXuat());
		txtTenNxB.setText(NXB.getTenNhaSanXuat());
		txtDiaChi.setText(NXB.getDiaChi());
		txtDienThoai.setText(NXB.getSoDienThoi());
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLySua();
				dispose();
			}
		});
	}

	protected void xuLySua() {
		nhaXuatBanService = new NhaXuatBanService();	
		NhaXuatBan NXB = new NhaXuatBan();
		
		NXB.setMaNhaSanXuat(txtMaNxB.getText());
		NXB.setTenNhaSanXuat(txtTenNxB.getText());
		NXB.setDiaChi(txtDiaChi.getText());
		NXB.setSoDienThoi(txtDienThoai.getText());
		
		Boolean check = nhaXuatBanService.update(NXB);
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
		JLabel lblNxB = new JLabel("Thông tin nhà xuất bản");
		lblNxB.setForeground(Color.BLUE);
		Font ft = new Font("arial", Font.BOLD, 20);
		lblNxB.setFont(ft);
		pnNxB.add(lblNxB);
		pnChiTiet.add(pnNxB);
		
		JPanel pnMaNxB = new JPanel();
		JLabel lblMaNxB = new JLabel("Mã NXB: ");
		txtMaNxB = new JTextField(25);
		pnMaNxB.add(lblMaNxB);
		pnMaNxB.add(txtMaNxB);
		pnChiTiet.add(pnMaNxB);
		
		JPanel pnTenNxb = new JPanel();
		JLabel lblTenNxb = new JLabel("Tên NXB: ");
		txtTenNxB = new JTextField(25);
		pnTenNxb.add(lblTenNxb);
		pnTenNxb.add(txtTenNxB);
		pnChiTiet.add(pnTenNxb);
		
		JPanel pnDiaChi = new JPanel();
		JLabel lblDiaChi = new JLabel("Địa Chỉ: ");
		txtDiaChi = new JTextField(25);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnChiTiet.add(pnDiaChi);
		
		JPanel pnDienThoai = new JPanel();
		JLabel lblDienThoai = new JLabel("Điện thoại: ");
		txtDienThoai = new JTextField(25);
		pnDienThoai.add(lblDienThoai);
		pnDienThoai.add(txtDienThoai);
		pnChiTiet.add(pnDienThoai);
		
		JPanel pnButtonSua = new JPanel();
		btnSua = new JButton("Sửa");
		pnButtonSua.add(btnSua);
		pnChiTiet.add(pnButtonSua);
		
		//Chinh sua
		lblMaNxB.setPreferredSize(lblDienThoai.getPreferredSize());
		lblTenNxb.setPreferredSize(lblDienThoai.getPreferredSize());
		lblDiaChi.setPreferredSize(lblDienThoai.getPreferredSize());
		
	}
	
	public void showWindow() {
		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
