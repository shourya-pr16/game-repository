package com.skilli.game.assignment.games.infrastructure.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@Table
@Entity
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private Timestamp startTime;
    @Column
    private Timestamp endTime;
    @Column
    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "game_category_mapping", joinColumns = @JoinColumn(name = "game_category_id",unique = false)
            , inverseJoinColumns = @JoinColumn(name = "game_id",unique = false))
    private Set<GameCategory> gameCategory;
    @Column
    private String gamePlayUrl;
}
