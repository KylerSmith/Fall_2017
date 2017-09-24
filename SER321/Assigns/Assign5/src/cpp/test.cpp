#include <iostream>
#include "stubclient.h"
#include <jsonrpccpp/client/connectors/httpclient.h>

int main() {

	HttpClient httpclient("http://localhost:8080");
	StubClient c(httpclient);
	try {
		cout << c.sayHello("Peter") << endl;
		c.notifyServer();
	} catch(JsonRpcException e) {
		cout << e.what() << endl;
	}
}
