package movieproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.db.DBconn;

public class MovieDAO {
	//회원 가입시 user 세션을 유지하기위해서 
	//테이블에 uniqueid값을 입력 
	
	public int userstartsession(MovieDTO dto) {
		
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		int result = 0;
		try {
			sql = "insert into payment (payment_id,custom_pid) values (no_payment_seq.nextval,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getCustom_pid());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	//snackorderlist추출 
	public List<MovieDTO> getSnackorderlist() {
		List<MovieDTO> lists = new ArrayList<MovieDTO>();
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql =   "select s_order_id,a.name popname,p_order_su popsu,b.name juname,j_order_su jusu from snack_order,snack a,snack b "; 
			sql +=	"where a.snack_id = snack_order.P_SNACK_ID and b.snack_id = snack_order.j_SNACK_ID order by s_order_id"; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			while(rs.next()) {	
				MovieDTO dto = new MovieDTO();
				dto.setS_order_id(rs.getInt("s_order_id"));
				dto.setPopname(rs.getString("popname"));			
				dto.setPopsu(rs.getInt("popsu"));
				dto.setJuname(rs.getString("juname"));
				dto.setJusu(rs.getInt("jusu"));
				lists.add(dto);
			}	
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	//snack 관리자 주문 쿼리 
	public int insertSnack(MovieDTO dto) {
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int result =0;
		try {
			conn.setAutoCommit(false);
			
			sql = "insert into snack_order  values (no_snackorder_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getP_snack_id());
			pstmt.setInt(2,dto.getP_order_su());
			pstmt.setInt(3,dto.getJ_snack_id());
			pstmt.setInt(4,dto.getJ_order_su());
			result = pstmt.executeUpdate();
			
			//s_order_id를 얻는 방법 
			sql = "select no_snackorder_seq.currval s_order_id from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setS_order_id(rs.getInt("s_order_id"));
			}
			sql = "select NO_PAYMENT_SEQ.currval payment_id from dual ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setPayment_id(rs.getInt("payment_id"));
			}
			sql = "update payment set s_order_id=? where payment_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getS_order_id());
			pstmt.setInt(2,dto.getPayment_id());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	public int snackpayment(MovieDTO dto) {
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		int result =0;
		try {
			sql = "update payment set s_order_id=? where custom_pid=? ";
			pstmt.setInt(1,dto.getS_order_id());
			pstmt.setInt(2,dto.getCustom_pid());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	//영화 예매
	public int reserverMovie(MovieDTO dto) {
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		
		int result =0;
		
		try {
			sql = "insert into snack_order  values (no_snackorder_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getP_snack_id());
			pstmt.setInt(2,dto.getP_order_su());
			pstmt.setInt(3,dto.getJ_snack_id());
			pstmt.setInt(4,dto.getJ_order_su());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	//영화 조회 
	public List<MovieDTO> getSnacklist() {
		
		List<MovieDTO> lists = new ArrayList<MovieDTO>();
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql =  "select snack_id,type,name,price from snack ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setSnack_id(rs.getInt("snack_id"));
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				lists.add(dto);
			}	
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	//영화 조회 
	public List<MovieDTO> getMovielist(){
		
		List<MovieDTO> lists = new ArrayList<MovieDTO>();
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql  = "select schedule_id,title,TO_CHAR(schedule_date,'hh24:mi') schedule_date,t.THEATER_ID ,TOTAL_SEAT_NUMBER "; 
			sql += "from schedule s,movie m,theater t "; 
			sql += "where s.MOVIE_ID=m.MOVIE_ID and s.THEATER_ID=t.THEATER_ID";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setSchedule_id(rs.getInt("schedule_id"));
				dto.setTitle(rs.getString("title"));
				dto.setSchedule_date(rs.getString("schedule_date"));
				dto.setTheater_id(rs.getInt("theater_id"));
				dto.setTotal_seat_number(rs.getInt("total_seat_number"));
				lists.add(dto);
			}	
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	public int insertMovie(MovieDTO dto) {
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;  
		String sql;
		int result = 0;
		try {
			sql = "insert into ticket values (no_ticketorder,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			/*pstmt.setInt(1,dto());
			pstmt.setString(2,dto());*/
			
			result = pstmt.executeUpdate();
			pstmt.close();	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	public List<MovieDTO> seatlist(MovieDTO dto){
		
		List<MovieDTO> lists = new ArrayList<MovieDTO>();
		List<MovieDTO> seatlists = new ArrayList<MovieDTO>();
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs = null;
		int result = 0;
		try {
			sql = "select theater_id from schedule where schedule_id= ? ";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getSchedule_id());
			rs  = pstmt.executeQuery();
			while(rs.next()) {
				dto.setTheater_id(rs.getInt("theater_id"));
				lists.add(dto);
			}
			Iterator<MovieDTO> it = lists.iterator();
			while(it.hasNext()) {
				dto = it.next();
				sql = "select seat_id from seat where theater_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,dto.getTheater_id());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setSeat_id(rs.getString("seat_id"));
					System.out.println(dto.printseatString());
				}
			}
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return seatlists;
	}
	public int paymentlist(MovieDTO dto,int custom_pid) {
		int result = 0;
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			sql = "select theater_id from schedule where schedule_id = ?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getSchedule_id());
			rs  = pstmt.executeQuery();
			while(rs.next()) {
				dto.setTheater_id(rs.getInt("theater_id"));
			}
			
			sql = "insert into ticket values (NO_TICKETORDER_SEQ.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,dto.getSchedule_id());
			pstmt.setInt(2,dto.getTheater_id());
			pstmt.setString(3, dto.getSeat_id());
			pstmt.setInt(4,dto.ticketprice);
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	public int paymentmovie(MovieDTO dto) {
		
		Connection conn = DBconn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int result = 0;
			try {
				conn.setAutoCommit(false);				
				sql = "select NO_PAYMENT_SEQ.currval payment_id from dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setPayment_id(rs.getInt("payment_id"));
				}
				
				sql = "select NO_TICKETORDER_SEQ.currval ticket_id from dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setTicket_id(rs.getInt("ticket_id"));
				}
				
				sql = "update payment set ticket_id=? where payment_id=?  ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,dto.getTicket_id());
				pstmt.setInt(2,dto.getPayment_id());
				result = pstmt.executeUpdate();
				System.out.println(dto.getTicket_id());
				conn.commit();
				System.out.println("영화 추가 완료 ");
				pstmt.close();
		   } catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	//total ticket
	
		//select no_ticketorder_seq.currval ticket_id from dual;
		//update
		//예매
		public List<MovieDTO> ticketReserve(int theater_id, String seat_id) {
			
			List<MovieDTO> lists = new ArrayList<>();
			Connection conn = DBconn.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			
			try {	
				//select theater_id, seat_id from seat
				//where theater_id = 1 and seat_id = 'A-1';
				conn.setAutoCommit(false);
				//insert into ticket values (no_ticketorder, schedule_id : int, theater_id : int, seat_id : String)
				sql = "insert into ticket(ticket_id, theater_id, seat_id) values (no_ticketorder_seq.nextval, ?, ?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, theater_id);
				pstmt.setString(2, seat_id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MovieDTO dto = new MovieDTO();
					dto.setTheater_id(rs.getInt("theater_id"));
					dto.setSeat_id(rs.getString("seat_id"));
					lists.add(dto);
				}
				rs.close();
				pstmt.close();
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			return lists;
		}
		
		
		//상영관 좌석 출력
		public List<MovieDTO> seatList(String seat_id) {
			
			List<MovieDTO> lists = new ArrayList<>();
			Connection conn = DBconn.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			
			
			try {
				
				sql = "select distinct seat_id from seat ";
				sql += "order by seat_id asc";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MovieDTO dto = new MovieDTO();
					dto.setSeat_id(rs.getString("seat_id"));
					lists.add(dto);
				}
				rs.close();
				pstmt.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
			return lists;
			
		}
		
		
		//티켓예매(상영번호, 좌석번호)
		//select theater_id, seat_id from seat
		//where theater_id = 1 and seat_id = 'A-1';
		public List<MovieDTO> movieReserve(int theater_id, String seat_id) {
			
			List<MovieDTO> lists = new ArrayList<>();
			Connection conn = DBconn.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			
			try {
				
				sql = "select theater_id, seat_id from seat ";
				sql += "where theater_id = ? and seat_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, theater_id);
				pstmt.setString(2, seat_id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					MovieDTO dto = new MovieDTO();
					dto.setTheater_id(rs.getInt("theater_id"));
					dto.setSeat_id(rs.getString("seat_id"));
					lists.add(dto);
				}
				rs.close();
				pstmt.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
			return lists;			
		}
		public int paymentorder(MovieDTO dto,int custom_pid) {
			List<MovieDTO> lists = new ArrayList<MovieDTO>();
			Connection conn = DBconn.getConnection();
			PreparedStatement pstmt = null;		
			String sql;
			ResultSet rs = null;
			int result = 0;
			try {
				conn.setAutoCommit(false);
				sql = "select NO_PAYMENT_SEQ.currval payment_id from dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setPayment_id(rs.getInt("payment_id"));
				}
				
				sql = "select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') payment_date from dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setPayment_date(rs.getString("payment_date"));
				}
				
				sql  = "select distinct ((p.PRICE*s.P_ORDER_SU)+(j.PRICE*s.J_ORDER_SU)+(t.PRICE)) total_price ";
				sql += "from snack_order s,snack p,snack j,custom c,payment,ticket t,schedule sch,movie mo,theater th ";  
				sql += "where payment.custom_pid=c.custom_pid and payment.s_order_id=s.s_order_id and s.P_SNACK_ID = p.SNACK_ID and s.J_SNACK_ID=j.SNACK_ID and ";
				sql += "payment.TICKET_ID=t.TICKET_ID and t.SCHEDULE_ID = sch.SCHEDULE_ID and sch.MOVIE_ID = mo.MOVIE_ID and sch.THEATER_ID = th.THEATER_ID";
				sql += " and payment_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,dto.getPayment_id());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setTotal_price(rs.getInt("total_price"));
				}
	
				sql = "update payment set total_price=?,payment_date=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') where payment_id=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,dto.getTotal_price());
				pstmt.setString(2,dto.getPayment_date());
				pstmt.setInt(3,dto.getPayment_id());
				result = pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
			return result;
	}
		
		//total 금액 나오는 메소드
		//int total_price;
		//select total_price, ticket_id from payment@link_test
		//where ticket_id = 122;
		//sql = "select NO_PAYMENT_SEQ.currval payment_id from dual";
		public List<MovieDTO> totalcash(MovieDTO dto) {
			
			List<MovieDTO> lists = new ArrayList<>();
			Connection conn = DBconn.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			
			try {
				//현재 payment_id를 받고
				sql = "select NO_PAYMENT_SEQ.currval payment_id from dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setCustom_pid(rs.getInt("payment_id"));
				}
				
				//pid를 조건?으로 줌
				sql = "select total_price from payment ";
				sql += "where payment_id = ?";
				rs = pstmt.executeQuery();
				pstmt.setInt(1, dto.getPayment_id());
				while(rs.next()) {
					//dto.setTotal_price(rs.getInt("total_price"));
					dto.setPayment_id(rs.getInt("payment_id"));
				}
				
				rs.close();
				pstmt.close();
			
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
			return lists;
		}
		
}
/*
 * 	sql  = "select distinct c.custom_pid custom_pid,c.custom_id custom_id, s.s_order_id s_order_id,p.type popname,s.P_ORDER_SU popsu,j.type juname,";
				sql += "s.J_ORDER_SU jusu,mo.TITLE title,sch.THEATER_ID theater,TO_CHAR(sch.SCHEDULE_DATE,'HH24:MI') schedule_date,";
				sql += "((p.PRICE*s.P_ORDER_SU)+(j.PRICE*s.J_ORDER_SU)+(t.PRICE)) tot_price ";
				sql += "from snack_order s,snack p,snack j,custom c,payment,ticket t,schedule sch,movie mo,theater th ";  
				sql += "where payment.custom_pid=c.custom_pid and payment.s_order_id=s.s_order_id and s.P_SNACK_ID = p.SNACK_ID and s.J_SNACK_ID=j.SNACK_ID and ";
				sql += "payment.TICKET_ID=t.TICKET_ID and t.SCHEDULE_ID = sch.SCHEDULE_ID and sch.MOVIE_ID = mo.MOVIE_ID and sch.THEATER_ID = th.THEATER_ID";
				
*/
