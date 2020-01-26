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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String... args){
        try {
            String driver = "com.mysql.jdbc.Driver";
            String connection = "jdbc:mysql://35.197.151.36:3306/transaction";
            String user = "root";
            String password = "root";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connection, user, password);
            if (!con.isClosed()) {
              con.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        SpringApplication.run(MainApplication.class, args);
    }

    @KafkaListener(topics = "engineOneTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception{
        logger.info(cr.toString());
    }
}
// passmysql: 123456