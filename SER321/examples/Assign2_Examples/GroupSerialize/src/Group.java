package ser321.serialize;

import java.util.Vector;

/**
 * Copyright (c) 2015 Tim Lindquist,
 * Software Engineering,
 * Arizona State University at the Polytechnic campus
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
 * Purpose:
 * An interface defining operations for managing authentication groups.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @see <a href="http://pooh.poly.asu.edu/Ser321">Ser321 Home Page</a>
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @file    Group.java
 * @date    August, 2015
 * @license See above
 */
public interface Group {

  /**
   * Get the name string associated with this authorization group.
   * Use getName to retrieve the name string property for the group.
   * @return The group's name string.
   */
  public String getName();

  /**
   * Associate a new user with this authorization group.
   * Use addUserToGroup to allow a new user access to group resources.
   * @param user Is a String specifying userId to add
   * @param pwd Is a String specifying password for user.
   */
  public void addUserToGroup(String id, String pwd);

  /**
   * Get the name strings of all users associated in this authorization group.
   * Use getName to retrieve the name string property for the group.
   * @return The vector of userId strings.
   */
  public Vector<String> getUserNames();

  /**
   * Determine whether a user is in the group.
   * Use isMember to authenticate a user password combination for this group
   * membership.
   * @param id Is a String specifying userId that is not already in the group
   * @param pwd Is a String specifying a password for id.
   * @return true if user and pwd are authorized;
   * otherwise return false.
   */
  public boolean isMember(String id, String pwd);

}
