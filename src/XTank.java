import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
/*
 * Author : Prasiddhi Gyawali, Ishika Patel
 * Date   : 11/12/22
 * Class  : CSC 335
 * File   : XTank.java
 * 
 * Purpose : This class creates a new socket and  UI for each player that connects to the server. A new thread is created
 *           for each player that joins. 
 */
public class XTank 
{
	public static void main(String[] args) throws Exception 
    {
        try (var socket = new Socket("127.0.0.1", 12345)) 
        {
        	DataInputStream in = new DataInputStream(socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        	var ui = new XTankUI(in, out);
            ui.start();
        }
    }
}