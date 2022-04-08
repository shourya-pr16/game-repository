package com.skilli.game.assignment;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MessageResponse implements Serializable {
    private static final long serialVersionUID = 1l;
    private String message;
}
