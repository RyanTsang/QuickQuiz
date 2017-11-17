package pers.ryan.quickquiz.server;
//Source:
//  Creating a simple Chat Client/Server Solution 
//  http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html


import java.net.*;
import java.io.*;

/***
 * This class can listen to client and send massage to client
 * @author Ryan Tsang
 *
 */
public class QuestionServerThread extends Thread
{

    private QuestionServer server = null;
    private Socket socket = null;
    private int ID = -1;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;

    public QuestionServerThread(QuestionServer _server, Socket _socket)
    {
        super();
        server = _server;
        socket = _socket;
        ID = socket.getPort();
    }

    /**
     * send message to client
     * @param msg
     * 		massage need to be sent
     */
    public void send(String msg)
    {
        try
        {
            streamOut.writeUTF(msg);
            streamOut.flush();
        }
        catch (IOException ioe)
        {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            server.remove(this);
            stop();
        }
    }

    public int getID()
    {
        return ID;
    }

    public void run()
    {
        System.out.println("Server Thread " + ID + " running.");
        while (true)
        {
            try
            {
            	//listen to client
                server.handle(this, streamIn.readUTF());
            }
            catch (IOException ioe)
            {
            	try {
					close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(this);
                stop();
            }
        }
    }

    public void open() throws IOException
    {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void close() throws IOException
    {
        if (socket != null)
        {
            socket.close();
        }
        if (streamIn != null)
        {
            streamIn.close();
        }
        if (streamOut != null)
        {
            streamOut.close();
        }
    }
}
