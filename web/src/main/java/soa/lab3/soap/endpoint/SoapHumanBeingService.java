package soa.lab3.soap.endpoint;

import soa.lab3.common.dto.HumanBeingDto;
import soa.lab3.common.dto.HumanBeingListDto;
import soa.lab3.common.entities.HumanBeing;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

@WebService(name="SoapHumanBeingService")
public interface SoapHumanBeingService {
    @WebMethod
    HumanBeingListDto listUser(String pageSizeReq, String pageNumberReq);

    @WebMethod
    HumanBeingDto insertUser(HumanBeingDto newHumanBeing);

    @WebMethod
    HumanBeingDto updateUser(HumanBeing newHumanBeing);

    @WebMethod
    HumanBeingDto deleteUser(int id);

    @WebMethod
    HumanBeingDto getUserById(int id);
}
