package com.jims.car.infrastructure.api;

import com.jims.car.data.dto.CarDTO;
import com.jims.car.service.application.car.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @ApiOperation(value = "get car", notes = "This WS is used to get car information.")
    @GetMapping("/{id}")
    public CarDTO getCar(@ApiParam(value = "123", required = true) @PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @ApiOperation(value = "list car", notes = "This WS is used to list car information.")
    @GetMapping("")
    public List<CarDTO> listCar() {
        return carService.getAllCar();
    }
}
