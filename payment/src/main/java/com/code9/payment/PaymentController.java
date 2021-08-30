package com.code9.payment;

import com.code9.payment.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final CashMapper cashMapper;
    private final CreditCardMapper creditCardMapper;
    private final PaymentMapper paymentMapper;

    @PostMapping("/cash")
    @ResponseStatus(HttpStatus.CREATED)
    public void payByCash(@Valid @RequestBody final CashDto dto) {
        paymentService.processCashPayment(cashMapper.dtoToEntity(dto));
    }

    @PostMapping("/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public void payByCreditCard(@Valid @RequestBody final CreditCardDto dto) {
        paymentService.processCreditCardPayment(creditCardMapper.dtoToEntity(dto));
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") final Long id) {
        paymentService.delete(id);
    }

    @GetMapping("/{id}")
    public PaymentDto getOne(@PathVariable("id") Long id) {
        return paymentMapper.entityToDto(paymentService.findOneById(id));
    }

    @GetMapping
    public List<PaymentDto> getAll() {
        return paymentService.findAll()
                .stream()
                .map(paymentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-page")
    public Page<PaymentDto> getAll(Pageable pageable) {
        return paymentService.findAll(pageable)
                .map(paymentMapper::entityToDto);
    }

}
