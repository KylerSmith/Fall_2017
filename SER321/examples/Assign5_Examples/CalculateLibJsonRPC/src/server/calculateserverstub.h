/**
 * This file is generated by jsonrpcstub, DO NOT CHANGE IT MANUALLY!
 */

#ifndef JSONRPC_CPP_STUB_CALCULATESERVERSTUB_H_
#define JSONRPC_CPP_STUB_CALCULATESERVERSTUB_H_

#include <jsonrpccpp/server.h>

class calculateserverstub : public jsonrpc::AbstractServer<calculateserverstub>
{
    public:
        calculateserverstub(jsonrpc::AbstractServerConnector &conn, jsonrpc::serverVersion_t type = jsonrpc::JSONRPC_SERVER_V2) : jsonrpc::AbstractServer<calculateserverstub>(conn, type)
        {
            this->bindAndAddMethod(jsonrpc::Procedure("serviceInfo", jsonrpc::PARAMS_BY_POSITION, jsonrpc::JSON_STRING,  NULL), &calculateserverstub::serviceInfoI);
            this->bindAndAddMethod(jsonrpc::Procedure("plus", jsonrpc::PARAMS_BY_POSITION, jsonrpc::JSON_REAL, "param01",jsonrpc::JSON_REAL,"param02",jsonrpc::JSON_REAL, NULL), &calculateserverstub::plusI);
            this->bindAndAddMethod(jsonrpc::Procedure("minus", jsonrpc::PARAMS_BY_POSITION, jsonrpc::JSON_REAL, "param01",jsonrpc::JSON_REAL,"param02",jsonrpc::JSON_REAL, NULL), &calculateserverstub::minusI);
            this->bindAndAddMethod(jsonrpc::Procedure("times", jsonrpc::PARAMS_BY_POSITION, jsonrpc::JSON_REAL, "param01",jsonrpc::JSON_REAL,"param02",jsonrpc::JSON_REAL, NULL), &calculateserverstub::timesI);
            this->bindAndAddMethod(jsonrpc::Procedure("divide", jsonrpc::PARAMS_BY_POSITION, jsonrpc::JSON_REAL, "param01",jsonrpc::JSON_REAL,"param02",jsonrpc::JSON_REAL, NULL), &calculateserverstub::divideI);
        }

        inline virtual void serviceInfoI(const Json::Value &request, Json::Value &response)
        {
            (void)request;
            response = this->serviceInfo();
        }
        inline virtual void plusI(const Json::Value &request, Json::Value &response)
        {
            response = this->plus(request[0u].asDouble(), request[1u].asDouble());
        }
        inline virtual void minusI(const Json::Value &request, Json::Value &response)
        {
            response = this->minus(request[0u].asDouble(), request[1u].asDouble());
        }
        inline virtual void timesI(const Json::Value &request, Json::Value &response)
        {
            response = this->times(request[0u].asDouble(), request[1u].asDouble());
        }
        inline virtual void divideI(const Json::Value &request, Json::Value &response)
        {
            response = this->divide(request[0u].asDouble(), request[1u].asDouble());
        }
        virtual std::string serviceInfo() = 0;
        virtual double plus(double param01, double param02) = 0;
        virtual double minus(double param01, double param02) = 0;
        virtual double times(double param01, double param02) = 0;
        virtual double divide(double param01, double param02) = 0;
};

#endif //JSONRPC_CPP_STUB_CALCULATESERVERSTUB_H_
