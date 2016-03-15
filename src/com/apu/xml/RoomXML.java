package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.Room;
import com.apu.util.Config;

public class RoomXML {
	
	
	private String file;
	
	public RoomXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "room.xml";
	}
	
	public boolean add(Room objRoom){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element room = doc.createElement("room");
			rootElement.appendChild(room);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "room")));
			room.setAttributeNode(attr);
			
			Element hotelId = doc.createElement("hotelId");
			hotelId.appendChild(doc.createTextNode(objRoom.getHotelId()));
			room.appendChild(hotelId);
			
			Element roomNo = doc.createElement("roomNo");
			roomNo.appendChild(doc.createTextNode(objRoom.getRoomNo()));
			room.appendChild(roomNo);
			
			Element floor = doc.createElement("floor");
			floor.appendChild(doc.createTextNode(objRoom.getFloor()));
			room.appendChild(floor);
	 
			Element size = doc.createElement("size");
			size.appendChild(doc.createTextNode(String.valueOf(objRoom.getSize())));
			room.appendChild(size);
	 
			Element persons = doc.createElement("persons");
			persons.appendChild(doc.createTextNode(String.valueOf(objRoom.getPersons())));
			room.appendChild(persons);
			
			Element price = doc.createElement("price");
			price.appendChild(doc.createTextNode(String.valueOf(objRoom.getPrice())));
			room.appendChild(price);
	 
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(objRoom.getCategory()));
			room.appendChild(category);
	  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<Room> getAll() {
		try {
			
			ArrayList<Room> objRoomList = new ArrayList<Room>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("room");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Room objRoom = new Room();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objRoom.setId(eElement.getAttribute("id"));
					objRoom.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
					objRoom.setRoomNo(eElement.getElementsByTagName("roomNo").item(0).getTextContent());
					objRoom.setFloor(eElement.getElementsByTagName("floor").item(0).getTextContent());
					objRoom.setSize(Double.valueOf(eElement.getElementsByTagName("size").item(0).getTextContent()));
					objRoom.setPersons(Integer.parseInt(eElement.getElementsByTagName("persons").item(0).getTextContent()));
					objRoom.setPrice(Double.valueOf(eElement.getElementsByTagName("price").item(0).getTextContent()));
					objRoom.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
					
					objRoomList.add(objRoom);
				}
				
			}
			
			return objRoomList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Room> getByHotelId(int hotelId) {
		try {
			
			ArrayList<Room> objRoomList = new ArrayList<Room>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("room");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Room objRoom = new Room();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getElementsByTagName("hotelId").item(0).getTextContent().equalsIgnoreCase(String.valueOf(hotelId))) {
						objRoom.setId(eElement.getAttribute("id"));
						objRoom.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objRoom.setRoomNo(eElement.getElementsByTagName("roomNo").item(0).getTextContent());
						objRoom.setFloor(eElement.getElementsByTagName("floor").item(0).getTextContent());
						objRoom.setSize(Double.valueOf(eElement.getElementsByTagName("size").item(0).getTextContent()));
						objRoom.setPersons(Integer.parseInt(eElement.getElementsByTagName("persons").item(0).getTextContent()));
						objRoom.setPrice(Double.valueOf(eElement.getElementsByTagName("price").item(0).getTextContent()));
						objRoom.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
					
						objRoomList.add(objRoom);
					}
				}
				
			}
			
			return objRoomList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Room get(int id) {
		try {
			
			Room objRoom = new Room();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("room");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objRoom.setId(eElement.getAttribute("id"));
						objRoom.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objRoom.setRoomNo(eElement.getElementsByTagName("roomNo").item(0).getTextContent());
						objRoom.setFloor(eElement.getElementsByTagName("floor").item(0).getTextContent());
						objRoom.setSize(Double.valueOf(eElement.getElementsByTagName("size").item(0).getTextContent()));
						objRoom.setPersons(Integer.parseInt(eElement.getElementsByTagName("persons").item(0).getTextContent()));
						objRoom.setPrice(Double.valueOf(eElement.getElementsByTagName("price").item(0).getTextContent()));
						objRoom.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
					}
				}
				
			}
			
			return objRoom;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(Room objRoom) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("room");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objRoom.getId()))){
						eElement.getElementsByTagName("hotelId").item(0).setTextContent(objRoom.getHotelId());
						eElement.getElementsByTagName("roomNo").item(0).setTextContent(objRoom.getRoomNo());
						eElement.getElementsByTagName("floor").item(0).setTextContent(objRoom.getFloor());
						eElement.getElementsByTagName("size").item(0).setTextContent(String.valueOf(objRoom.getSize()));
						eElement.getElementsByTagName("persons").item(0).setTextContent(String.valueOf(objRoom.getPersons()));
						eElement.getElementsByTagName("price").item(0).setTextContent(String.valueOf(objRoom.getPrice()));
						eElement.getElementsByTagName("category").item(0).setTextContent(objRoom.getCategory());
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
			
			NodeList nList = doc.getElementsByTagName("room");
		 
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
