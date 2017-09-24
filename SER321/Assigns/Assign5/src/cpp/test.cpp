#include <iostream>
#include "movierpcmethods.h"
#include <jsonrpccpp/client/connectors/httpclient.h>

using namespace std;
using namespace jsonrpc;

int main() {

	HttpClient httpclient("http://localhost:8080");
	MovieRPCMethods c(httpclient);
	try {
		cout << c.getTitles() << endl;
	} catch(JsonRpcException e) {
		cout << e.what() << endl;
	}
}
