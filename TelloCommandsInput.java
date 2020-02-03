/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellocommandsinput;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class TelloCommandsInput {

    /**
     * @param args the command line arguments
     */
    
    void MyFunc()
    {
        
    }
    public static void main(String[] args) throws UnknownHostException 
    {
        // TODO code application logic here
        String sentence = "";
        
    String responseSentence= "";
    Scanner scanner = new Scanner(System.in, "UTF-8");
    DatagramSocket clientSocket = null;
        try 
        {
            clientSocket = new DatagramSocket();
        }
        catch (SocketException ex) 
        {
            Logger.getLogger(TelloCommandsInput.class.getName()).log(Level.SEVERE, null, ex);
        }
	  
    InetAddress IPAddress = InetAddress.getByName("192.168.100.239");
	  
    System.out.println("Connessione al Tello effettuata.");
	  
    byte[] sendData = null;
    byte[] receiveData = new byte[256];
      	  
    while (!sentence.equals("bye"))
    {
        try{
       System.out.print("Send Commnad : ");       
       sentence = scanner.nextLine();
	 		
       sendData = sentence.getBytes();
       DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8889);
       clientSocket.send(sendPacket);
	   
       DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
       clientSocket.receive(receivePacket);
      
       responseSentence = new String(receivePacket.getData(), StandardCharsets.UTF_8);
	  	   
       System.out.println("FROM Tello:" + responseSentence);
        }
        catch(Exception E)
        {
            
        }
      }//end while
	  
    clientSocket.close();
    }
    
}
