package com.jims.car.constraint.factory.car;

import com.jims.car.data.dto.CarDTO;
import com.jims.car.data.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    CarDTO carToCarDTO(Car car);

    List<CarDTO> carListToCarDTOList(List<Car> car);

    Car carDTOtoCar(CarDTO carDto);
}
