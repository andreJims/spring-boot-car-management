package com.jims.car.service.dao.car;

import com.jims.car.data.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    Car save(Car car);

    Optional<Car> findById(Long id);

    List<Car> findAll();
}
