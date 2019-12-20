package movieproject;

import java.util.Scanner;
import com.db.DBconn;

public class AccountMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Account ac = new Account();
		MovieMain moviemain = new MovieMain();
		int ch;
		try {
			while(true) {
				do { 
					System.out.println();
					System.out.println("       ,o888888o.     8888  b.             88 8888888888            ,8.       ,8.                   .8.         ");          
					System.out.println("      8888     `88.   8888  888o.          88 8888                 ,888.     ,888.                 .888.         ");
					System.out.println("   ,8 8888       `8.  8888  Y88888o.       88 8888                .`8888.   .`8888.               :88888.        ");
					System.out.println("   88 8888            8888  .`Y888888o.    88 8888               ,8.`8888. ,8.`8888.             . `88888.       ");
					System.out.println("   88 8888            8888  8o. `Y888888o. 88 888888888888      ,8'8.`8888,8^8.`8888.           .8. `88888.      ");
					System.out.println("   88 8888            8888  8`Y8o. `Y88888o88 8888             ,8' `8.`8888' `8.`8888.         .8`8. `88888.     ");
					System.out.println("   88 8888            8888  8   `Y8o. `Y88888 8888            ,8'   `8.`88'   `8.`8888.       .8' `8. `88888.    ");
					System.out.println("   `8 8888       .8'  8888  8      `Y8o. `Y88 8888           ,8'     `8.`'     `8.`8888.     .8'   `8. `88888.   ");
					System.out.println("      8888     ,88'   8888  8         `Y8o.`8 8888          ,8'       `8        `8.`8888.   .888888888. `88888.  ");
					System.out.println("       `8888888P'     8888  8            `Yo8 888888888888 ,8'         `         `8.`8888. .8'       `8. `88888. ");
					System.out.println();
					System.out.println("┌─────┬────┬───┐");		
					System.out.println("│1.회원가입│2.로그인│3.종료│");
					System.out.println("└─────┴────┴───┘");
					System.out.print(" 메뉴를 선택하세요  : ");
					ch = sc.nextInt();
				}while(ch< 1|| ch> 3 );
				switch(ch) {
				case 1:
					ac.userInsert();
					break;
				case 2:
					//로그인시 입력된 정보가 맞으면 true
					//입력된 값과 다르면 false를 출력합니다. 
					if(ac.userCheck() == true){
						moviemain.Moviemeue(ac.getcustom_pid());
						break;
					}else {
						break;
					}
				case 3:
					System.out.println();
					System.out.println("888888 88888888 8 888       8          .8.          b.             8 8 888     ,88' `8.`888.      ,8'     ,o88888o.     8 888      88"); 
					System.out.println("     8 888      8 888       8         .888.         888o.          8 8 888    ,88'   `8.`888.    ,8'   . 888     `88.   8 888      88"); 
					System.out.println("     8 888      8 888       8        :88888.        Y88888o.       8 8 888   ,88'     `8.`888.  ,8'   ,8 888       `8b  8 888      88"); 
					System.out.println("     8 888      8 888       8       . `88888.       .`Y888888o.    8 8 888  ,88'       `8.`888.,8'    88 888        `8b 8 888      88"); 
					System.out.println("     8 888      8 888       8      .8. `88888.      8o. `Y888888o. 8 8 888 ,88'         `8.`8888'     88 888         88 8 888      88"); 
					System.out.println("     8 888      8 888       8     .8`8. `88888.     8`Y8o. `Y88888o8 8 888 88'           `8. 888      88 888         88 8 888      88"); 
					System.out.println("     8 888      8 88888888888    .8' `8. `88888.    8   `Y8o. `Y8888 8 88888<             `8 888      88 888        ,8P 8 888      88"); 
					System.out.println("     8 888      8 888       8   .8'   `8. `88888.   8      `Y8o. `Y8 8 888 `Y8.            8 888      `8 888       ,8P  ` 888     ,8P"); 
					System.out.println("     8 888      8 888       8  .888888888. `88888.  8         `Y8o.` 8 888   `Y8.          8 888       ` 888     ,88'     888   ,d8P "); 
					System.out.println("     8 888      8 888       8 .8'       `8. `88888. 8            `Yo 8 888     `Y8.        8 888          `888888P'        `Y8888P'  "); 
					System.out.println();
					DBconn.close();
					System.exit(0);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
