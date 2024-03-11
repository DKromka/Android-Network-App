import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
	public static void main(String[] args) throws IOException{
		ServerSocket server = null;
		
		try {
			//Create a ServerSock on localhost:4591
	        server = new ServerSocket(4591);
	        server.setReuseAddress(true);
	        
			while(true) {
		        System.out.println("ServerSocket awaiting connections...");

		        // .accept blocks until an inbound connection on this port is attempted
		        Socket client = server.accept();
		        
		        System.out.println("Connection from " + client.getInetAddress().getHostAddress());
		        
		        ClientHandler clientSock = new ClientHandler(client);
		        new Thread(clientSock).start();
			}
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//ClientHandler class
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private Message msg;
		
		private OutputStream outputStream;
		private InputStream inputStream;
		private ObjectOutputStream out;
		private ObjectInputStream in;
		
		//Constructor
		public ClientHandler(Socket socket)  throws IOException, ClassNotFoundException
		{
			this.clientSocket = socket;
			
	        //get the input stream from the connected socket
	        outputStream = clientSocket.getOutputStream();
	        inputStream = clientSocket.getInputStream();

	        //create a ObjectInputStream so we can read data from it.
	        out = new ObjectOutputStream(outputStream);
	        in = new ObjectInputStream(inputStream);
		}

		public void run()
		{
			try { 
				msg = (Message)in.readObject();
				
				System.out.println(msg.getType() + " | " + msg.getData() + " | " + msg.getId());
				
		        clientSocket.close();				
			}
			catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
