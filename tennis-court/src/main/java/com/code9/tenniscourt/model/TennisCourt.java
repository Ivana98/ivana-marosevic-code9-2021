package com.code9.tenniscourt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tennis_court")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tennis_court SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class TennisCourt {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy="tennisCourt", fetch=FetchType.LAZY)
    private Set<Reservation> reservations;

    @Column
    private boolean deleted = Boolean.FALSE;

}
