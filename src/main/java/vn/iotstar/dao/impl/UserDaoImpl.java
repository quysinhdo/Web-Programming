package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.User;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao{
	
	public Connection con = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM users ";
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("email"), rs.getString("username"),
						rs.getString("fullname"), rs.getString("password"), rs.getString("images"),
						rs.getString("phone"), rs.getInt("roleid"), rs.getDate("createDate")));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findAId(int id) {
		String sql = "SELECT * FROM Users WHERE id = ? ";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(username, password, images, fullname, email, phone, roleid,createDate) VALUES (?,?,?,?,?,?,?,?)";

		try {
			con = super.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getImages());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getRoleid());
			ps.setDate(8, user.getCreateDate());

			ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public UserModel FindByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from users where email = ?";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			con.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from users where username = ?";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			con.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(String password, String email) {
		String sql = "UPDATE Users SET password = ? WHERE email = ?";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> search(String username) {
		String sql = "SELECT * FROM Users WHERE username = ?";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<String> user = new ArrayList<String>();
				user.add(rs.getString("username"));
				user.add(rs.getString("images"));
				user.add(rs.getString("fullname"));
				user.add(rs.getString("phone"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateProfile(String username, String fullname, String phone, String images) {
		String sql = "UPDATE Users SET fullname = ?, phone = ?, images = ? WHERE username = ?  ";
		try {
			con = new DBConnectSQL().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, fullname);
			ps.setString(2, phone);
			ps.setString(3, images);
			ps.setString(4, username);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl();
			System.out.println(userDao.FindByUserName("sinhdq"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
