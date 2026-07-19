package ma.lbledfirst.backend.service;

import ma.lbledfirst.backend.domain.Booking;
import ma.lbledfirst.backend.domain.BookingStatus;
import ma.lbledfirst.backend.domain.Payment;
import ma.lbledfirst.backend.domain.PaymentStatus;
import ma.lbledfirst.backend.repository.BookingRepository;
import ma.lbledfirst.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class PaymentService extends AbstractCrudService<Payment, Long> {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        super(paymentRepository);
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Payment save(Payment payment) {
        Booking booking = payment.getBooking();

        if (booking == null || booking.getId() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Booking is required");
        }

        Booking existingBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Booking not found"));

        // Amount is calculated server-side, never trusted from the client
        payment.setAmount(existingBooking.getTotalPrice());
        payment.setStatus(PaymentStatus.pending);

        return super.save(payment);
    }

    public Payment markSucceeded(Long paymentId) {
        Payment payment = findById(paymentId);
        payment.setStatus(PaymentStatus.succeeded);
        paymentRepository.save(payment);

        Booking booking = payment.getBooking();
        booking.setStatus(BookingStatus.confirmed);
        bookingRepository.save(booking);

        return payment;
    }
}