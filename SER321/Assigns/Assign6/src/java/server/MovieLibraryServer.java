package assign6.movie.server;

import java.net.*;
import java.io.*;
import java.util.*;



public class MovieLibraryServer extends Thread {

   private Socket conn;
   private int id;
   private MovieLibrarySkeleton skeleton;

   public MovieLibraryServer (Socket sock, int id,
                              MovieLibrary ml) {
      this.conn = sock;
      this.id = id;
      skeleton = new MovieLibrarySkeleton(ml);
   }

   public void run() {
      try {
         OutputStream outSock = conn.getOutputStream();
         InputStream inSock = conn.getInputStream();
         byte clientInput[] = new byte[1024]; // up to 1024 bytes in a message.
         int numr = inSock.read(clientInput,0,1024);
         if (numr != -1) {
            //System.out.println("read "+numr+" bytes");
            String request = new String(clientInput,0,numr);
            System.out.println("request is: "+request);
            String response = skeleton.callMethod(request);
            byte clientOut[] = response.getBytes();
	    outSock.write(clientOut,0,clientOut.length);
            System.out.println("response is: "+response);
         }
         inSock.close();
         outSock.close();
         conn.close();
      } catch (IOException e) {
         System.out.println("I/O exception occurred for the connection:\n"+e.getMessage());
      }
   }
    
   public static void main (String args[]) {
      Socket sock;
      MovieLibrary ml = new MovieLibrary("movies.json");
      int id=0;

      try {
	int portNo = 8080;

         ServerSocket serv = new ServerSocket(portNo);
         // accept client requests. For each request create a new thread to handle
         while (true) { 
            System.out.println("Server waiting for connects on port "
                               +portNo);
            sock = serv.accept();
            System.out.println("Server connected to client: " + id);
            MovieLibraryServer myServerThread =
               new MovieLibraryServer(sock, id++, ml);
            myServerThread.start();
         }
      } catch(Exception e) {e.printStackTrace();}
   }
}

