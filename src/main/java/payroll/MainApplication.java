package payroll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    private TransactionRepository repository;

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @KafkaListener(topics = "publishDataTopic")
    public void listen(String cr) throws Exception{
        logger.info(cr.toString());
 
        TransactionModel transaction = new Gson().fromJson(cr, TransactionModel.class);

        String pattern = ("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(transaction.transactionTime);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fd = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(fd.getTime());

        repository.save(new TransactionModel(sqlDate, transaction.productId, transaction.bodyMessage));
    }
}