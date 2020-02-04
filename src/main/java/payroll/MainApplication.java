package payroll;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);
    private TransactionRepository repository;

    public static void main(String... args) throws ParseException {
        SpringApplication.run(MainApplication.class, args);
    }

    @KafkaListener(topics = "publishDataTopic")
    public void listen(String cr) throws Exception{
        logger.info(cr.toString());

        TransactionModel transaction = new Gson().fromJson(cr, TransactionModel.class);
        logger.info("StartSave");
        saveRepo(transaction);
        logger.info("EndSave");
    }

    public String saveRepo(TransactionModel transactionModel) throws ParseException {
        TransactionModel transaction = new TransactionModel();

        String pattern = ("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(transaction.transactionTime);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fd = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(fd.getTime());

        transaction.setTransactionTime(sqlDate);
        transaction.setProductId(transactionModel.productId);
        transaction.setBodyMessage(transactionModel.bodyMessage);
        repository.save(transaction);
        return  "Saved";
    }
}