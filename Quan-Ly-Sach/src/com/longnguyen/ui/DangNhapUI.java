package com.longnguyen.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.longnguyen.Service.TaiKhoanService;
import com.longnguyen.model.TaiKhoan;

public class DangNhapUI extends JFrame{

	private static final long serialVersionUID = 1L;
	JTextField txtUseName,txtPassWord;
	
	JButton btnThoat, btnDangNhap;

	public DangNhapUI(String title) {
		super(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		btnDangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyDangNhap();
				
			}
		});
	}

	protected void xuLyDangNhap() {
		TaiKhoanService taiKhoanService = new TaiKhoanService();
		TaiKhoan taiKhoan = taiKhoanService.findByUserNameAndPassWord(txtUseName.getText(), txtPassWord.getText());
		if(taiKhoan.getUserName() != null) {
			QuanLySachUI ui = new QuanLySachUI("Quan ly sach");
			ui.showWindow();
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu sai xin kiểm tra lại !");
		}
	}

	private void addControls() {

		Container con = getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		JPanel pnTren = new JPanel();
		JPanel pnDuoi = new JPanel();
		con.add(pnTren);
		con.add(pnDuoi);
		
		pnTren.setLayout(new BoxLayout(pnTren, BoxLayout.Y_AXIS));
		JPanel pnLogIn = new JPanel();
		JLabel lblLogIn = new JLabel("Đăng Nhập");
		lblLogIn.setForeground(Color.BLUE);
		Font ft = new Font("arial", Font.BOLD, 20);
		lblLogIn.setFont(ft);
		pnLogIn.add(lblLogIn);
		pnTren.add(pnLogIn);
		
		JPanel pnUseName = new JPanel();
		JLabel lblUserName = new JLabel("Tài Khoản: ");
		txtUseName = new JTextField(25);
		pnUseName.add(lblUserName);
		pnUseName.add(txtUseName);
		pnTren.add(pnUseName);
		
		JPanel pnPassWord = new JPanel();
		JLabel lblPassWord = new JLabel("Mật Khẩu: ");
		txtPassWord = new JPasswordField(25);
		pnPassWord.add(lblPassWord);
		pnPassWord.add(txtPassWord);
		pnTren.add(pnPassWord);
		
		
		pnDuoi.setLayout(new FlowLayout());
		JPanel pnButtonDangNhap = new JPanel();
		btnDangNhap = new JButton("Đăng nhập");
		pnButtonDangNhap.add(btnDangNhap);
		pnDuoi.add(pnButtonDangNhap);
		btnDangNhap.setIcon(new ImageIcon("images/key.png"));
		
		JPanel pnButtonThoat = new JPanel();
		btnThoat = new JButton("Thoát");
		pnButtonThoat.add(btnThoat);
		pnDuoi.add(pnButtonThoat);
		btnThoat.setIcon(new ImageIcon("images/Button-Close-icon.png"));
		
		

	}
	
	public void showWindow() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
