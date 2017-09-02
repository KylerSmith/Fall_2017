package movie;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import oracle.adfmf.json;

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

public class MovieDescription {	
/**
* Class properties.
*/
	private String title;
	private String rating;
	private String released;
	private String runtime;
	private String plot;
	private String filename;
	private String[] genres = new String[20];
	private String[] actors = new String[20];

	private int actorCount = 0;
	private int genreCount = 0;
/**
* Constructors.
*/

	public MovieDescription(){};

	public MovieDescription(String title, String rating, String released,
				String runtime, String plot, String filename,
				String genre, String actor) {
		
		this.title = title;
		this.rating = rating;
		this.released = released;
		this.runtime = runtime;
		this.plot = plot;
		this.filename = filename;
		this.genres[0] = genre;
		this.actors[0] = actor;

		actorCount++;
		genreCount++;
	}

	// TODO: following constructors
	public MovieDescription(JSONObject jsonObj) {
		
	}
	
	public MovieDescription(String jsonObjAsString) {

	}


/**
* Getters.
*/
	
	public String getTitle()    { return title; }
	public String getRating()   { return rating; }
	public String getReleased() { return released; }
	public String getRuntime()  { return runtime; }
	public String getPlot()     { return plot; }
	public String getFilename() { return filename; }
	
	public String[] getGenre()    { return genres; }
	public String[] getActors()   { return actors; }

/**
* Setters.
*/

	public void setTitle(String title)       { this.title = title; }
	public void setRating(String rating) 	 { this.rating = rating; }
	public void setReleased(String released) { this.released = released; }
	public void setRuntime(String runtime) 	 { this.runtime = runtime; }
	public void setPlot(String plot) 	 { this.plot = plot; }
	public void setFilename(String filename) {this.filename = filename; }
	
	public void setGenre(String genre) { 
		this.genres[genreCount] = genre; 
		genreCount++;
	}

	public void setActors(String actors) { 
		this.actors[actorCount] = actors; 
		actorCount++;
	}


/**
* Methods
*/

	/**
	* Adds a genre to the MovieDescription object
	* @param genre: String name of the genre to add
	*/
	public void addGenre(String genre) {
		this.genres[genreCount] = genre;
		genreCount++;
	}

	/**
	* Adds an actor to the MovieDescription obj
	* @param actor: String of the name of actor to add
	*/
	public void addActor(String actor) {
		this.actors[actorCount] = actor;
		actorCount++;
	}

	/**
	* Returns a String version of the MovieDescription obj
	*/
	public String toString() {
		
		String allActors = "";
		String allGenres = "";
		
		for(String s : genres) {
			if(s == null)
				break;
			allGenres += s;	
			allGenres += " ";	
		}	
		for(String s : actors) {
			if(s == null)
				break;
			allActors += s;
			allActors += " ";
		}

		return  "\t" + getTitle() + "\n\t" +
		        getRating() + "\n\t" +
			getReleased() + "\n\t" +
			getRuntime() + "\n\t" +
			getPlot() + "\n\t" +
			getFilename() + "\n\t" +
			allActors + "\n\t" + allGenres;
	}


	/**
	* TODO: Implement method to turn MovieDescription obj to a JSONObject
	* @return JSONObject: JSONObject representation of MovieDescription obj
	*/
	public JSONObject toJson() {
		return null;
	}
}







