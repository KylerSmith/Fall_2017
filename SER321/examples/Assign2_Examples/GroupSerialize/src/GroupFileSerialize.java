package ser321.serialize;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Vector;
import java.util.Enumeration;

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
 * @file    GroupFileSerialize.java
 * @date    August, 2015
 * @license See above
 */
public class GroupFileSerialize {
  public static void main (String args[]) {

    try {
      Group admin = new GroupImpl("Administration");
      admin.addUserToGroup("Tim","any");
      admin.addUserToGroup("Joe","hisWord");
      admin.addUserToGroup("Sue","herWord");

      System.out.println("Server ready and waiting to export a group");

      File outFile = new File("admin.ser");
      ObjectOutputStream os = 
                         new ObjectOutputStream(new FileOutputStream(outFile));
      os.writeObject(admin);
      os.flush();
      os.close(); //no need in Java to close the File outFiel (not a closeable object).
      System.out.println("Server done exporting a group");

      File inFile = new File("admin.ser");
      ObjectInputStream in =
                            new ObjectInputStream(new FileInputStream(inFile));
      Group g = (GroupImpl)in.readObject();
      System.out.println("Group "+g.getName()+" received. Includes:");
      Vector<String> users = g.getUserNames();
      for (Enumeration e = users.elements(); e.hasMoreElements() ;) {
        System.out.println((String)e.nextElement());
      }
      in.close();
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}
