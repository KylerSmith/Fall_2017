package assign6.movie.client;

import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;

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

public class MovieLibraryGUI extends JFrame {
	
	protected JTree tree;

	protected JTextField guiTitle;
	protected JTextField guiRated;
	protected JTextField guiPlot;
	protected JTextField guiReleased;
	protected JTextField guiRuntime;
	protected JTextField guiFilename;

	protected JList guiActors;
	protected JList guiGenre;
	protected DefaultListModel aInfo;
	protected DefaultListModel gInfo;


	JPanel container;
	DefaultMutableTreeNode root;
	JSplitPane split;

	public MovieLibraryGUI() {
		super("Movie Library Client");
		JPanel jp = new JPanel(new FlowLayout());
	
		/** Create the tree */
		root = new DefaultMutableTreeNode("Root");
                tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode
			(TreeSelectionModel.SINGLE_TREE_SELECTION);
	
		/**  */
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		createNodes(root);

		/** Create scroll pane and add tree */
		JScrollPane treeView = new JScrollPane(tree), infoView;

		/** Create and add the fields for viewing the movie info */
		container = new JPanel(new FlowLayout());
		
		guiTitle = new JTextField("Movie Title");
		guiTitle.setPreferredSize(new Dimension(400, 30));

		guiRated = new JTextField("Rated");
		guiRated.setPreferredSize(new Dimension(70, 30));

		guiReleased = new JTextField("Released");
		guiReleased.setPreferredSize(new Dimension(70, 30));
		
		guiFilename = new JTextField("Filename");
		guiFilename.setPreferredSize(new Dimension(70, 30));

		guiRuntime = new JTextField("Runtime");
		guiRuntime.setPreferredSize(new Dimension(70, 30));

		guiPlot = new JTextField("Plot");
		guiPlot.setPreferredSize(new Dimension(350, 150));		

		aInfo = new DefaultListModel();
		aInfo.addElement("Actors");
		guiActors = new JList(aInfo);
		guiActors.setPreferredSize(new Dimension(150, 150));	

		gInfo = new DefaultListModel();
		gInfo.addElement("Genre");
		guiGenre = new JList(gInfo);
		guiGenre.setPreferredSize(new Dimension(150, 150));

		container.add(guiTitle);
		container.add(guiRated);
		container.add(guiReleased);
		container.add(guiRuntime);
		container.add(guiFilename);
		container.add(guiPlot);
		container.add(guiActors);
		container.add(guiGenre);

		/** Add the scroll pane to the split panel */
		split.add(treeView);
		split.add(container);


		Dimension minSize = new Dimension(100, 100);
		treeView.setMinimumSize(minSize);
		split.setPreferredSize(new Dimension(550, 400));
		this.setPreferredSize(new Dimension(550, 400));
		getContentPane().add("Center", split);
		pack();
		setVisible(true);	
	}
	
	private void createTree() {
		DefaultMutableTreeNode root =
			new DefaultMutableTreeNode("Root");
		createNodes(root);
		tree = new JTree(root);
		JScrollPane treeView = new JScrollPane(tree);
	}	

	/** Method to override */
	private void createNodes(DefaultMutableTreeNode r) {}

	/** For testing while building the GUI */
	public static void main(String[] args) {
		new MovieLibraryGUI();
	}

}

