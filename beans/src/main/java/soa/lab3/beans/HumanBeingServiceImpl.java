package soa.lab3.beans;


import soa.lab3.common.dto.HumanBeingDto;
import soa.lab3.common.dto.HumanBeingListDto;
import soa.lab3.common.entities.Car;
import soa.lab3.common.entities.Coordinates;
import soa.lab3.common.entities.HumanBeing;
import soa.lab3.common.entities.WeaponType;



import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;


@Stateless
public class HumanBeingServiceImpl implements HumanBeingService {

    @EJB
    private HumanBeingDao humanBeingDao;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public HumanBeingServiceImpl() {
    }

    @Override
    public HumanBeing findHumanBeing(int id) {
        return humanBeingDao.findById(id);
    }

    @Override
    public String validateHumanBeing(HumanBeing humanBeing) {
        Set<ConstraintViolation<HumanBeing>> constraintViolations =
                validator.validate(humanBeing);
        StringBuilder message = new StringBuilder();

        if (!constraintViolations.isEmpty())
            for (ConstraintViolation s : constraintViolations)
                message.append(s.getMessage()).append(", ");


        Set<ConstraintViolation<Coordinates>> constraintViolationsCoordinates =
                validator.validate(humanBeing.getCoordinates());
        if (!constraintViolationsCoordinates.isEmpty())
            for (ConstraintViolation s : constraintViolationsCoordinates)
                message.append(s.getMessage()).append(", ");


        Set<ConstraintViolation<Car>> constraintViolationsCar =
                validator.validate(humanBeing.getCar());
        if (!constraintViolationsCar.isEmpty())
            for (ConstraintViolation s : constraintViolationsCar)
                message.append(s.getMessage()).append(", ");


        System.out.println(message);
        if (message.length() < 2)
            return "Done";

        return message.toString();
    }

    @SneakyThrows
    @Override
    public HumanBeing mapHumanBeing(HttpServletRequest request) {
        HumanBeing newHumanBaing = new HumanBeing();

        System.out.println(request.getParameterMap().toString());

        Car newCar = new Car();
        Coordinates newCoord = new Coordinates();

        newCoord.setX(Integer.parseInt(request.getParameter("x")));
        newCoord.setY(Integer.parseInt(request.getParameter("y")));
        newCar.setName(request.getParameter("car"));

        newHumanBaing.setCar(newCar);
        newHumanBaing.setCoordinates(newCoord);
        newHumanBaing.setName(request.getParameter("name"));
        newHumanBaing.setHasToothpick(Boolean.valueOf(request.getParameter("hasToothpick")));
        newHumanBaing.setImpactSpeed(Double.valueOf(request.getParameter("impactSpeed")));
        newHumanBaing.setMinutesOfWaiting(Double.parseDouble(request.getParameter("minutesOfWaiting")));
        newHumanBaing.setRealHero(Boolean.parseBoolean(request.getParameter("realHero")));
        newHumanBaing.setSoundtrackName(request.getParameter("soundtrackName"));
        newHumanBaing.setWeaponType(WeaponType.valueOf(request.getParameter("weaponType")));

        return newHumanBaing;
    }

    @SneakyThrows
    @Override
    public HumanBeingListDto listUser(String pageSizeReq, String pageNumberReq) {
        HumanBeingListDto humanBeingListDto = new HumanBeingListDto();
        try {
            ArrayList<HumanBeing> humanBeings = humanBeingDao.findAll();
            int pageSize = humanBeings.size();
            int pageNumber = 1;

            if (pageSizeReq != null)
                pageSize = Integer.parseInt(pageSizeReq);

            if (pageNumberReq != null)
                pageNumber = Integer.parseInt(pageNumberReq);

            humanBeings = getPage(humanBeings, pageNumber, pageSize);

            humanBeingListDto.setPageNumber(pageNumber);
            humanBeingListDto.setPageSize(pageSize);
            humanBeingListDto.setHumanBeingList(humanBeings);
            humanBeingListDto.setResponseCode(200);


        } catch (Exception exception){
            humanBeingListDto.setResponseCode(400);
            humanBeingListDto.setMessage(exception.getMessage());

        }
        return humanBeingListDto;
    }


    @SneakyThrows
    @Override
    public HumanBeingDto insertUser(HumanBeing newHumanBeing) {
        String message = validateHumanBeing(newHumanBeing);
        HumanBeingDto responseStructure = new HumanBeingDto();
        if(!message.equals("Done")) {
            responseStructure.setMessage(message);
            responseStructure.setResponseCode(400);
        } else {
            humanBeingDao.save(newHumanBeing);
            responseStructure.setHumanBeing(newHumanBeing);
            responseStructure.setMessage("Done");
            responseStructure.setResponseCode(200);
        }
        return responseStructure;
    }


    @SneakyThrows
    @Override
    public HumanBeingDto updateUser(HumanBeing newHumanBeing) {
        String message = validateHumanBeing(newHumanBeing);
        newHumanBeing.setCreationDate(findHumanBeing(newHumanBeing.getId()).getCreationDate());
        HumanBeingDto humanBeingDto = new HumanBeingDto();

        if (!message.equals("Done")) {
            humanBeingDto.setHumanBeing(newHumanBeing);
            humanBeingDto.setMessage(message);
            humanBeingDto.setResponseCode(200);
        } else {
            humanBeingDao.update(newHumanBeing);
            humanBeingDto.setResponseCode(400);
            humanBeingDto.setMessage("Done");
            humanBeingDto.setHumanBeing(newHumanBeing);
        }
        return humanBeingDto;
    }

    @SneakyThrows
    @Override
    public HumanBeingDto deleteUser(int id) {
        HumanBeingDto responseStructure = new HumanBeingDto();
        if(humanBeingDao.findById(id) != null) {
            responseStructure.setResponseCode(200);
            responseStructure.setMessage("Done");
            responseStructure.setHumanBeing(humanBeingDao.findById(id));
            humanBeingDao.delete(humanBeingDao.findById(id));
        } else{
            responseStructure.setResponseCode(400);
            responseStructure.setMessage("Can't find Human Being with id = " + id);
        }
        return responseStructure;
    }

    @SneakyThrows
    @Override
    public HumanBeingDto getUserById(int id) {
        HumanBeingDto responseStructure = new HumanBeingDto();
        if(humanBeingDao.findById(id) != null) {
            responseStructure.setResponseCode(200);
            responseStructure.setMessage("Done");
            responseStructure.setHumanBeing(humanBeingDao.findById(id));
        } else{
            responseStructure.setResponseCode(400);
            responseStructure.setMessage("Can't find Human Being with id = " + id);
        }
        return responseStructure;
    }

    @Override
    public <T> ArrayList<T> getPage(ArrayList<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return (ArrayList<T>) Collections.emptyList();
        }
        return new ArrayList<>(sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size())));
    }


}
