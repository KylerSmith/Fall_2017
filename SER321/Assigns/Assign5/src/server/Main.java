package movie.server;

import com.google.gson.Gson;
import java.util.*;
import java.lang.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.*;

import edu.asu.ser.jsonrpc.common.JsonRpcException;
import edu.asu.ser.jsonrpc.server.HttpServer;
import edu.asu.ser.jsonrpc.*;

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
 * Purpose: Driver for the server utilizing the MovieLibrary and MovieDescription classes.
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    test.java
 * @date    September, 2017
 **/

public class Main {

	public static void main(String[] args) throws IOException {
	      	String port = "8080";
      		if (args.length > 0) {
         		port = args[0];
      		}
      		HttpServer serv = new HttpServer(
         		new MovieLibrary("movies.json"), Integer.parseInt(port));
      		serv.start();
	}
}


