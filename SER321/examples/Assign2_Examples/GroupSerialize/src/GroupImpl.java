package ser321.serialize;

import java.util.Vector;
import java.util.Enumeration;
import java.io.Serializable;

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
 * @file    GroupImpl.java
 * @date    August, 2015
 * @license See above
 */
public class GroupImpl implements Group, Serializable {

  private String name;
  private Vector<User> users = new Vector<User>();

  public GroupImpl(String name) {
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void addUserToGroup(String id, String pwd) {
    boolean found = false;
    for (int i = 0; i<users.size(); i++) {
      if ((users.elementAt(i)).getId().equals(id))
        found = true;
    }
    if (!found)
      users.addElement(new User(id,pwd));
  }

  public Vector<String> getUserNames() {
    Vector<String> ret = new Vector<String>();
    for (Enumeration e = users.elements() ; e.hasMoreElements() ;) {
      ret.addElement(((User)e.nextElement()).getId());
    }
    return ret;
  }

  public boolean isMember(String id, String pwd) {
    boolean match = false;
    for (Enumeration e = users.elements() ; e.hasMoreElements() ;) {
      if(((User)e.nextElement()).check(id,pwd)){
        match = true;
        break;
      }
    }
    return match;
  }
}
