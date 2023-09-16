package example.cashcard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Tells Spring this class is a Component of type RestController
@RestController
//Companion to @RestController that indicates which address requests must have to access this Controller.
@RequestMapping("/cashcards")
public class CashCardController {

    //GET requests that match cashcards/{requestedID} will be handled by this method.
    @GetMapping("/{requestedId}")
    //@PathVariable makes Spring Web aware of the requestedId supplied in the HTTP request, allowing us to use it in code
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        if (requestedId.equals(99L)) {
            CashCard cashCard = new CashCard(99L, 123.45);
            return ResponseEntity.ok(cashCard);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
