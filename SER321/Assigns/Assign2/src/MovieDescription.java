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
	private String[] genre;
	private String[] actors;

/**
* Constructors.
*/

	// TODO: Create constructors.
	public MovieDescription(){};

	public MovieDescription(String title, String rating, String released,
				String runtime, String plot, String filename,
				String genre[], String actors[]) {
		
		this.title = title;
		this.rating = rating;
		this.released = released;
		this.runtime = runtime;
		this.plot = plot;
		this.filename = filename;
		this.genre = genre;
		this.actors = actors;
		
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
	public String[] getGenre() { return genre; }
	public String[] getActors() { return actors; }

/**
* Setters.
*/

	public void setTitle(String title)       { this.title = title; }
	public void setRating(String rating) 	 { this.rating = rating; }
	public void setReleased(String released) { this.released = released; }
	public void setRuntime(String runtime) 	 { this.runtime = runtime; }
	public void setPlot(String plot) 	 { this.plot = plot; }
	public void setFilename(String filename) {this.filename = filename; }
	public void setGenre(String[] genre) 	 { this.genre = genre; }
	public void setActors(String[] actors) 	 { this.actors = actors; }

/**
* Main entry point for the program.
*/

	public static void main(String[] args) {
		MovieDescription md = new MovieDescription();
	}


}







