package com.code9.payment;

import com.code9.payment.enumeration.PaymentMethod;
import com.code9.payment.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public void delete(Long id) {
        findOneById(id);
        paymentRepository.deleteById(id);
    }

    public Payment findOneById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            throw new NotFoundException(String.format("Payment with id '%d' does not exist", id));
        }
        return paymentOptional.get();
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    public void processCashPayment(Payment payment) {
        // TODO call api/reservations/payment/{id} PUT endpoint
        payment.setPaymentMethod(PaymentMethod.CASH);
        payment.setPayDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }

    public void processCreditCardPayment(Payment payment) {
        // TODO call api/reservations/payment/{id} PUT endpoint
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setPayDate(LocalDateTime.now());
        paymentRepository.save(payment);
    }

}
