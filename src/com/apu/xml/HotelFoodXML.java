package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.HotelFood;
import com.apu.util.Config;

public class HotelFoodXML {
	
	
	private String file;
	
	public HotelFoodXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "hotelFood.xml";
	}
	
	public boolean add(HotelFood objHotelFood){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element hotelFood = doc.createElement("hotelFood");
			rootElement.appendChild(hotelFood);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "hotelFood")));
			hotelFood.setAttributeNode(attr);
			
			Element hotelId = doc.createElement("hotelId");
			hotelId.appendChild(doc.createTextNode(objHotelFood.getHotelId()));
			hotelFood.appendChild(hotelId);
	 
			Element foodId = doc.createElement("foodId");
			foodId.appendChild(doc.createTextNode(objHotelFood.getFoodId()));
			hotelFood.appendChild(foodId);
			  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<HotelFood> getAll() {
		try {
			
			ArrayList<HotelFood> objHotelFoodList = new ArrayList<HotelFood>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFood");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				HotelFood objHotelFood = new HotelFood();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objHotelFood.setId(eElement.getAttribute("id"));
					objHotelFood.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
					objHotelFood.setFoodId(eElement.getElementsByTagName("foodId").item(0).getTextContent());
					
					objHotelFoodList.add(objHotelFood);
				}
				
			}
			
			return objHotelFoodList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<HotelFood> getByHotelId(String hotelId) {
		try {
			
			ArrayList<HotelFood> objHotelFoodList = new ArrayList<HotelFood>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFood");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				HotelFood objHotelFood = new HotelFood();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getElementsByTagName("hotelId").item(0).getTextContent().equalsIgnoreCase(hotelId)){
						objHotelFood.setId(eElement.getAttribute("id"));
						objHotelFood.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objHotelFood.setFoodId(eElement.getElementsByTagName("foodId").item(0).getTextContent());
						
						objHotelFoodList.add(objHotelFood);
					}
				}
				
			}
			
			return objHotelFoodList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public HotelFood get(int id) {
		try {
			
			HotelFood objHotelFood = new HotelFood();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFood");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objHotelFood.setId(eElement.getAttribute("id"));
						objHotelFood.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objHotelFood.setFoodId(eElement.getElementsByTagName("foodId").item(0).getTextContent());
					}
				}
				
			}
			
			return objHotelFood;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(HotelFood objHotelFood) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFood");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objHotelFood.getId()))){
						eElement.getElementsByTagName("hotelId").item(0).setTextContent(objHotelFood.getHotelId());
						eElement.getElementsByTagName("foodId").item(0).setTextContent((objHotelFood.getFoodId()));
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
			
			NodeList nList = doc.getElementsByTagName("hotelFood");
		 
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
