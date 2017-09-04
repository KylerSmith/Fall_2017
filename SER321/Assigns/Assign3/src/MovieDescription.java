package movie;

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
 * Purpose: A class that hold information on movies.
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieDescription.java
 * @date    <August, 2017>
 **/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.*;

public class MovieDescription {	
/**
* Class properties.
*/
	private String Title = "";
	private String Rated = "";
	private String Released = "";
	private String Runtime = "";
	private String Plot = "";
	private String Filename = "";

	private List<String> Genre = new ArrayList<>();;
        private List<String> Actors = new ArrayList<>();;

/**
* Constructors.
*/

	public MovieDescription(){};

	public MovieDescription(String title, String rating, String released,
				String runtime, String plot, String filename,
				String[] genre, String[] actors) {
		
		this.Title = title;
		this.Rated = rating;
		this.Released = released;
		this.Runtime = runtime;
		this.Plot = plot;
		this.Filename = filename;
		
		for(String s : genre)
			Genre.add(s);

		for(String s : actors)
			Actors.add(s);
	}


	public MovieDescription(String content) {
		JsonObject val = new Gson().fromJson(content, JsonObject.class);
	
		this.Title = val.get("Title").toString();
		this.Rated = val.get("Rated").toString();
		this.Released = val.get("Released").toString();
		this.Runtime = val.get("Runtime").toString();
		this.Plot = val.get("Plot").toString();
		
		if(val.has("Filename"))
			this.Filename = val.get("Filename").toString();
		
		
		String actorString = val.get("Actors").toString();
		String genreString = val.get("Genre").toString();

		String[] indieActors = actorString.split(",");
		String[] indieGenres = genreString.split(",");

		for(String s : indieActors) {
			s = s.replace(" ", "");
			s = s.replace("\"", "");
			Actors.add(s);
		}
		for(String s : indieGenres) {
			s = s.replace(" ", "");
			s = s.replace("\"", "");
			Genre.add(s);
		}
	}


/**
* Getters.
*/
	
	public String getTitle()    { return Title; }
	public String getRating()   { return Rated; }
	public String getReleased() { return Released; }
	public String getRuntime()  { return Runtime; }
	public String getPlot()     { return Plot; }
	public String getFilename() { return Filename; }
	
	public String[] getActors() { 		
		String[] a = new String[Actors.size()];
		for(int i = 0; i < Actors.size(); i++)
			a[i] = Actors.get(i);
		return a;		
	}
	
	public String[] getGenre() {
		String[] g = new String[Genre.size()];
		for(int i = 0; i < Genre.size(); i++)
			g[i] = Genre.get(i);
		return g;
	}
	
/**
* Setters.
*/

	public void setTitle(String title)       { this.Title = title; }
	public void setRating(String rating) 	 { this.Rated = rating; }
	public void setReleased(String released) { this.Released = released; }
	public void setRuntime(String runtime) 	 { this.Runtime = runtime; }
	public void setPlot(String plot) 	 { this.Plot = plot; }
	public void setFilename(String filename) { this.Filename = filename; }

	public void setActors(String[] a) {
		Actors.clear();
		for(String s : a)
			Actors.add(s);
	}
	public void setGenre(String[] g) {
		Genre.clear();
		for(String s : g)
			Genre.add(s);
	}
		

/**
* Methods
*/

	/**
	* Returns a String version of the MovieDescription obj
	*/
	public String toString() {
			
		String allActors = "";
		String allGenres = "";

		for(String s : Genre) {
			allGenres += s;
			allGenres += " ";
		}
		for(String s : Actors) {
			allActors += s;
			allActors += " ";
		}
		
		return  getTitle() + "\n\t" +
		        getRating() + "\n\t" +
			getReleased() + "\n\t" +
			getRuntime() + "\n\t" +
			getPlot() + "\n\t" +
			getFilename() +
			allActors + "\n\t" +
			allGenres;
	}


	/**
	* @return Gson: Gson representation of MovieDescription obj
	*/
	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}


}







