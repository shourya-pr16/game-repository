package com.skilli.game.assignment.games.infrastructure.repository.entity;

import com.skilli.game.assignment.user.repository.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class GameTransaction {
    private static final long serialVersionUID = 1L;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_GAME_REGISTRATION",
            joinColumns = @JoinColumn(name = "game_transaction_id",unique = false),
            inverseJoinColumns = @JoinColumn(name = "user_id",unique = false))
    Set<User> registeredUsers;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    @Column
    private int poolSize;
    @Column
    private int entryCoins;
    @Column
    private int winningAmount;
    @OneToOne
    @JoinColumn(name = "game_ref", referencedColumnName = "id")
    private Game game;
}
