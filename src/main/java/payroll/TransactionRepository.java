package payroll;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Extend method from CrudRepository
@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, Integer> {

}
