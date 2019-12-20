package movieproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
 
public class Movie {
	
	MovieDAO dao = new MovieDAO();
	//session 시작 
	
	int custom_pid = 0;
	int s_order_id = 0;
	public Movie(int custom_pid) {
		this.custom_pid = custom_pid;
		MovieDTO dto = new MovieDTO();
		dto.setCustom_pid(custom_pid);
		int result = dao.userstartsession(dto);
		if(result != 0) {
			System.out.println("세션 시작 ");
		}else {
			System.out.println("세션이 연결되지 않음 ");
		}
	}
	//영화 조회 
	public void movieselect() {
		List<MovieDTO> lists = dao.getMovielist();
		Iterator<MovieDTO> it = lists.iterator();
		System.out.println();
		System.out.println(" ─────────────────────────");
		System.out.println(" 상영번호     영화명     상영시간  상영관 남은좌석");
		System.out.println(" ─────────────────────────");
		while(it.hasNext()) {
			MovieDTO dto = it.next();
			System.out.println(dto.movietoString());
		}
		System.out.println();
	}
	//스낵 조회 
	public void snackselect() {
		List<MovieDTO> lists = dao.getSnacklist();
		Iterator<MovieDTO> it = lists.iterator();
		System.out.println();
		System.out.println(" ────────────────────────");
		System.out.println(" 상품번호      종류       상품이름         가격");	
		System.out.println(" ────────────────────────");
		while(it.hasNext()) {
			MovieDTO dto = it.next();
			System.out.println(dto.snacktoString());
		}
		System.out.println();
		snackorder();
	}	
	//스낵 주문 조회
	public void snackorderselect() {
		List<MovieDTO> lists = dao.getSnackorderlist();
		Iterator<MovieDTO> it = lists.iterator();
		System.out.println();
		System.out.println(" ──────────────────────────────");
		System.out.println("  주문번호    팝콘명       팝콘수량      음료명     음료수량 ");
		System.out.println(" ──────────────────────────────");
		while(it.hasNext()) {
			MovieDTO dto = it.next();
			System.out.println(dto.snacktoorderString());
			
		}
		
		System.out.println();
	}
	//스낵 주문 
	public void snackorder() {
		MovieDTO mo = new MovieDTO();
		mo.setCustom_pid(custom_pid);
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println(" ┌──────────┐");
			System.out.println(" │스낵을 주문해 주세요│");
			System.out.println(" └──────────┘");
			System.out.print(" 팝콘 [1~4중 선택하세요] : ");
			mo.setP_snack_id(sc.nextInt());
		}while(mo.getP_snack_id() < 1 || mo.getP_snack_id() > 4);
		
		System.out.print(" 수량 : ");
		mo.setP_order_su(sc.nextInt());
		
		do {
			System.out.println(" ┌──────────┐");
			System.out.println(" │음료을 주문해 주세요│");
			System.out.println(" └──────────┘");
			System.out.print(" 음료 [5~7중 선택하세요] :");
			mo.setJ_snack_id(sc.nextInt());	
		}while(mo.getJ_snack_id() < 5 || mo.getJ_snack_id() > 7);
		System.out.print(" 수량 : ");
		mo.setJ_order_su(sc.nextInt());
		
		int snackresult = 0;
		snackresult = dao.insertSnack(mo);
		
		if(snackresult != 0) {
			System.out.println();
			System.out.println(" ♡주문 완료♡");
			finalpayment();
		}else {
			System.out.println(" 주문 실패 !!");
		}
		
	}
	//영화예매 입력 
	public void Movieorder() {
		Scanner sc = new Scanner(System.in);
		MovieDTO dto = new MovieDTO();
		System.out.print(" 상영번호를 입력하세요 : ");
		dto.setSchedule_id(sc.nextInt());
		seatprint(dto);
		System.out.print(" 좌석을 입력하세요 ex [A-1] : ");
		dto.setSeat_id(sc.next());
		int resmovie = dao.paymentlist(dto,custom_pid);
		
		if(resmovie != 0) {
			System.out.println(" ticket table 데이터 추가 성공 ");
		}else {
			System.out.println(" ticket table 데이터 추가 실패 ");
		}
		int paymentmovie = dao.paymentmovie(dto);
		
		if(paymentmovie != 0) {
			System.out.println(" payment table 추가 성공 ");
		}else {
			System.out.println(" payment table 추가 실패 ");
		}
	}
	public void finalpayment() {
		Scanner sc = new Scanner(System.in);
		MovieDTO dto = new MovieDTO();
		System.out.println();
		System.out.println("최종 결제 하시겠습니까 ? ");
		System.out.println("1. 예 2. 아니요 ");
		int checkqurey = sc.nextInt();
		//if else 문으로 1을 누르면 바로 종료로 넘어가도록 하기
		if(checkqurey == 1) {
			dao.totalcash(dto);
			System.exit(0);
		}
		else
			Movieorder();
			/*int updatepayment = dao.paymentorder(dto,custom_pid);
			
			//if문 여기에 그냥쓰기
			if(updatepayment != 0 ) {
				dao.totalcash(dto);
				System.out.println("update 성공 ");
			}else {
				System.out.println("update 실패 ");
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
	
	//결제 항목 조회 
	
}



