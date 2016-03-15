package com.apu.xml;

import com.apu.obj.Hotel;
import com.apu.util.Config;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HotelXML {
	
	private String file;
	
	public HotelXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "hotel.xml";
	}
	
	public boolean add(Hotel objHotel){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element hotel = doc.createElement("hotel");
			rootElement.appendChild(hotel);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "hotel")));
			hotel.setAttributeNode(attr);
	 
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(objHotel.getName()));
			hotel.appendChild(name);
	 
			Element street = doc.createElement("street");
			street.appendChild(doc.createTextNode(objHotel.getStreet()));
			hotel.appendChild(street);
	 
			Element city = doc.createElement("city");
			city.appendChild(doc.createTextNode(objHotel.getCity()));
			hotel.appendChild(city);
	 
			Element country = doc.createElement("country");
			country.appendChild(doc.createTextNode(objHotel.getCountry()));
			hotel.appendChild(country);
			
			Element postcode = doc.createElement("postCode");
			postcode.appendChild(doc.createTextNode(objHotel.getPostCode()));
			hotel.appendChild(postcode);
			
			Element status = doc.createElement("status");
			status.appendChild(doc.createTextNode(String.valueOf(objHotel.getStatus())));
			hotel.appendChild(status);
			
			Element phone = doc.createElement("phone");
			phone.appendChild(doc.createTextNode(objHotel.getPhone()));
			hotel.appendChild(phone);
			
			Element empId = doc.createElement("empId");
			empId.appendChild(doc.createTextNode(objHotel.getEmpId()));
			hotel.appendChild(empId);
	 		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<Hotel> getAll() {
		try {
			
			ArrayList<Hotel> objHotelList = new ArrayList<Hotel>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotel");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Hotel objHotel = new Hotel();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objHotel.setId(eElement.getAttribute("id"));
					objHotel.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					objHotel.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
					objHotel.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
					objHotel.setCountry(eElement.getElementsByTagName("country").item(0).getTextContent());
					objHotel.setPostCode(eElement.getElementsByTagName("postCode").item(0).getTextContent());
					objHotel.setStatus(Integer.parseInt(eElement.getElementsByTagName("status").item(0).getTextContent()));
					objHotel.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
					objHotel.setEmpId(eElement.getElementsByTagName("empId").item(0).getTextContent());
					
					objHotelList.add(objHotel);
				}
				
			}
			
			return objHotelList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Hotel get(int id) {
		try {
			
			Hotel objHotel = new Hotel();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotel");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objHotel.setId(eElement.getAttribute("id"));
						objHotel.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
						objHotel.setStreet(eElement.getElementsByTagName("street").item(0).getTextContent());
						objHotel.setCity(eElement.getElementsByTagName("city").item(0).getTextContent());
						objHotel.setCountry(eElement.getElementsByTagName("country").item(0).getTextContent());
						objHotel.setPostCode(eElement.getElementsByTagName("postCode").item(0).getTextContent());
						objHotel.setStatus(Integer.parseInt(eElement.getElementsByTagName("status").item(0).getTextContent()));
						objHotel.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
						objHotel.setEmpId(eElement.getElementsByTagName("empId").item(0).getTextContent());
					}
				}
				
			}
			
			return objHotel;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(Hotel objHotel) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotel");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objHotel.getId()))){
						eElement.getElementsByTagName("name").item(0).setTextContent(objHotel.getName());
						eElement.getElementsByTagName("street").item(0).setTextContent(objHotel.getStreet());
						eElement.getElementsByTagName("city").item(0).setTextContent(objHotel.getCity());
						eElement.getElementsByTagName("country").item(0).setTextContent(objHotel.getCountry());
						eElement.getElementsByTagName("postCode").item(0).setTextContent(objHotel.getPostCode());
						eElement.getElementsByTagName("status").item(0).setTextContent(String.valueOf(objHotel.getStatus()));
						eElement.getElementsByTagName("phone").item(0).setTextContent(objHotel.getPhone());
						eElement.getElementsByTagName("empId").item(0).setTextContent(objHotel.getEmpId());
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
			
			NodeList nList = doc.getElementsByTagName("hotel");
		 
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
