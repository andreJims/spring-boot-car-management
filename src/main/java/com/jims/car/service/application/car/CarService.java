package com.jims.car.service.application.car;

import com.jims.car.data.dto.CarDTO;

import java.util.List;

public interface CarService {

    CarDTO getCarById(Long id);

    List<CarDTO> getAllCar();
}
