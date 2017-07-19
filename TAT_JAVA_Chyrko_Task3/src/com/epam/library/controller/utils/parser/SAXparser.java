package com.epam.library.controller.utils.parser;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.parser.handler.SaxHandler;

public class SAXparser {
	
	SaxHandler handler;
	XMLReader reader;
	public List<String> parseXML() throws SAXException, IOException{
		handler = new SaxHandler();
		reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(handler);
		reader.parse(new InputSource(".\\src\\commands.xml"));
		// включение проверки действительности
		reader.setFeature("http://xml.org/sax/features/validation", true);
		// включение обработки пространств имен
		reader.setFeature("http://xml.org/sax/features/namespaces", true);
		// включение канонизации строк
		reader.setFeature("http://xml.org/sax/features/string-interning", true);
		// отключение обработки схем
		reader.setFeature("http://apache.org/xml/features/validation/schema", false);	
		
		List<String> command = handler.getCommandList();
		for (String comm : command) {
			System.out.println(comm);
		}
		
		return command;
	}
}
