package com.skilli.game.assignment.games.application.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActiveGame implements Serializable {
    private static final long serialVersionUID = 1l;

    private String gameEventId;
    private String gameTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int currentlyEnrolledPlayers;

    public ActiveGame() {
    }

    public String getGameEventId() {
        return this.gameEventId;
    }

    public String getGameTitle() {
        return this.gameTitle;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public int getCurrentlyEnrolledPlayers() {
        return this.currentlyEnrolledPlayers;
    }

    public void setGameEventId(String gameEventId) {
        this.gameEventId = gameEventId;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setCurrentlyEnrolledPlayers(int currentlyEnrolledPlayers) {
        this.currentlyEnrolledPlayers = currentlyEnrolledPlayers;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ActiveGame)) return false;
        final ActiveGame other = (ActiveGame) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$gameEventId = this.getGameEventId();
        final Object other$gameEventId = other.getGameEventId();
        if (this$gameEventId == null ? other$gameEventId != null : !this$gameEventId.equals(other$gameEventId))
            return false;
        final Object this$gameTitle = this.getGameTitle();
        final Object other$gameTitle = other.getGameTitle();
        if (this$gameTitle == null ? other$gameTitle != null : !this$gameTitle.equals(other$gameTitle)) return false;
        final Object this$startTime = this.getStartTime();
        final Object other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !this$startTime.equals(other$startTime)) return false;
        final Object this$endTime = this.getEndTime();
        final Object other$endTime = other.getEndTime();
        if (this$endTime == null ? other$endTime != null : !this$endTime.equals(other$endTime)) return false;
        if (this.getCurrentlyEnrolledPlayers() != other.getCurrentlyEnrolledPlayers()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ActiveGame;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $gameEventId = this.getGameEventId();
        result = result * PRIME + ($gameEventId == null ? 43 : $gameEventId.hashCode());
        final Object $gameTitle = this.getGameTitle();
        result = result * PRIME + ($gameTitle == null ? 43 : $gameTitle.hashCode());
        final Object $startTime = this.getStartTime();
        result = result * PRIME + ($startTime == null ? 43 : $startTime.hashCode());
        final Object $endTime = this.getEndTime();
        result = result * PRIME + ($endTime == null ? 43 : $endTime.hashCode());
        result = result * PRIME + this.getCurrentlyEnrolledPlayers();
        return result;
    }

    public String toString() {
        return "ActiveGame(gameEventId=" + this.getGameEventId() + ", gameTitle=" + this.getGameTitle() + ", startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ", currentlyEnrolledPlayers=" + this.getCurrentlyEnrolledPlayers() + ")";
    }
}
