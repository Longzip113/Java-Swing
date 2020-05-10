package com.longnguyen.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.longnguyen.Service.NhaXuatBanService;
import com.longnguyen.Service.SachService;
import com.longnguyen.model.NhaXuatBan;
import com.longnguyen.util.PagingUtils;

public class QuanLySachUI extends JFrame{

	private static final long serialVersionUID = 1L;
	JTextField txtMaNxB,txtTenNxB,txtDiaChi,txtDienThoai;
	JButton btnVeTruoc,btnVeSau;
	JLabel lblStep;
	
	JButton btnThem, btnLuu, btnSua, btnXoa;
	
	DefaultTableModel dtmNxB;
	JTable tblNxB;
	
	JButton btnTimKiem, btnDangXuat;
	
	ArrayList<NhaXuatBan> DSNXB = null;
	
	private PagingUtils pagingUtils = null;
	private NhaXuatBan nhaXuatBan = new NhaXuatBan();
	private NhaXuatBanService nhaXuatBanService = new NhaXuatBanService();
	private SachService sachService = new SachService();
	private Integer page = 1;
	private Integer limit = 2;
	
	public QuanLySachUI(String title) {
		super(title);
		pagingUtils = new PagingUtils(page, limit);
		nhaXuatBan.setPage(pagingUtils.getPage());
		nhaXuatBan.setLimit(pagingUtils.getLimit());
		nhaXuatBan.setTotalItem(nhaXuatBanService.countItem());
		nhaXuatBan.setTotalPage((int) Math.ceil((double) nhaXuatBan.getTotalItem() /nhaXuatBan.getLimit()));
		addControls();
		addEvents();
		hienThiToanBoNhaSanXuat();
	}

	private void hienThiToanBoNhaSanXuat() {
		nhaXuatBanService = new NhaXuatBanService();
		DSNXB = nhaXuatBanService.findAll(pagingUtils);
		dtmNxB.setRowCount(0);
		for (NhaXuatBan nhaXuatBan : DSNXB) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(nhaXuatBan.getMaNhaSanXuat());
			vec.add(nhaXuatBan.getTenNhaSanXuat());
			vec.add(nhaXuatBan.getDiaChi());
			vec.add(nhaXuatBan.getSoDienThoi());
			dtmNxB.addRow(vec);
		}
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimKiemUI ui = new TimKiemUI("Tim kiem");
				ui.showWindow();
			}
			
		});
		
		btnDangXuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DangNhapUI ui = new DangNhapUI("Đăng nhập");
				ui.showWindow();
				dispose();
			}
		});
		
		btnVeSau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				page += 1;
				System.out.println("da nhan");
				pagingUtils = new PagingUtils(page, limit);
				hienThiToanBoNhaSanXuat();
			}
		});
		
		btnVeTruoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				page -= 1;
				System.out.println("da nhan");
				pagingUtils = new PagingUtils(page, limit);
				hienThiToanBoNhaSanXuat();
			}
		});
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyThem();// TODO Auto-generated method stub
				
			}
		});
		
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNxB.getSelectedRow();
				if(row == -1) return;
				String maNXB = tblNxB.getValueAt(row, 0) + ""; 
				
				SuaThongTinUI STT = new SuaThongTinUI("Sửa thông tin");
				STT.maNXB = maNXB;
				STT.hienThiThongTinChiTiet();
				STT.showWindow();
			}
		});
		
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNxB.getSelectedRow();
				if(row == -1) return;
				String maNXB = tblNxB.getValueAt(row, 0) + "";
				
				int yesOrNo = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hay không!", "Delete row ",JOptionPane.YES_NO_OPTION);
				if(yesOrNo == 0) {
					int soLuongSach = checkNXB(maNXB);
					if (soLuongSach != 0) {
						JOptionPane.showMessageDialog(null, "Bạn có " + soLuongSach + " cuốn sách của nhà xuất bản chưa xóa xin kiểm tra lại !"
														,"Cảnh báo !",JOptionPane.WARNING_MESSAGE);
					}else {
						nhaXuatBanService.delete(maNXB);
					}
				} else {
					JOptionPane.showConfirmDialog(null, "Xoa That bai");
				}
			}
		});
		
	}


	protected int checkNXB(String maNXB) {
		// TODO Auto-generated method stub
		return sachService.countItem(maNXB);
	}

	protected void xuLyThem() {
		NhaXuatBan NXB = new NhaXuatBan();
		
		NXB.setMaNhaSanXuat(txtMaNxB.getText());
		NXB.setTenNhaSanXuat(txtTenNxB.getText());
		NXB.setDiaChi(txtDiaChi.getText());
		NXB.setSoDienThoi(txtDienThoai.getText());
		
		Boolean check = nhaXuatBanService.insert(NXB);
		if(check) {
			JOptionPane.showMessageDialog(null, "Thêm thành công !");
		} else {
			JOptionPane.showMessageDialog(null, "Thêm thất bại !");
		}
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		JPanel pnCenter = new JPanel();
		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Chia vung
		con.add(pnNorth, BorderLayout.NORTH);
		con.add(pnCenter,BorderLayout.CENTER);
		con.add(pnSouth, BorderLayout.SOUTH);
		
		pnNorth.setLayout(new BorderLayout());
		JPanel pnChiTiet = new JPanel();
		pnNorth.add(pnChiTiet, BorderLayout.CENTER);
		JPanel pnThucHien = new JPanel();
		pnNorth.add(pnThucHien, BorderLayout.EAST);

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
		
		JPanel pnButtonChiTiet = new JPanel();
		btnVeTruoc = new JButton("Về trước");
		lblStep = new JLabel(""+nhaXuatBan.getPage() + "/" + nhaXuatBan.getTotalPage() +"");
		btnVeSau = new JButton("Về sau");
		pnButtonChiTiet.add(btnVeTruoc);
		pnButtonChiTiet.add(lblStep);
		pnButtonChiTiet.add(btnVeSau);
		pnChiTiet.add(pnButtonChiTiet);
		//=================================================================== Trên phải ==========================
		
		
		pnThucHien.setLayout(new BoxLayout(pnThucHien, BoxLayout.Y_AXIS));
		JPanel pnButtonThem = new JPanel();
		btnThem = new JButton("Thêm");
		pnButtonThem.add(btnThem);
		pnThucHien.add(pnButtonThem);
		
		JPanel pnButtonLuu = new JPanel();
		btnLuu = new JButton("Lưu");
		pnButtonLuu.add(btnLuu);
		pnThucHien.add(pnButtonLuu);
		
		JPanel pnButtonSua = new JPanel();
		btnSua = new JButton("Sửa");
		pnButtonSua.add(btnSua);
		pnThucHien.add(pnButtonSua);
		
		JPanel pnButtonXoa = new JPanel();
		btnXoa = new JButton("Xóa");
		pnButtonXoa.add(btnXoa);
		pnThucHien.add(pnButtonXoa);
		
		pnCenter.setLayout(new BorderLayout());
		dtmNxB = new DefaultTableModel();
		dtmNxB.addColumn("Mã Nhà Xuất Bản:");
		dtmNxB.addColumn("Tên Xuất Bản:");
		dtmNxB.addColumn("Địa chỉ:");
		dtmNxB.addColumn("Số điện thoại:");
		tblNxB = new JTable(dtmNxB);
		JScrollPane scTable = new JScrollPane(tblNxB,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnCenter.add(scTable, BorderLayout.CENTER);
		
		JPanel pnButtonOfSouth = new JPanel();
		pnButtonOfSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnTimKiem = new JButton("Tìm Kiếm");
		pnButtonOfSouth.add(btnTimKiem);
		pnSouth.add(pnButtonOfSouth);
		
		JPanel pnButtonDangXuat = new JPanel();
		pnButtonDangXuat.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnDangXuat = new JButton("Đăng xuất");
		pnButtonDangXuat.add(btnDangXuat);
		pnSouth.add(pnButtonDangXuat);
		
		
		//kẻ khung
		TitledBorder borderThongTinChiTiet = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED)
				,"Thông tin chi tiết");
		pnChiTiet.setBorder(borderThongTinChiTiet);
		
		TitledBorder borderThucHien = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE)
				,"Thực hiện");
		pnThucHien.setBorder(borderThucHien);
		
		TitledBorder borderThongTin = new TitledBorder(
				BorderFactory.createLineBorder(Color.BLUE)
				,"Danh Sách Nhà Xuất Bản");
		pnCenter.setBorder(borderThongTin);
		
		//Chinh sua
		lblMaNxB.setPreferredSize(lblDienThoai.getPreferredSize());
		lblTenNxb.setPreferredSize(lblDienThoai.getPreferredSize());
		lblDiaChi.setPreferredSize(lblDienThoai.getPreferredSize());
		
		//Chinh sua icon
		btnThem.setIcon(new ImageIcon("images/Button-Add-icon.png"));
		btnXoa.setIcon(new ImageIcon("images/Button-Close-icon.png"));
		btnLuu.setIcon(new ImageIcon("images/Save-icon.png"));
		btnSua.setIcon(new ImageIcon("images/Change-icon.png"));
		
		btnVeSau.setIcon(new ImageIcon("images/Button-vesau-icon.png"));
		btnVeTruoc.setIcon(new ImageIcon("images/Button-vetruoc-icon.png"));
		
		btnTimKiem.setIcon(new ImageIcon("images/Search-icon.png"));
		
		
	}
	public void showWindow() {
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
