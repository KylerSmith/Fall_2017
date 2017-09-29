package assign6.movie.client;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import assign6.movie.server.MovieLibrary;
import assign6.movie.server.MovieDescription;

/**
 * Copyright (c) 2017 Kyler Smith,
 * Software Engineering,
 * Arizona State University
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2
 * of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but without any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html
 * see also: https://www.gnu.org/licenses/gpl-faq.html
 * so you are aware of the terms and your rights with regard to this software.
 * Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: Proxy for the MovieLibrary class
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    movieLibraryProxy.java
 * @date    <September, 2017>
 **/

/**
* Needs to implement MovieLibrary | MovieDescription
*/
public class MovieLibraryProxy extends MovieLibrary {

	private static int port = 8080;
	private static String host = "localhost"; 	//"192.168.0.4";
	private static int id = 0;
	int buffSize = 1028;

	public MovieLibraryProxy(String h, int p) {
		this.host = h;
		this.port = p;
	}

	public String callMethod(String method, Object[] params){
		JsonObject callObj = new JsonObject();
      		String ret = "{}";
      		try{
			callObj.addProperty("method", method);
			callObj.addProperty("id", id);
			callObj.addProperty("jsonrpc", "2.0");
			JsonArray jsonParams = new JsonArray();

			if(params != null)
				for(int i = 0; i < params.length; i++)
					jsonParams.add(params[i].toString());

			callObj.add("params", jsonParams);
			
         		Socket sock = new Socket(host,port);
         		OutputStream os = sock.getOutputStream();
         		InputStream is = sock.getInputStream();
         		int numBytesReceived;
         		int bufLen = 1024;
         		String strToSend = callObj.toString();
         		byte bytesReceived[] = new byte[buffSize];
         		byte bytesToSend[] = strToSend.getBytes();
         		os.write(bytesToSend,0,bytesToSend.length);
         		numBytesReceived = is.read(bytesReceived,0,bufLen);
         		ret = new String(bytesReceived,0,numBytesReceived);
         		os.close();
         		is.close();
         		sock.close();
      		}catch(Exception ex){
         		ex.printStackTrace();
      		}
      		return ret;
	}

	
	public boolean saveToFile(String filename) {
		return false;
	}
	public boolean restoreFromFile() {
		return false;
	}

	/**
	* Get the titles in a list.
	*/
	public String[] getTitles() {
		String[] ret;
		String result = callMethod("getTitles", null);
		JsonParser parser = new JsonParser();
		JsonObject jObj = parser.parse(result).getAsJsonObject();
		JsonArray jArr = jObj.getAsJsonArray("result");
		ret = new String[jArr.size()];
		for(int i = 0; i < jArr.size(); i++)
			ret[i] = jArr.get(i).getAsString();
		return ret;
	}

	/**
	* Gets the info for a movie if it's in the library.
	*/
	public MovieDescription get(String movieTitle) {
		MovieDescription md = null;
		String result = callMethod("get", new Object[]{movieTitle});
		
		JsonParser parser = new JsonParser();
		JsonObject jObj = parser.parse(result).getAsJsonObject();

		try {
			md = new MovieDescription(jObj.get("result").toString());
		} catch(Exception e) {
			System.out.println("No movie found.");
		}
		return md;
	}

	/**
	* Removes a title from the Library
	*/
	public boolean remove(String movieTitle) {
		boolean ret;
		String result = callMethod("remove", new Object[]{movieTitle});
		JsonParser parser = new JsonParser();
		JsonObject jObj = parser.parse(result).getAsJsonObject();
		
		return jObj.get("result").getAsBoolean();
	}
	public boolean add(MovieDescription clip) {		
		return false;
	}

	public boolean add(String title) {
		return false;
	}

	public MovieDescription searchTitle(String movieTitle) {
		return null;
	}
	public boolean print(String s) {
		return false;
	}
}



