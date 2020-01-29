package payroll;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Set this Class into Entity
@Entity
public class TransactionModel {
    //Auto Generate Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    //Date Format for Json
    @JsonFormat(pattern="dd/MM/yyyy")
    public Date transactionTime;
    public int productId;
    public String bodyMessage;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    //Constructor
    TransactionModel(Date transactionTime, int productId, String bodyMessage ){
        this.transactionTime = transactionTime;
        this.productId = productId;
        this.bodyMessage = bodyMessage;
    }

    public TransactionModel(){
        super();
    }
}
