package com.jims.car.data.dto;

import com.jims.car.data.dto.commun.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO extends BaseDTO {
    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private CarDTO carDTO;
    private Long carId;
}
