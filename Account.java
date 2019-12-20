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
		System.out.print(" ��й�ȣ: ");
		dto.setcPwd(sc.next());
		
		System.out.print(" �̸�: ");
		dto.setcName(sc.next());
		
		System.out.println(" ��ȭ��ȣ: (-���� �Է� �ϼ���) ");
		
		dto.setcTel(sc.next());
		
		int result = dao.insertUserData(dto);
		
		if(result!=0)
			System.out.println(" ȸ�� �߰� ����!");
		else
			System.out.println(" ȸ�� �߰� ����!");	
	}	
	public boolean userCheck() {
	System.out.print(" ���̵�: ");
		String id = sc.next();
		System.out.print(" ��й�ȣ: ");
		String pw = sc.next();
		setcustom_pid(id);
		//���� �����ϸ� �α��μ���, ���н� �α��� ���� �޼��� �߰��ϱ�
		boolean checkloginuser = dao.userDataCheck(id,pw);
		if(checkloginuser == true) {	
			System.out.println();
			System.out.println("����������������������������");
			System.out.println(" ���ݰ����ϴ� "+id+"�Ԣ� ");
			System.out.println("����������������������������");
		}else {
			System.out.println("����������������������������������");
			System.out.println("�����̵�� �н����尡 �ٸ��ϴ�. ��");
			System.out.println("����������������������������������");
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

