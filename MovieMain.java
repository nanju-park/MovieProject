package movieproject;

import java.util.Scanner;

import com.db.DBconn;

public class MovieMain {

	public void Moviemeue(int custom_pid) {
		Scanner sc = new Scanner(System.in);
		Movie mo = new Movie(custom_pid);
		int ch;
		try {
			while(true) {
				do {
					System.out.println();
					System.out.println("������������������������������������������������������������������������������");
					System.out.println("��1.��ȭ��ȸ��2.��ȭ���Ŧ�3.������ȸ��4.������ȸ��5.�����ֹ���6.������7.���ᦢ");
					System.out.println("������������������������������������������������������������������������������");
					System.out.print(" �޴����� �����ϼ��� : ");
					ch = sc.nextInt();
				}while(ch<1||ch>7);
				switch(ch){
				case 1:
					mo.movieselect();
					break;
				case 2:
					mo.Movieorder();
					break;
				case 3:
					System.out.println("���� ���� �ȵ� �޼ҵ� : ������ȸ ");
					break;
				case 4:
					mo.snackselect();
					break;
				case 5:
					mo.snackorder();
					break;
				case 6:
					mo.finalpayment();
					break;
				case 7:
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

