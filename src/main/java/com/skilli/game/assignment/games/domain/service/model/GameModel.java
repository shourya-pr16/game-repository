package com.skilli.game.assignment.games.domain.service.model;

import java.time.LocalDateTime;

public class GameModel {
    private String gameEventId;
    private String gameTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int currentlyEnrolledPlayers;

    public GameModel() {
    }

    public GameModel(String gameEventId, String gameTitle, LocalDateTime startTime, LocalDateTime endTime, int currentlyEnrolledPlayers) {
        this.gameEventId = gameEventId;
        this.gameTitle = gameTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentlyEnrolledPlayers = currentlyEnrolledPlayers;
    }

    public static GameModelBuilder builder() {
        return new GameModelBuilder();
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
        if (!(o instanceof GameModel)) return false;
        final GameModel other = (GameModel) o;
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
        return other instanceof GameModel;
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
        return "GameModel(gameEventId=" + this.getGameEventId() + ", gameTitle=" + this.getGameTitle() + ", startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ", currentlyEnrolledPlayers=" + this.getCurrentlyEnrolledPlayers() + ")";
    }

    public static class GameModelBuilder {
        private String gameEventId;
        private String gameTitle;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private int currentlyEnrolledPlayers;

        GameModelBuilder() {
        }

        public GameModelBuilder gameEventId(String gameEventId) {
            this.gameEventId = gameEventId;
            return this;
        }

        public GameModelBuilder gameTitle(String gameTitle) {
            this.gameTitle = gameTitle;
            return this;
        }

        public GameModelBuilder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public GameModelBuilder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public GameModelBuilder currentlyEnrolledPlayers(int currentlyEnrolledPlayers) {
            this.currentlyEnrolledPlayers = currentlyEnrolledPlayers;
            return this;
        }

        public GameModel build() {
            return new GameModel(gameEventId, gameTitle, startTime, endTime, currentlyEnrolledPlayers);
        }

        public String toString() {
            return "GameModel.GameModelBuilder(gameEventId=" + this.gameEventId + ", gameTitle=" + this.gameTitle + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", currentlyEnrolledPlayers=" + this.currentlyEnrolledPlayers + ")";
        }
    }
}
