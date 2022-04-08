package com.skilli.game.assignment.user.application.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayGameResponse {
    private String gameTitle;
    private String gameUrl;
}
