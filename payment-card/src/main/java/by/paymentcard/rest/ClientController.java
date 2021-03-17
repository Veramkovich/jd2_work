package by.paymentcard.rest;

import by.paymentcard.domain.Client;
import by.paymentcard.domain.Currency;
import by.paymentcard.domain.PaymentCard;
import by.paymentcard.domain.Type;
import by.paymentcard.service.ClientRepository;
import by.paymentcard.service.PaymentCardRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private final ClientRepository clientRepository;
    private final PaymentCardRepository paymentCardRepository;

    public ClientController(ClientRepository clientRepository,
                            PaymentCardRepository paymentCardRepository) {
        this.clientRepository = clientRepository;
        this.paymentCardRepository = paymentCardRepository;
    }

    @GetMapping("/clients")
    public List<Client> allClient() {
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        Client addedClient;
        try {
            addedClient = clientRepository.save(client);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(addedClient);
    }

    @GetMapping("/clients/phones")
    public List<String> getPhones(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam Type type,
            @RequestParam Currency currency
            ) {
        return paymentCardRepository
                .findByClientIdNotNullAndTypeAndCurrency(
                    type,
                    currency,
                    PageRequest.of(pageNum, pageSize))
                .stream()
                .map(PaymentCard::getClientId)
                .map(id -> clientRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(Client::getPhoneNumber)
                .collect(Collectors.toList());
    }
}
