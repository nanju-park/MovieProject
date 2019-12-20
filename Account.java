package movieproject;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Account {
	
	AccountDAO dao = new AccountDAO();
	Scanner sc = new Scanner(System.in);
	int custom_pid = 0;
	
	public void userInsert() {
		
		AccountDTO dto = new AccountDTO();
		do {
			System.out.print(" ID: ");
			dto.setcId(sc.next());
		}while(dao.useridOverlay(dto.getcId())==true); 
		System.out.print(" 비밀번호: ");
		dto.setcPwd(sc.next());
		
		System.out.print(" 이름: ");
		dto.setcName(sc.next());
		
		System.out.println(" 전화번호: (-없이 입력 하세요) ");
		
		dto.setcTel(sc.next());
		
		int result = dao.insertUserData(dto);
		
		if(result!=0)
			System.out.println(" 회원 추가 성공!");
		else
			System.out.println(" 회원 추가 실패!");	
	}	
	public boolean userCheck() {
	System.out.print(" 아이디: ");
		String id = sc.next();
		System.out.print(" 비밀번호: ");
		String pw = sc.next();
		setcustom_pid(id);
		//만약 성공하면 로그인성공, 실패시 로그인 실패 메세지 뜨게하기
		boolean checkloginuser = dao.userDataCheck(id,pw);
		if(checkloginuser == true) {	
			System.out.println();
			System.out.println("──────────────");
			System.out.println(" ♡반갑습니다 "+id+"님♡ ");
			System.out.println("──────────────");
		}else {
			System.out.println("┌───────────────┐");
			System.out.println("│아이디와 패스워드가 다릅니다. │");
			System.out.println("└───────────────┘");
		}
		return checkloginuser;
	}
	public void setcustom_pid(String cId) {
		custom_pid = dao.getuserpid(cId);
	}
	public int getcustom_pid() {
		return custom_pid;
	}
}

