package soa.lab3.beans;

import soa.lab3.common.entities.HumanBeing;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface HumanBeingDao {
    HumanBeing findById(int id);

    void save(HumanBeing humanBeing);

    void update(HumanBeing humanBeing);

    void delete(HumanBeing humanBeing);

    ArrayList<HumanBeing> findAll();
}
