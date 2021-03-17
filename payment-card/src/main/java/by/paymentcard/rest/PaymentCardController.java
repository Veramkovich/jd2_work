package by.paymentcard.rest;

import by.paymentcard.domain.Client;
import by.paymentcard.domain.PaymentCard;
import by.paymentcard.service.ClientRepository;
import by.paymentcard.service.PaymentCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PaymentCardController {

    private final PaymentCardRepository paymentCardRepository;
    private final ClientRepository clientRepository;

    public PaymentCardController(PaymentCardRepository paymentCardRepository,
                                 ClientRepository clientRepository) {
        this.paymentCardRepository = paymentCardRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/cards")
    public List<PaymentCard> allCard() {
        return paymentCardRepository.findAll();
    }

    @PostMapping("/cards")
    public ResponseEntity<PaymentCard> addCard(@Valid @RequestBody PaymentCard card) {
        PaymentCard addedCard;
        try {
            if (card.getClientId() != null
                    && !clientRepository.existsById(card.getClientId())) {
                return new ResponseEntity("Client is not found: id=" + card.getClientId(), HttpStatus.BAD_REQUEST);
            }
            addedCard = paymentCardRepository.save(card);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(addedCard);
    }

    @PutMapping("/cards/{id}")
    @Transactional
    public ResponseEntity<PaymentCard> updateCard(
            @PathVariable Long id,
            @Valid @RequestBody PaymentCardUpdateCmd updateCmd) {
        PaymentCard updatedPaymentCard;
        try {
            PaymentCard savedPaymentCard = paymentCardRepository
                    .findById(id)
                    .orElseThrow(() -> new Exception("Payment card is not found: id=" + id));
            Client savedClient = clientRepository
                    .findById(updateCmd.getClientId())
                    .orElseThrow(() -> new Exception("Client is not found: id=" + updateCmd.getClientId()));

            savedClient.setStatus(savedPaymentCard.getType().toString());
            savedPaymentCard.setClientId(savedClient.getId());

            clientRepository.save(savedClient);
            updatedPaymentCard = paymentCardRepository.save(savedPaymentCard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(updatedPaymentCard);
    }
}
