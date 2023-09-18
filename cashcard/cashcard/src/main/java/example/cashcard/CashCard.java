package example.cashcard;

import org.springframework.data.annotation.Id;

public record CashCard(@Id Long id, Double amount) {    //"id" is configured as the "Id" for CashCardRepository.java
}