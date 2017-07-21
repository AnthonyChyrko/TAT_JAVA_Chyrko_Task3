package com.epam.library.controller.utils.parser;

import java.io.IOException;

import org.xml.sax.SAXException;

public class RunnerParser {
	 static String name = "Himalaya";
	  static RunnerParser getMountain() {
	    System.out.println("Getting Name ");
	    return null;
	  }
	public static void main(String[] args) throws SAXException, IOException {
		new SAXparser().parseXML();
////		System.out.println( getMountain().getMountain().getMountain()  );
////		System.out.println( RunnerParser.name);
//		A aa = new A();
//		A ab = new B();
//	    B bb = new B();
//	   
////	    b.process(a);
//	    
//	    System.out.println(aa.i);
//	    System.out.println(ab.i);
//	    System.out.println(bb.i);
////	    System.out.println(aa.y);
////	    System.out.println(ab.y);
//	    System.out.println(bb.y);
//	    bb.process(aa);
//	    System.out.println( aa.getI() );
//	    System.out.println( bb.getI() );
	}
	
}
// class A{
//	  protected int i = 10;
//	  public int getI() { return i; }
//	}
//
//	//in file B.java
//
//
//	 class B extends A{
//		 protected int y = 30;
//		  public void process(A a) {
//			  a.i = a.i*2;
//			  i = a.i*3;
//			  System.out.println("U");
//		  }
//	 }
