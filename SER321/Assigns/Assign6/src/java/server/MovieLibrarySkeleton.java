package assign6.movie.server;

import java.net.*;
import java.io.*;
import java.util.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

public class MovieLibrarySkeleton extends Object {

   	MovieLibrary ml;

   	public MovieLibrarySkeleton(MovieLibrary movieLibrary){
      		this.ml = movieLibrary;
   	}

   	public String callMethod(String request){
		JsonObject jsonRet = new JsonObject();
      		try {
			JsonParser parser = new JsonParser();
			JsonObject called = parser.parse(
				request).getAsJsonObject();
			String method = called.get("method").getAsString();
			String id = called.get("id").getAsString();
			JsonArray params = null;


			if(called.getAsJsonArray("params").size() > 0) {
				/** Deal with parameters passed ie) movie title */
				params = called.getAsJsonArray("params");
				for(int i = 0; i < params.size(); i++){
					System.out.println("PARAM_"+i+" "+params.get(i).getAsString());
				}
			}
			jsonRet.addProperty("id", id);
			jsonRet.addProperty("jsonrpc", "2.0");

			/** Handle method request */
			if(method.equalsIgnoreCase("gettitles")){
				String[] titles = ml.getTitles();
				JsonArray jArr = new JsonArray();
				for(String s : titles)
					jArr.add(s);
				jsonRet.add("result", jArr);

			} else if(method.equalsIgnoreCase("remove")) {
				String movieTitle = params.get(0).getAsString();
				boolean wasRemoved = ml.remove(movieTitle);
				jsonRet.addProperty("result", wasRemoved);

			} else if(method.equalsIgnoreCase("add")) {


			} else if(method.equalsIgnoreCase("get")){
				String movieTitle = params.get(0).getAsString();
				MovieDescription ret = ml.get(movieTitle);

				if(ret != null)
					jsonRet.add("result", ret.toJson());
				else
					jsonRet.add("result", new JsonObject());

			} else {
				jsonRet.addProperty("result", 0.0);
			}

			return jsonRet.toString();
      		} catch(Exception e){
			e.printStackTrace();
      		}
		return null;
	}
}



