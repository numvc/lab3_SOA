package soa.lab3.beans;

import lombok.SneakyThrows;
import soa.lab3.common.dto.HumanBeingDto;
import soa.lab3.common.dto.HumanBeingListDto;
import soa.lab3.common.entities.HumanBeing;

import javax.ejb.Remote;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Remote
public interface HumanBeingService {
    HumanBeing findHumanBeing(int id);

    String validateHumanBeing(HumanBeing humanBeing);

    @SneakyThrows
    HumanBeing mapHumanBeing(HttpServletRequest request);

    @SneakyThrows
    HumanBeingListDto listUser(String pageSizeReq, String pageNumberReq);

    @SneakyThrows
    HumanBeingDto insertUser(HumanBeing newHumanBeing);

    @SneakyThrows
    HumanBeingDto updateUser(HumanBeing newHumanBeing);

    @SneakyThrows
    HumanBeingDto deleteUser(int id);

    @SneakyThrows
    HumanBeingDto getUserById(int id);

    <T> ArrayList<T> getPage(ArrayList<T> sourceList, int page, int pageSize);
}
