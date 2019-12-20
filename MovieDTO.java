package movieproject;

public class MovieDTO {
	
	//1. 주석 
	//2. 
	
	//snack 
	int snack_id;
	String type;
	String name;
	int price;
	//snackorder 
	int s_order_id; //s_order_id pk : 음식주문번호 
	int p_snack_id; //p_snack_id : 팝콘번호 
	int p_order_su; //
	int j_snack_id;
	int j_order_su;
	
	/// 출력 
	int popsu;
	String popname;
	int jusu;
	String juname;
	
	//ticket 
	int ticket_id;
	int ticketprice = 10000;
	//seat 
	String seat_id;
	int issue;
	//theater
	int theater_id;
	int total_seat_number;
	//schedule
	int schedule_id;
	String schedule_date;
	//movie
	int movie_id;
	String title;
	String watch_time;
	//custom 
	int custom_pid;
	String custom_id;
	//payment
	int payment_id;
	String payment_date;
	int total_price;
	
	public String getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public int getTicketprice() {
		return ticketprice;
	}
	public void setTicketprice(int ticketprice) {
		this.ticketprice = ticketprice;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public int getCustom_pid() {
		return custom_pid;
	}
	public void setCustom_pid(int custom_pid) {
		this.custom_pid = custom_pid;
	}
	public int getPopsu() {
		return popsu;
	}
	public void setPopsu(int popsu) {
		this.popsu = popsu;
	}
	public String getPopname() {
		return popname;
	}
	public void setPopname(String popname) {
		this.popname = popname;
	}
	
	public int getJusu() {
		return jusu;
	}
	public void setJusu(int jusu) {
		this.jusu = jusu;
	}
	public String getJuname() {
		return juname;
	}
	public void setJuname(String juname) {
		this.juname = juname;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	public String getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public int getTotal_seat_number() {
		return total_seat_number;
	}
	public void setTotal_seat_number(int total_seat_number) {
		this.total_seat_number = total_seat_number;
	}
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getS_order_id() {
		return s_order_id;
	}
	public void setS_order_id(int s_order_id) {
		this.s_order_id = s_order_id;
	}
	public int getP_snack_id() {
		return p_snack_id;
	}
	public void setP_snack_id(int p_snack_id) {
		this.p_snack_id = p_snack_id;
	}
	public int getP_order_su() {
		return p_order_su;
	}
	public void setP_order_su(int p_order_su) {
		this.p_order_su = p_order_su;
	}
	public int getJ_snack_id() {
		return j_snack_id;
	}
	public void setJ_snack_id(int j_snack_id) {
		this.j_snack_id = j_snack_id;
	}
	public int getJ_order_su() {
		return j_order_su;
	}
	public void setJ_order_su(int j_order_su) {
		this.j_order_su = j_order_su;
	}
	public int getSnack_id() {
		return snack_id;
	}
	public void setSnack_id(int snack_id) {
		this.snack_id = snack_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(int theater_id) {
		this.theater_id = theater_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}
	public String movietoString() {
		String movielist = String.format("%8d %10s %11s %8s %5d ",schedule_id,title,schedule_date,theater_id,total_seat_number);
		return movielist;
	}

	public String snacktoString() {
		String snacklist = String.format("%8d %8s %8s %10s ",snack_id,type,name,price);
		return snacklist;
	}
	public String snacktoorderString() {
		String snacklist = String.format("%8d %8s %10d %10s %8d ",s_order_id,popname,popsu,juname,jusu);
		return snacklist;
	}
	public String printseatString() {
		String seatlist = String.format("%5s",seat_id);
		return seatlist;
	}
	public String totalcash_print() {
		String total = String.format("%10d", total_price);
		return total;
	}
}
