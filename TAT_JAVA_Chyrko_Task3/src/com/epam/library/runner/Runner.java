package com.epam.library.runner;

import com.epam.library.bean.User;
import com.epam.library.controller.Controller;


public class Runner {	

	public static void main(String[] args) {
//		User user = User.getInstance();		
		
//		user.setUser(login, password, "IN" , "dfsafasfdSA"); //user in system
//		user.setUser("����", "password2", "IN" , "U"); //user in system
//		user.setUser(3, "Semas", "qwerty", "IN" , "A"); //user in system
//		user.setUser("Anton", "password4", "IN" , "SA"); //user in system


//		String request = "command=show_all_books&login=Ѹ��&access=A";  //���������� ��� �����
//		String request = "command=add_book&title=MyFirstBook1&author=Chyrko Anton"
//				+ "&genre=fantasy&year=2017&quantity=1";   // �������� �����
		String request1 = "command=registration&login=Perswe123w3&password=pAss11";   // ����������� �����
//		String request1 = "command=sign_in&login=Semas&password=qwerty";    // ���� � �������
//		String request = "command=sign_out&login=Anton";					// ����� �� �������
//		String request = "command=edit_Login&login=Sema"; // ������������� login
//		String request = "command=edit_Password&password=newPassword"; // ������������� password
//		String request = "command=edit_access&login=Kol&access=U"; // ������������� access
//		String request = "command=ban_user&login=Ѹ��&signIn=BAN"; // ban User
//		String request = "command=add_book&title=SuperBookFree2&author=Somebody Else&year=2016&genre=fantastik&quantity=11&availability=Y"; //add new book
//		String request1 = "command=EDIT_ORDER_BOOKS_LIST&ACTION_COMMAND=Add_book&B_ID=4"; //edit orderBooksList ���� ��������� �� ����� �����
//		String request2 = "command=EDIT_ORDER_BOOKS_LIST&ACTION_COMMAND=Add_book&B_ID=4"; //edit orderBooksList ���� ��������� �� ����� �����
//		String request3 = "command=EDIT_ORDER_BOOKS_LIST&ACTION_COMMAND=remove_book&B_ID=5"; //edit orderBooksList ���� ��������� �� ����� �����
//		String request4 = "command=EDIT_ORDER_BOOKS_LIST&ACTION_COMMAND=remove_book&B_ID=6"; //edit orderBooksList ���� ��������� �� ����� �����
//		String request3 = "command=ADD_SUBSCRIPTION&"; //subscription ����� ��������� � ���� ������
//		String request4 = "command=REMOVE_SUBSCRIPTION&U_ID=3&B_ID=6"; //subscription ����� ��������� � ���� ������
		
//		String request = "command=BOOK_AVAILABILITY&title=SuperBook3&availability=Y&author=andCompany&year=2017&genre=fantastik&quantity=50&book_id=4&"; //book availability
//		String request = "command=edit_book&title=SuperBook&author=Anton&genre=fantastikAndProgramming&quantity=49&book_id=8&"; //edit dook
		Controller contr = new Controller();
		System.out.println(contr.executeTask(request1)+"\n--------------------------------------------------------------------------\n");
//		System.out.println(contr.executeTask(request2)+"\n--------------------------------------------------------------------------\n");
//		System.out.println(contr.executeTask(request3)+"\n--------------------------------------------------------------------------\n");
//		System.out.println(contr.executeTask(request4)+"\n--------------------------------------------------------------------------\n");
//		System.out.println("current user"+user.toString());
		
	}
}
