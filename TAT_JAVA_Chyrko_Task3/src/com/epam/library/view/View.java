package com.epam.library.view;

import com.epam.library.controller.Controller;

public class View {	
	String request1 = "command=registration&login=Perswe123w3&password=pAssyu11";
	public void start(){		
		Controller controller = new Controller();
        System.out.println(controller.init());
        System.out.println(controller.executeTask(request1));
        System.out.println(controller.destroy());
    }
}
