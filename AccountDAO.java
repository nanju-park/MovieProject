package movieproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.db.DBconn;


 

public class AccountDAO {

	//회원가입 기능을 제공하는 메소드 
	public int insertUserData(AccountDTO dto) {

		int result = 0;
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into custom (custom_pid,custom_id, custom_pw, custom_name, custom_tel) ";
			sql += "values (no_custom_seq.nextval,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getcId());
			pstmt.setString(2, dto.getcPwd());
			pstmt.setString(3, dto.getcName());
			pstmt.setString(4, dto.getcTel());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	public boolean userDataCheck(String cId, String cPwd) {
		boolean checkuser = false;
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs  = null;
		try {
			sql = "select custom_pw from custom where custom_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,cId);
			rs = pstmt.executeQuery();
			AccountDTO dto = new AccountDTO();
			while(rs.next()) {
				dto.setCheckpw(rs.getString("custom_pw"));
			}
			if(cPwd.equals(dto.getCheckpw())) {
				checkuser = true;
			}else {
				checkuser = false;
			}
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		return checkuser;
	}
	//아이디 중복 체크 
	public boolean useridOverlay(String cId) {
		boolean checkuser = false;
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs  = null;
		try {
			sql = "select custom_id from custom where custom_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,cId);
			rs = pstmt.executeQuery();
			AccountDTO dto = new AccountDTO();
			while(rs.next()) {
				dto.setCheckid(rs.getString("custom_id"));
			}
			if(cId.equals(dto.getCheckid())) {
				checkuser = true;
				System.out.println("중복된 아이디가 있습니다. ");
				System.out.println("다른 아이디를 입력해 주세요");
			}else {
				checkuser = false;
				System.out.println("사용가능한 아이디 입니다.");
			}
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		return checkuser;
	}
	public int getuserpid(String cId) {
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs  = null;
		AccountDTO dto = new AccountDTO();
		try {
			sql = "select custom_pid from custom where custom_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,cId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setCustom_pid(rs.getInt("custom_pid"));
			}
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		return dto.getCustom_pid();
	}
}



	