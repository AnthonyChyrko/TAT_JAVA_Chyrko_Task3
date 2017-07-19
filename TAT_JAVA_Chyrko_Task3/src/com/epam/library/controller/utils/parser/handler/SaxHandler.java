package com.epam.library.controller.utils.parser.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.parser.ParameterTagName;

public class SaxHandler extends DefaultHandler{
	private List<String> commandList = new ArrayList<String>();
	
	private Command command;
	
	private StringBuilder text;
	private StringBuilder commandRequest;
	public List<String> getCommandList(){
		return commandList;
	}
	
	public void startDocument() throws SAXException {
		System.out.println("Parsing started.");
	}
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended.");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		text = new StringBuilder();
		if (qName.equals("command")){
			commandRequest = new StringBuilder();
			commandRequest = commandRequest.append(qName.concat("=").concat(attributes.getValue("name")).concat("&"));			
		}
	}
	
	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println(uri+" - "+qName);
			ParameterTagName paramTagName = ParameterTagName.valueOf(qName.toUpperCase());
			if(paramTagName.equals(ParameterTagName.COMMAND)){
				commandList.add(""+commandRequest);
				commandRequest = null;
			}else if(!paramTagName.equals(ParameterTagName.COMMANDS)){
				commandRequest.append(qName.concat("=").concat(""+text).concat("&"));
			}
	}
	
	public void warning(SAXParseException exception) {
		System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
	}
	
	public void error(SAXParseException exception) {
		System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
	}
		
	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
		throw (exception);
	}
	
	
}
