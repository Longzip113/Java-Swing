package com.longnguyen.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.longnguyen.Service.SachService;
import com.longnguyen.model.Sach;

public class TimKiemUI extends JFrame{

	private static final long serialVersionUID = 1L;

	JTextField txtTimKiem;
	JButton btnBatDauTim, btnThem;
	DefaultTableModel dtmSach;
	JTable tblSach;
	
	SachService sachService;
	JMenuItem mNuEdit, mNuDelete;
	JPopupMenu popupMenu;
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
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SuaThongTinSachUI ui = new SuaThongTinSachUI("Thông tin chi tiết ", false);
				ui.showWindow();
			}
		});
		
		tblSach.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tblSach.rowAtPoint(e.getPoint());// Lấy dong tại vị trí ta click 
				int col = tblSach.columnAtPoint(e.getPoint()); // Lấy cot tại vị trí ta click 
				if(e.isPopupTrigger()) //Vừa nhấn chuôt phải
				{
					if (! tblSach.isRowSelected(row)) {
						tblSach.changeSelection(row, col, false, false);
					}
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblSach.rowAtPoint(e.getPoint());// Lấy dong tại vị trí ta click 
				int col = tblSach.columnAtPoint(e.getPoint()); // Lấy cot tại vị trí ta click 
				if(e.isPopupTrigger()) //Vừa nhấn chuôt phải
				{
					if (! tblSach.isRowSelected(row)) {
						tblSach.changeSelection(row, col, false, false);
					}
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
		
			}
		});
		mNuEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblSach.getSelectedRow();
				if(row == -1) return;
				String maSach = tblSach.getValueAt(row, 0) + ""; 
				
				SuaThongTinSachUI STTS = new SuaThongTinSachUI("Thông tin chi tiết", true);
				STTS.maSach = maSach;
				STTS.hienThiThongTinChiTiet();
				STTS.showWindow();
				
			}
		});
		mNuDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblSach.getSelectedRow();
				if(row == -1) return;
				String maSach = tblSach.getValueAt(row, 0) + "";
				
				int yesOrNo = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hay không!", "Delete row ",JOptionPane.YES_NO_OPTION);
				if(yesOrNo == 0) {
					sachService = new SachService();
					Boolean check = sachService.delete(maSach);
					if (check) {
						JOptionPane.showConfirmDialog(null, "Xoa thành công");
					} else {
						JOptionPane.showConfirmDialog(null, "Xoa That bai");
					}
				} else {
					JOptionPane.showConfirmDialog(null, "Xoa That bai");
				}
				
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
		
		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnThem = new JButton("Thêm");
		pnSouth.add(btnThem);
		con.add(pnSouth, BorderLayout.SOUTH);
		
		popupMenu = new JPopupMenu();
		mNuEdit = new JMenuItem("Chỉnh sửa");
		mNuDelete = new JMenuItem("Xóa");
		popupMenu.add(mNuEdit);
		popupMenu.addSeparator();
		popupMenu.add(mNuDelete);
		
		tblSach.setComponentPopupMenu(popupMenu);
		
	}
	
	public void showWindow() {
		this.setSize(500, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
