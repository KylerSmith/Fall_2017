package assign6.movie.client;

import javax.swing.tree.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.net.*;
import java.io.*;

import assign6.movie.server.MovieDescription;
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
 * Purpose: Client GUI
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieLibraryClientGUI.java
 * @date    <September, 2017>
 **/


public class MovieLibraryClientGUI extends MovieLibraryGUI {

	private String host = "localhost";
	private int port = 8080;

	MovieLibraryProxy mlp = 
		(MovieLibraryProxy)new MovieLibraryProxy(host, port);

	public MovieLibraryClientGUI() {
		super();
		createNodes();

		/** events */
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {						System.out.println("=== CLICKED ===");
				TreePath sel = tree.getPathForLocation
					(e.getX(), e.getY());
				String selection = sel.getLastPathComponent().toString();
				if(!(selection.equalsIgnoreCase("root") || selection == null))
					updateOnSelection(selection);
			}
		});
	}

	private void createNodes() {
		String[] movies = mlp.getTitles();
		if(movies != null)
			for(String s : movies)
				root.add(new DefaultMutableTreeNode(s));
	}


	private void updateOnSelection(String title) {
		MovieDescription md = mlp.get(title);
		//System.out.println(md.toString());
		guiTitle.setText(md.getTitle());
		guiRated.setText(md.getRating());
		guiPlot.setText(md.getPlot());
		guiReleased.setText(md.getReleased());
		guiRuntime.setText(md.getRuntime());
		guiFilename.setText(md.getFilename());

		aInfo.clear(); gInfo.clear();
		aInfo.addElement("Actors"); gInfo.addElement("Genre");

		for(String s : md.getActors())
			aInfo.addElement(s);

		for(String s : md.getGenre())
			gInfo.addElement(s);

	}

	public static void main(String[] args) {
                new MovieLibraryClientGUI();
        }
}
