package com.jims.car.service.application.comment;

import com.jims.car.data.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getAllCommentByCarId(Long carId);

    CommentDTO create(CommentDTO commentDTO);
}
