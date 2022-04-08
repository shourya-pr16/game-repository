package com.skilli.game.assignment.user.domain.service.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalDateTime;

@Data
@Builder
//@NoArgsConstructor
public class RegisteredGameModel implements Serializable {
    private static final long serialVersionUID = 1l;
    private String gameEventId;
    private String gameTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String gamePlayUrl;
    private URI gameImageUrl;
}
