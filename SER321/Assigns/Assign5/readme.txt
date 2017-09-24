/**
 * This server will need to be run with the command
 * 	ant execute.java.server
 *
 * This client can be built with the command
 * 	ant build.client
 *
 * After being built, the client can be deployed with the command
 * 	./bin/movieClient
 * 
 * The server and client will be run from the local host, port 8080.
 * 
 **/

	/**
	 * Running linux? In the build file we have these lines. It's pretty important to
	 * have the libs there becuase this won't work if they aren't.
	 **/
            <property name="includepath" value="/usr/include/jsoncpp"/>
            <property name="client.lib.path" value="/usr/local/lib"/>
            <property name="client.lib.list" value="jsonrpccpp-client,
						    jsonrpccpp-common,
						    microhttpd,jsoncpp,
						    fltk,pthread,stdc++"/>

	/**
	 * How about Mac? Same applies as above.
	 **/
		<property name="includepath" value="/opt/local/include:/usr/local/include"/>
            	<property name="client.lib.path" value="/opt/local/lib"/>
            	<property name="client.lib.list" value="c++,fltk,stdc++,jsonrpccpp"/>

ISSUES:

	Not runnning for Mac systems.
	Good thing the requirements are for Debian ;)
