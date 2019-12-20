package movieproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
 
public class Movie {
	
	MovieDAO dao = new MovieDAO();
	//session ���� 
	
	int custom_pid = 0;
	int s_order_id = 0;
	public Movie(int custom_pid) {
		this.custom_pid = custom_pid;
		MovieDTO dto = new MovieDTO();
		dto.setCustom_pid(custom_pid);
		int result = dao.userstartsession(dto);
		if(result != 0) {
			System.out.println("���� ���� ");
		}else {
			System.out.println("������ ������� ���� ");
		}
	}
	//��ȭ ��ȸ 
	public void movieselect() {
		List<MovieDTO> lists = dao.getMovielist();
		Iterator<MovieDTO> it = lists.iterator();
		System.out.println();
		System.out.println(" ��������������������������������������������������");
		System.out.println(" �󿵹�ȣ     ��ȭ��     �󿵽ð�  �󿵰� �����¼�");
		System.out.println(" ��������������������������������������������������");
		while(it.hasNext()) {
			MovieDTO dto = it.next();
			System.out.println(dto.movietoString());
		}
		System.out.println();
	}
	//���� ��ȸ 
	public void snackselect() {
		List<MovieDTO> lists = dao.getSnacklist();
		Iterator<MovieDTO> it = lists.iterator();
		System.out.println();
		System.out.println(" ������������������������������������������������");
		System.out.println(" ��ǰ��ȣ      ����       ��ǰ�̸�         ����");	
		System.out.println(" ������������������������������������������������");
		while(it.hasNext()) {
			MovieDTO dto = it.next();
			System.out.println(dto.snacktoString());
		}
		System.out.println();
		snackorder();
	}	
	//���� �ֹ� ��ȸ
	public void snackorderselect() {
		List<MovieDTO> lists = dao.getSnackorderlist();
		Iterator<MovieDTO> it = lists.iterator();
		System.out.println();
		System.out.println(" ������������������������������������������������������������");
		System.out.println("  �ֹ���ȣ    ���ܸ�       ���ܼ���      �����     ������� ");
		System.out.println(" ������������������������������������������������������������");
		while(it.hasNext()) {
			MovieDTO dto = it.next();
			System.out.println(dto.snacktoorderString());
			
		}
		
		System.out.println();
	}
	//���� �ֹ� 
	public void snackorder() {
		MovieDTO mo = new MovieDTO();
		mo.setCustom_pid(custom_pid);
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(" ������������������������");
			System.out.println(" �������� �ֹ��� �ּ��䦢");
			System.out.println(" ������������������������");
			System.out.print(" ���� [1~4�� �����ϼ���] : ");
			mo.setP_snack_id(sc.nextInt());
		}while(mo.getP_snack_id() < 1 || mo.getP_snack_id() > 4);
		
		System.out.print(" ���� : ");
		mo.setP_order_su(sc.nextInt());
		
		do {
			System.out.println(" ������������������������");
			System.out.println(" �������� �ֹ��� �ּ��䦢");
			System.out.println(" ������������������������");
			System.out.print(" ���� [5~7�� �����ϼ���] :");
			mo.setJ_snack_id(sc.nextInt());	
		}while(mo.getJ_snack_id() < 5 || mo.getJ_snack_id() > 7);
		System.out.print(" ���� : ");
		mo.setJ_order_su(sc.nextInt());
		
		int snackresult = 0;
		snackresult = dao.insertSnack(mo);
		
		if(snackresult != 0) {
			System.out.println();
			System.out.println(" ���ֹ� �Ϸᢽ");
			finalpayment();
		}else {
			System.out.println(" �ֹ� ���� !!");
		}
		
	}
	//��ȭ���� �Է� 
	public void Movieorder() {
		Scanner sc = new Scanner(System.in);
		MovieDTO dto = new MovieDTO();
		System.out.print(" �󿵹�ȣ�� �Է��ϼ��� : ");
		dto.setSchedule_id(sc.nextInt());
		seatprint(dto);
		System.out.print(" �¼��� �Է��ϼ��� ex [A-1] : ");
		dto.setSeat_id(sc.next());
		int resmovie = dao.paymentlist(dto,custom_pid);
		
		if(resmovie != 0) {
			System.out.println(" ticket table ������ �߰� ���� ");
		}else {
			System.out.println(" ticket table ������ �߰� ���� ");
		}
		int paymentmovie = dao.paymentmovie(dto);
		
		if(paymentmovie != 0) {
			System.out.println(" payment table �߰� ���� ");
		}else {
			System.out.println(" payment table �߰� ���� ");
		}
	}
	public void finalpayment() {
		Scanner sc = new Scanner(System.in);
		MovieDTO dto = new MovieDTO();
		System.out.println();
		System.out.println("���� ���� �Ͻðڽ��ϱ� ? ");
		System.out.println("1. �� 2. �ƴϿ� ");
		int checkqurey = sc.nextInt();
		//if else ������ 1�� ������ �ٷ� ����� �Ѿ���� �ϱ�
		if(checkqurey == 1) {
			dao.totalcash(dto);
			System.exit(0);
		}
		else
			Movieorder();
			/*int updatepayment = dao.paymentorder(dto,custom_pid);
			
			//if�� ���⿡ �׳ɾ���
			if(updatepayment != 0 ) {
				dao.totalcash(dto);
				System.out.println("update ���� ");
			}else {
				System.out.println("update ���� ");
			}*/
			
		}
		
		
		
	
	
	public void totalm(MovieDTO dto) {
		List<MovieDTO> lists = dao.totalcash(dto);
		Iterator<MovieDTO> it = lists.iterator();
		while(it.hasNext()) {
			MovieDTO li = it.next();
			System.out.println(dto.totalcash_print());
		}
	}
	
	
	public void seatprint(MovieDTO dto) {
		List<MovieDTO> lists = dao.seatlist(dto);
		Iterator<MovieDTO> it = lists.iterator();
		while(it.hasNext()) {
			MovieDTO seatlist = it.next();
			System.out.println(dto.printseatString());
		}
		System.out.println();
	}
	
	//���� �׸� ��ȸ 
	
}



