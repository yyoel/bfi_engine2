package payroll;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);
    
    //Inject TransactionRepository
    @Autowired
    private TransactionRepository repository;

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @KafkaListener(topics = "publishDataTopic")
    public void listen(String cr) throws Exception{
        //info Topic Value
        logger.info(cr.toString());
 
        //deserialize JSON to Object(TransactionModel)
        TransactionModel transaction = new Gson().fromJson(cr, TransactionModel.class);

        //Store Object to Datasabase (MySql)
        repository.save(transaction);
    }
}