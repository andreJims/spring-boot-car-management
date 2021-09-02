package com.jims.car.constraint.factory.comment;

import com.jims.car.data.dto.CommentDTO;
import com.jims.car.data.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Mappings({
            @Mapping(source = "comment.car.id", target = "carId"),
            @Mapping(source = "comment.car", target = "carDTO")
    })
    CommentDTO commentToCommentDTO(Comment comment);

    List<CommentDTO> commentListToCommentDTOList(List<Comment> comment);

    Comment commentDTOtoComment(CommentDTO commentDto);
}
