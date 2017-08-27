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
 * Purpose: Class that deals with a collection of MovieDescription class
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieLibrary.java
 * @date    <August, 2017>
 **/

public class MovieLibrary {

	private static int MAX_MOVIES = 100;

/**
* Properties.
*/
	private Object movies[] = new Object[MAX_MOVIES];
	private int movieCount = 0;

/**
* TODO: Implement the following methods.
*/

	/**
	* @param clip: MovieDescription information to add to collection
	* @return boolean: 1 if added successfully, 0 if not.
	*/
	public boolean add(MovieDescription clip) {
		
		movies[movieCount] = clip;
		movieCount++;	

		return true;
	}


       /**
        * @param title: The string of the title to remove
        * @return boolean: 1 if remove was successful, 0 if not / no title found.
        */
        public boolean remove(String title) {
               
                for(int i = 0; i < movieCount; i++) {
			MovieDescription m = (MovieDescription) movies[i];
                        if(title.equals(m.getTitle())) {
                                for(int j = i; j <= movieCount; j++) {
                                        if(j+1 == movieCount + 1) {
						movieCount--;
                                                return true;
					}
                                        
                                        movies[j] = movies[j+1];
                                }
				movieCount--;
                                return true;
                        }
                }
                return false;
        }

	/**
	* @param title: A String of the MovieDescription to return.
	* @return MovieDescription
	*/
	public MovieDescription get(String title) {

		for(int i = 0; i < movieCount; i++) {
			MovieDescription m = (MovieDescription) movies[i];
			if(title.equals(m.getTitle())) {
				return (MovieDescription) movies[i];
			}
		}
		return null;
	}

	/**
	* @return String[]: Array of Strings, which are all titles in the library. 
	*/
	public String[] getTitles() {
		String[] titles = new String[movieCount];
		
		for(int i = 0; i < movieCount; i++) {
			MovieDescription m = (MovieDescription) movies[i]; 
			titles[i] = m.getTitle();
		}
		
		return titles;
	}
}




