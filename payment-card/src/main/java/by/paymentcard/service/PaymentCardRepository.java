package by.paymentcard.service;

import by.paymentcard.domain.Currency;
import by.paymentcard.domain.PaymentCard;
import by.paymentcard.domain.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

    List<PaymentCard> findByClientIdNotNullAndTypeAndCurrency(
            Type type, Currency currency, Pageable var1);

}
