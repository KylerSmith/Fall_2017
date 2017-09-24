#include "MovieClientGui.cpp"
#include "moviemethods.h"
#include <jsonrpccpp/client/connectors/httpclient.h>
#include <json/json.h>

#include <FL/Fl.H>
#include <FL/Fl_Window.H>
#include <FL/Fl_Button.H>
#include <FL/Fl_Output.H>
#include <FL/Fl_Text_Display.H>
#include <FL/Fl_Input_Choice.H>
#include <FL/Fl_Multiline_Input.H>
#include <FL/Fl_Tree.H>

#include <stdio.h>
#include <iostream>
#include <thread>
#include <stdlib.h>
#include <string>
#include <sstream>
#include <array>

using namespace std;
using namespace jsonrpc;

/**
 * Copyright (c) 2017 Tim Lindquist,
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
 * Purpose: C++ FLTK client sample.
 * This class extends the Gui component class MovieClientGui.
 * The class provides control functionality that respond to button clicks and tree item
 * selections.
 * This software is meant to run on Debian Jessie Linux
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering,
 *                       IAFSE, ASU at the Polytechnic campus
 * @file    MovieClient.cpp
 * @date    January, 2017
 **/

HttpClient httpclient("http://localhost:8080");
MovieMethods mm(httpclient);

// string variable and run method for use with threading. A separate thread
// is created to run the vlc application, which plays the video file.
string cmd;
void run(){
   system(cmd.c_str());
}

class MovieClient : public MovieClientGui {

public:

   // Don't make the client stub for the json-rpc-cpp an instance variable of
   // this object. In FLTK, callbacks may be handled on different threads, and
   // because of the way libjson-rpc-cpp is implmented, you must recreate
   // the stub object in each callback method in which its used.
   string appAuthor;
   thread * playThread;
   string host;
   string port;

   /**
    * Constructor for the MovieClient class.
    * This constructor creates the callback for clicking the X (exit) in the window,
    * creates callback for all menu item selections, and creates a callback
    * for tree selection events. Event callbacks must be static methods, so the
    * object passed to the callback is the MovieClient object. The static callback
    * methods then call an instance method on the instance. For example,
    * a tree item selection calls the TreeCallbackS static method, passing it
    * the MovieClient instance (this) as an argument. The static TreeCallbackS uses
    * the instance to call the instance method which has access to the MovieClient,
    * and MovieClientGui instance variables (types Fl_Tree, FL_Input, and FL_Choice)
    * necessary to implement the control functionality.
    **/
   MovieClient(string name, string aHost, string aPort) : MovieClientGui(name.c_str()) {
      callback(ClickedX, (void*)this);
      playThread = NULL;
      menubar->callback(Menu_ClickedS, (void*)this);	
      appAuthor = name;
      host = aHost;
      port = aPort;
      buildTree();
      tree->callback(TreeCallbackS, (void*)this);
   }

   /**
    * ClickedX is one of the callbacks for GUI controls. As partly described above,
    * callbacks in FLTK need to be static functions. But, static functions
    * cannot directly access instance data. This program uses "userdata"
    * to get around that by passing the instance to the callback
    * function. The callback then accesses whatever GUI control object
    * that it needs for implementing its functionality.
    */
   static void ClickedX(Fl_Widget * w, void * userdata) {
      cout << "You clicked Exit" << endl;
      MovieClient *o = (MovieClient*)userdata;
      if(o->playThread != NULL && (o->playThread)->joinable()){
         (o->playThread)->join();
      }
      exit(1);
   }

   // Static tree callback method
   static void TreeCallbackS(Fl_Widget*w, void*data) {
      MovieClient *o = (MovieClient*)data;
      o->TreeCallback(); //call the instance callback method
   }

   /**
    * TreeCallback is a callback for tree selections, deselections, expand or
    * collapse.
    */
   void TreeCallback() {
      // Find item that was clicked
      Fl_Tree_Item *item = (Fl_Tree_Item*)tree->item_clicked();
      cout << "Tree callback. Current selection is: ";
      if ( item ) {
	cout<< item->label();
      } else {
         cout << "none";
      }
      cout << endl;
      string aStr("unknown");
      string aTitle(item->label());
      switch ( tree->callback_reason() ) {  // reason callback was invoked
        case FL_TREE_REASON_NONE: {aStr = "none"; break;}
        case FL_TREE_REASON_OPENED: {aStr = "opened";break;}
        case FL_TREE_REASON_CLOSED: {aStr = "closed"; break;}
        case FL_TREE_REASON_SELECTED: {
        	aStr = "selection";
           	titleInput->value(aTitle.c_str());
           	break;
        }
        case FL_TREE_REASON_DESELECTED: {aStr = "deselected"; break;}
      	default: {break;}
      	}
   	std::cout << "Callback reason: " << aStr.c_str() << endl;	

	string check = string("selection");

	if(aStr.compare(check) == 0) {
		Json::Reader r;
		Json::Value val = mm.get(aTitle);
		if(!r.parse(val.asString(), val))
			cout << "\nParsing error.\n";
		ratedInput->value(val["Rated"].asString().c_str());
		runtimeInput->value(val["Runtime"].asString().c_str());
		releasedInput->value(val["Released"].asString().c_str());
		plotMLIn->value(val["Plot"].asString().c_str());
		filenameInput->value(val["Filename"].asString().c_str());

		actorsChoice->clear();
		genreChoice->clear();

		Json::Value actors = val["Actors"], genre = val["Genre"];
		for(int i = 0; i < actors.size(); i++) 
			actorsChoice->add(actors[i].asString().c_str());
		for(int i = 0; i < genre.size(); i++)
			genreChoice->add(genre[i].asString().c_str());

		actorsChoice->value(0);
		genreChoice->value(0);
	}
   }

   // Static menu callback method
   static void Menu_ClickedS(Fl_Widget*w, void*data) {
      MovieClient *o = (MovieClient*)data;
      o->Menu_Clicked(); //call the instance callback method
   }

   // Menu selection instance method that has ccess to instance vars.
   void Menu_Clicked() {
      char picked[80];
      menubar->item_pathname(picked, sizeof(picked)-1);
      string selectPath(picked);
      cout << "Selected Menu Path: " << selectPath << endl;
      // get a stub to the library server
      // Handle menu selections calling methods on the stub where necessary.
      if(selectPath.compare("File/Save")==0){
         cout << "Menu item File/Save selected." << endl;
      }else if(selectPath.compare("File/Restore")==0){
         cout << "Menu item File/Restore selected." << endl;
      }else if(selectPath.compare("File/Exit")==0){
         cout << "Menu item File/Exit selected." << endl;
         if(playThread != NULL && playThread->joinable()){
            playThread->join();
         }
         exit(0);
      }else if(selectPath.compare("Movie/Remove")==0){
         cout << "Menu item Movie/Remove selected." << endl;
         cout << "Removing video with title: " << titleInput->value() << endl;
	mm.remove(string(titleInput->value()));

      	}else if(selectPath.compare("File/Tree Refresh")==0){
		cout << "Menu item File/Refresh Tree selected." << endl;
		buildTree();
	}
   }

   // local method to execute a system command (uname or pwd). Note, the
   // command is executed on the main thread.
   string exec(const char* cmd) {
      FILE* pipe = popen(cmd, "r");
      if (!pipe) return "ERROR";
      char buffer[128];
      string result = "";
      while(!feof(pipe)) {
         if(fgets(buffer, 128, pipe) != NULL)
            result += buffer;
      }
      pclose(pipe);
      return result;
   }

   // local method to build the tree in the GUI left panel.
   void buildTree(){

	Json::Value titles = mm.getTitles();
	Json::Reader reader;
	int size = titles.size(), i;

	std::vector<std::string> titleList;

	for(i = 0; i < size; ++i) {
		cout << "Adding: " << titles[i].asString() << endl;
		titleList.push_back(titles[i].asString());
	}
	
      tree->clear();
      cout << endl << "Adding tree nodes for movie titles: ";
      for(int i = 0; i < size; i++) {
         cout << " " << titleList[i] << ", ";
         string title = titleList[i];
         std::stringstream stream;
         stream << "Movie"
                << "/" << title;
         tree->add(stream.str().c_str());
      }
      cout << endl;
      tree->root_label(appAuthor.c_str());
      tree->redraw();
   }

};

// main method for this program.
int main(int argc, char * argv[]) {
	
	//string host = "http://127.0.0.1:8080";
	
	string nameStr = (argc>1)?argv[1]:"Movie Library";
	string host = (argc>2)?argv[2]:"127.0.0.1";
	string port = (argc>3)?argv[3]:"8080";
	MovieClient mc(nameStr,host,port);
	return (Fl::run());
}





