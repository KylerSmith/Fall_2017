package movie.server;

import com.google.gson.JsonObject;
import edu.asu.ser.jsonrpc.common.JsonRpcException;
import java.io.IOException;
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
 * Purpose: MovieLibraryInterface defines the interface to the server operations
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieLibraryInterface.java
 * @date    <September, 2017>
 **/

public interface MovieLibraryInterface {
	public boolean saveToFile(String filename) throws JsonRpcException, IOException;
	public boolean restoreFromFile() throws JsonRpcException;
	public String[] getTitles() throws JsonRpcException;
	public MovieDescription get(String movieTitle) throws JsonRpcException;
	public boolean remove(String movieTitle) throws JsonRpcException;
	public boolean add(MovieDescription clip) throws JsonRpcException; 
	public boolean add(String jObjContent) throws JsonRpcException;
	public MovieDescription searchTitle(String movieTitle) throws JsonRpcException;
	public boolean print(String s) throws JsonRpcException;
}








