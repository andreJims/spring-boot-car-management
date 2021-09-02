package com.jims.car.service.repository;

import com.jims.car.data.entity.Car;
import com.jims.car.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCar(Car car);
}
