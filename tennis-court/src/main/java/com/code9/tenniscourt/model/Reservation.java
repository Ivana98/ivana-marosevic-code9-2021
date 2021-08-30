package com.code9.tenniscourt.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE reservation SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Reservation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Long playerId;

    @ManyToOne
    @JoinColumn(nullable=false)
    private TennisCourt tennisCourt;

    @Column
    private boolean paymentProcessed;

    @Column
    private boolean deleted = Boolean.FALSE;

}
