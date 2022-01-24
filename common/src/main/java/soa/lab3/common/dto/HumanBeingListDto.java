package soa.lab3.common.dto;


import soa.lab3.common.entities.HumanBeing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HumanBeingListDto extends Response implements Serializable {
    private ArrayList<HumanBeing> humanBeingList;
    private int pageSize;
    private int pageNumber;
}
