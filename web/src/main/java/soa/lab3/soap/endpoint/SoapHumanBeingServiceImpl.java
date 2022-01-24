package soa.lab3.soap.endpoint;

import soa.lab3.beans.HumanBeingService;
import soa.lab3.common.dto.HumanBeingDto;
import soa.lab3.common.dto.HumanBeingListDto;
import soa.lab3.common.entities.HumanBeing;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "soa.lab3.soap.endpoint.SoapHumanBeingService")
public class SoapHumanBeingServiceImpl implements SoapHumanBeingService {
    @EJB
    private HumanBeingService humanBeingService;

    @Override
    public HumanBeingListDto listUser(String pageSizeReq, String pageNumberReq) {
        return humanBeingService.listUser(pageSizeReq, pageNumberReq);
    }

    @Override
    public HumanBeingDto insertUser(HumanBeingDto newHumanBeing) {
        return humanBeingService.insertUser(newHumanBeing.getHumanBeing());
    }

    @Override
    public HumanBeingDto updateUser(HumanBeing newHumanBeing) {
        return humanBeingService.updateUser(newHumanBeing);
    }

    @Override
    public HumanBeingDto deleteUser(int id) {
        return humanBeingService.deleteUser(id);
    }

    @Override
    public HumanBeingDto getUserById(int id) {
        return humanBeingService.getUserById(id);
    }
}
