package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class TransactionController {
    @Autowired
    private TransactionRepository repository;

    @PostMapping(path = "/add")
    public @ResponseBody String addTransaction(@RequestParam String message){
        TransactionModel transaction = new TransactionModel();

        transaction.setBodyMessage(message);
        repository.save(transaction);
        return "Saved";
    }
}
