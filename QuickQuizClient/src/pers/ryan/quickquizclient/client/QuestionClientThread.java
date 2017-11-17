package pers.ryan.quickquizclient.client;

import java.io.DataInputStream;
import java.io.IOException;
//Source:
//Creating a simple Chat Client/Server Solution 
//http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
import java.net.Socket;
import java.net.UnknownHostException;

import pers.ryan.quickquizclient.frame.ClientFrame;

/***
 * Client thread for listening to  server  
 * @author Ryan Tsang
 *
 */
public class QuestionClientThread extends Thread {

	private Socket socket = null;

	private ClientFrame client;

	public void setClient(ClientFrame client) {
		this.client = client;
	}

	private DataInputStream streamIn = null;

	public QuestionClientThread(Socket _socket) throws IOException {
		this.socket = _socket;
		open();
	}

	public void open() throws IOException {
		try {
			streamIn = new DataInputStream(socket.getInputStream());
		} catch (IOException ioe) {
			throw (new IOException("Error getting input stream: " + ioe + "/nPlease restart the program."));
		}
	}

	public void close() throws IOException {
		try {
			if (streamIn != null) {
				streamIn.close();
			}
		} catch (IOException ioe) {
			throw (new IOException("Error closing input stream: " + ioe));
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				//start listening to server
				client.handle(streamIn.readUTF());
			}
		} catch (IOException ioe) {
		} finally{
			try {
				close();
			} catch (IOException e) {
			}
		}
	}
}
