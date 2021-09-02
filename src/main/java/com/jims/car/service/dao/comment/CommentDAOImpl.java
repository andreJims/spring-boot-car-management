package com.jims.car.service.dao.comment;

import com.jims.car.constraint.errors.ErrorsEnum;
import com.jims.car.constraint.errors.ObjectNotFoundException;
import com.jims.car.data.entity.Car;
import com.jims.car.data.entity.Comment;
import com.jims.car.service.repository.CarRepository;
import com.jims.car.service.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDAOImpl implements CommentDAO {

    private CommentRepository commentRepository;

    private CarRepository carRepository;

    public CommentDAOImpl(CommentRepository commentRepository, CarRepository carRepository) {
        this.commentRepository = commentRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ObjectNotFoundException(ErrorsEnum.ERR_API_CAR_IS_NOT_FOUND));
        return commentRepository.findByCar(car);
    }
}
