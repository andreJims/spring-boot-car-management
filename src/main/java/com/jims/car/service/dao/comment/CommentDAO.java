package com.jims.car.service.dao.comment;

import com.jims.car.data.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment save(Comment comment);

    List<Comment> findByCar(Long carId);
}
