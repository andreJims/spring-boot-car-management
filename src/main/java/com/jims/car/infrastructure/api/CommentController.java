package com.jims.car.infrastructure.api;

import com.jims.car.data.dto.CommentDTO;
import com.jims.car.service.application.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    /**
     * 
     * @param commentDTO
     * @return
     */
    @ApiOperation(value = "add Comment", notes = "This WS is used to add comment information.")
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public CommentDTO addComment(
            @ApiParam(name = "CommentDTO", value = "{\"comment\":\"Lorem ipsum\",\"car_id\": 456}", required = true) @RequestBody CommentDTO commentDTO) {
        return commentService.create(commentDTO);
    }

    @ApiOperation(value = "get Comment", notes = "This WS is used to get comment information.")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public List<CommentDTO> getComment(@ApiParam(value = "123", required = true) @PathVariable("id") Long id) {
        return commentService.getAllCommentByCarId(id);
    }
}
