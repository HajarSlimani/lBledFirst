package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Payment;
import ma.lbledfirst.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractCrudService<Payment, Long> {
    public PaymentService(PaymentRepository repository) {
        super(repository);
    }
}
