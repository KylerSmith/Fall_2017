package movie.client;

import java.net.URL;
import java.util.ArrayList;
import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import edu.asu.ser.jsonrpc.common.PositionalParams;
import edu.asu.ser.jsonrpc.common.JsonRequest;
import edu.asu.ser.jsonrpc.common.JsonRpcException;
import edu.asu.ser.jsonrpc.client.HttpClient;

import movie.server.MovieLibraryInterface;
import movie.server.MovieDescription;

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
public class MovieLibraryProxy extends HttpClient implements MovieLibraryInterface {

	private static String port = "8080";
	private static String host = "localhost";
	private static int id = 0;
	private static final boolean debug = true;
	private static String curlCommand = "curl --data";

	public MovieLibraryProxy(URL url) {
		super(url);
	}
	
	public boolean saveToFile(String filename) throws JsonRpcException {
		return false;
	}
	public boolean restoreFromFile() throws JsonRpcException {
		return false;
	}



	/**
	* Get the titles in a list.
	*/
	public String[] getTitles() throws JsonRpcException {
		
		String[] ret;
		String value = execute("getTitles", "[]");
		
		JsonObject gson = new Gson().fromJson(value, JsonObject.class);
		JsonArray arr = gson.getAsJsonArray("result");
		
		ret = new String[arr.size()];
		
		for(int i = 0; i < arr.size(); i++) 
			ret[i] = arr.get(i).getAsString();
		
		return ret;
	}


	/**
	* Gets the info for a movie if it's in the library.
	*/
	public MovieDescription get(String movieTitle) throws JsonRpcException {
		
		MovieDescription md;
		String value = execute("get", ("[\"" + movieTitle + "\"]"));
	
		String content = new Gson().fromJson(value, JsonObject.class).
					get("result").getAsString();
		
		
		md = new MovieDescription(content);

		debug(md.toString());	
		return md;
	}

	/**
	* Removes a title from the Library
	*/
	public boolean remove(String movieTitle) throws JsonRpcException {
		String value = execute("remove", ("[\"" + movieTitle + "\"]"));
		debug(value);
		return (new Gson().fromJson(value, JsonObject.class)).
			get("result").getAsBoolean();
	}
	public boolean add(MovieDescription clip) throws JsonRpcException {
		
		
		return false;
	} 
	public MovieDescription searchTitle(String movieTitle) throws JsonRpcException {
		return null;
	}
	public boolean print(String s) throws JsonRpcException {
		return false;
	}

// ------------ HELPER METHODS -------------- \\
	
	private void debug(String s) {
		if(debug) System.out.println("DEBUG MESSAGE: " + s);
	}

	private String addCmdMethod(String cmd) {
		String ret = curlCommand;

		ret = ret + " {\"jsonrpc\":\"2.0\"," +
				"\"method\":\"" + cmd + "\"," + 
				"\"params\":";

		return ret;
	}
	
	private String addCmdParams(String param, String toAdd) {
		String ret = toAdd;
		ret = ret + param + ",\"id\":3}";
		return ret;
	}

	private String build(String cmd, String param) {
		String ret = curlCommand;
		
		ret = addCmdMethod(cmd);
		debug(addCmdParams(param, ret) + " " + host + ":" + port);
		return (addCmdParams(param, ret) + " " + host + ":" + port);
	}

	private String execute(String method, String param) {
		String ret = "", s;
		try{
                        Process p = Runtime.getRuntime().
                                exec(build(method, param));

                        BufferedReader br = new BufferedReader(new
                                InputStreamReader(p.getInputStream()));
                        while ((s = br.readLine()) != null) {
                                ret += s;
                        }

                } catch (Exception e) {
                        System.out.println("You messed up.");
                }

     		return ret;          
	}
	
}



