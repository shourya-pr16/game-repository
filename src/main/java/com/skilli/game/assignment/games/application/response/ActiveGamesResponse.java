package com.skilli.game.assignment.games.application.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ActiveGamesResponse implements Serializable {
    private static final long serialVersionUID = 1l;
    private List<ActiveGame> games;
}
