package payroll;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;

import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);

//    @Autowired
//    TransactionRepository repository;

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner dmeo(TransactionRepository repository){
//        return (args -> {
//            String from_date = "1997-07-17";
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date fd = formatter.parse(from_date);
//            java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
//            repository.save(new TransactionModel(sqlDate,1,"Hello"));
//
//            logger.info("GetAllTransaction");
//            logger.info("-----------------");
//            for (TransactionModel transactionModel : repository.findAll()){
//                logger.info(toString());
//            }
//            logger.info("");
//        });
//    }

    @KafkaListener(topics = "engineOneTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception{
        logger.info(cr.toString());
    }

    @Bean
    public String saveRepo(TransactionRepository repository) throws ParseException {
        TransactionModel transaction = new TransactionModel();

        String from_date = "1997-07-17";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fd = formatter.parse(from_date);
        java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
        transaction.setTransactionTime(sqlDate);
        transaction.setProductId(2);
        transaction.setBodyMessage("Hello World");
        repository.save(transaction);
        return  "Saved";
    }
}
// passmysql: 123456