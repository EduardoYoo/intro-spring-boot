package example.cashcard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

//Tells Spring this class is a Component of type RestController
@RestController
//Companion to @RestController that indicates which address requests must have to access this Controller.
@RequestMapping("/cashcards")
public class CashCardController {

    private CashCardRepository cashCardRepository;

    public CashCardController(CashCardRepository cashCardRepository) {  //Injecting cashCardRepository
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")   //GET requests that match cashcards/{requestedID} will be handled by this method.
    //@PathVariable makes Spring Web aware of the requestedId supplied in the HTTP request, allowing us to use it in code
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {

        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
        if (cashCardOptional.isPresent()) {
            return ResponseEntity.ok(cashCardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
