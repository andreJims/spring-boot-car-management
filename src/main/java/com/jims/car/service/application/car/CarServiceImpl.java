package com.jims.car.service.application.car;

import com.jims.car.constraint.factory.car.CarMapper;
import com.jims.car.data.dto.CarDTO;
import com.jims.car.constraint.errors.ErrorsEnum;
import com.jims.car.constraint.errors.ObjectNotFoundException;
import com.jims.car.data.entity.Car;
import com.jims.car.data.entity.Comment;
import com.jims.car.service.dao.car.CarDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;
    private CarMapper carMapper;

    public CarServiceImpl(CarDAO carDAO, CarMapper carMapper) {
        this.carDAO = carDAO;
        this.carMapper = carMapper;
    }

    @Override
    public CarDTO getCarById(Long id) {
        return carMapper.carToCarDTO(
                carDAO.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorsEnum.ERR_API_CAR_IS_NOT_FOUND))
        );
    }

    @Override
    public List<CarDTO> getAllCar() {
        return carMapper.carListToCarDTOList(carDAO.findAll());
    }
}
