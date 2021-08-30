package com.code9.payment;

import com.code9.payment.enumeration.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE payment SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Payment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long reservationId;

    @Column
    private PaymentMethod paymentMethod;

    @Column
    private LocalDateTime payDate;

    @Column
    private boolean deleted = Boolean.FALSE;

}
