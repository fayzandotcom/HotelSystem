package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.HotelFacility;
import com.apu.util.Config;

public class HotelFacilityXML {
	
	
	private String file;
	
	public HotelFacilityXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "hotelFacility.xml";
	}
	
	public boolean add(HotelFacility objHotelFacility){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element hotelFacility = doc.createElement("hotelFacility");
			rootElement.appendChild(hotelFacility);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "hotelFacility")));
			hotelFacility.setAttributeNode(attr);
			
			Element hotelId = doc.createElement("hotelId");
			hotelId.appendChild(doc.createTextNode(objHotelFacility.getHotelId()));
			hotelFacility.appendChild(hotelId);
	 
			Element facilityId = doc.createElement("facilityId");
			facilityId.appendChild(doc.createTextNode(objHotelFacility.getFacilityId()));
			hotelFacility.appendChild(facilityId);
			  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<HotelFacility> getAll() {
		try {
			
			ArrayList<HotelFacility> objHotelFacilityList = new ArrayList<HotelFacility>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFacility");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				HotelFacility objHotelFacility = new HotelFacility();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objHotelFacility.setId(eElement.getAttribute("id"));
					objHotelFacility.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
					objHotelFacility.setFacilityId(eElement.getElementsByTagName("facilityId").item(0).getTextContent());
					
					objHotelFacilityList.add(objHotelFacility);
				}
				
			}
			
			return objHotelFacilityList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<HotelFacility> getByHotelId(String hotelId) {
		try {
			
			ArrayList<HotelFacility> objHotelFacilityList = new ArrayList<HotelFacility>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFacility");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				HotelFacility objHotelFacility = new HotelFacility();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getElementsByTagName("hotelId").item(0).getTextContent().equalsIgnoreCase(hotelId)) {
						objHotelFacility.setId(eElement.getAttribute("id"));
						objHotelFacility.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objHotelFacility.setFacilityId(eElement.getElementsByTagName("facilityId").item(0).getTextContent());
						
						objHotelFacilityList.add(objHotelFacility);
					}
				}
				
			}
			
			return objHotelFacilityList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public HotelFacility get(int id) {
		try {
			
			HotelFacility objHotelFacility = new HotelFacility();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFacility");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objHotelFacility.setId(eElement.getAttribute("id"));
						objHotelFacility.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objHotelFacility.setFacilityId(eElement.getElementsByTagName("facilityId").item(0).getTextContent());
					}
				}
				
			}
			
			return objHotelFacility;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(HotelFacility objHotelFacility) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelFacility");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objHotelFacility.getId()))){
						eElement.getElementsByTagName("hotelId").item(0).setTextContent(objHotelFacility.getHotelId());
						eElement.getElementsByTagName("facilityId").item(0).setTextContent((objHotelFacility.getFacilityId()));
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
			
			NodeList nList = doc.getElementsByTagName("hotelFacility");
		 
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
