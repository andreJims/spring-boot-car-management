package com.jims.car.service.application.comment;


import com.jims.car.data.dto.CommentDTO;
import com.jims.car.constraint.factory.comment.CommentMapper;
import com.jims.car.data.entity.Comment;
import com.jims.car.service.dao.comment.CommentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentDAO commentDAO, CommentMapper commentMapper) {
        this.commentDAO = commentDAO;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> getAllCommentByCarId(Long carId) {
        return commentMapper.commentListToCommentDTOList(commentDAO.findByCar(carId));
    }

    @Override
    public CommentDTO create(CommentDTO commentDTO) {
        return commentMapper.commentToCommentDTO(
                commentDAO.save(commentMapper.commentDTOtoComment(commentDTO))
        );
    }
}
