package com.code9.tennisplayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tennis_player")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tennis_player SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
//@FilterDef(name = "deletedPlayersFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
//@Filter(name = "deletedPlayersFilter", condition = "deleted = :isDeleted")
public class Player {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique=true)
    private String email;

    @Column
    private LocalDate birthdate;

    @Column
    private boolean deleted = Boolean.FALSE;

}
