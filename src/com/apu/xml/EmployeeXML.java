package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.Employee;
import com.apu.util.Config;

public class EmployeeXML {
	
	
	private String file;
	
	public EmployeeXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "employee.xml";
	}
	
	public boolean add(Employee objEmployee){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element employee = doc.createElement("employee");
			rootElement.appendChild(employee);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "employee")));
			employee.setAttributeNode(attr);
	 
			Element fname = doc.createElement("fname");
			fname.appendChild(doc.createTextNode(objEmployee.getFname()));
			employee.appendChild(fname);
	 
			Element lname = doc.createElement("lname");
			lname.appendChild(doc.createTextNode(objEmployee.getLname()));
			employee.appendChild(lname);
			
			Element street = doc.createElement("street");
			street.appendChild(doc.createTextNode(objEmployee.getStreet()));
			employee.appendChild(street);
	 
			Element city = doc.createElement("city");
			city.appendChild(doc.createTextNode(objEmployee.getCity()));
			employee.appendChild(city);
	 
			Element country = doc.createElement("country");
			country.appendChild(doc.createTextNode(objEmployee.getCountry()));
			employee.appendChild(country);
			
			Element postcode = doc.createElement("postCode");
			postcode.appendChild(doc.createTextNode(objEmployee.getPostCode()));
			employee.appendChild(postcode);
			
			Element phone = doc.createElement("phone");
			phone.appendChild(doc.createTextNode(objEmployee.getPhone()));
			employee.appendChild(phone);
			
			Element email = doc.createElement("email");
			email.appendChild(doc.createTextNode(String.valueOf(objEmployee.getEmail())));
			employee.appendChild(email);
	 		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<Employee> getAll() {
		try {
			
			ArrayList<Employee> objEmployeeList = new ArrayList<Employee>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("employee");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Employee objEmployee = new Employee();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objEmployee.setId(eElement.getAttribute("id"));
					objEmployee.setFname(eElement.getElementsByTagName("fname").item(0).getTextContent());
					objEmployee.setLname(eElement.getElementsByTagName("lname").item(0).getTextContent());
					objEmployee.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
					objEmployee.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
					objEmployee.setCountry(eElement.getElementsByTagName("country").item(0).getTextContent());
					objEmployee.setPostCode(eElement.getElementsByTagName("postCode").item(0).getTextContent());
					objEmployee.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
					objEmployee.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
					
					objEmployeeList.add(objEmployee);
				}
				
			}
			
			return objEmployeeList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Employee get(int id) {
		try {
			
			Employee objEmployee = new Employee();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("employee");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objEmployee.setId(eElement.getAttribute("id"));
						objEmployee.setFname(eElement.getElementsByTagName("fname").item(0).getTextContent());
						objEmployee.setLname(eElement.getElementsByTagName("lname").item(0).getTextContent());
						objEmployee.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
						objEmployee.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
						objEmployee.setCountry(eElement.getElementsByTagName("country").item(0).getTextContent());
						objEmployee.setPostCode(eElement.getElementsByTagName("postCode").item(0).getTextContent());
						objEmployee.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
						objEmployee.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
					}
				}
				
			}
			
			return objEmployee;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(Employee objEmployee) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("employee");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objEmployee.getId()))){
						eElement.getElementsByTagName("fname").item(0).setTextContent(objEmployee.getFname());
						eElement.getElementsByTagName("lname").item(0).setTextContent(objEmployee.getLname());
						eElement.getElementsByTagName("street").item(0).setTextContent(objEmployee.getStreet());
						eElement.getElementsByTagName("city").item(0).setTextContent(objEmployee.getCity());
						eElement.getElementsByTagName("country").item(0).setTextContent(objEmployee.getCountry());
						eElement.getElementsByTagName("postCode").item(0).setTextContent(objEmployee.getPostCode());
						eElement.getElementsByTagName("phone").item(0).setTextContent(objEmployee.getPhone());
						eElement.getElementsByTagName("email").item(0).setTextContent(objEmployee.getEmail());
						XMLReaWrite.wtiteXMLFile(doc, file);
						return true;
					}
				}
			}
			
			return false;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int id) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("employee");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						nNode.getParentNode().removeChild(nNode);
						XMLReaWrite.wtiteXMLFile(doc, file);
						return true;
					}
				}
				
			}
			
			return false;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


}
