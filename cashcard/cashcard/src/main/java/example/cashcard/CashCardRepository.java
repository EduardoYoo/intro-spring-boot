package example.cashcard;

import org.springframework.data.repository.CrudRepository;

//Spring Boot and Spring Data will work together to generate the CRUD methods to interact with database using CrudRepository
public interface CashCardRepository extends CrudRepository<CashCard, Long> {    //Manage CashCard's data with CashCard ID as a Long

}