package com.jims.car.data.dto;

import com.jims.car.data.dto.commun.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO extends BaseDTO {
    private Long id;
    private String model;
    private String photo;
}
