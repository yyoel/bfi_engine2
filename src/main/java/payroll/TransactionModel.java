package payroll;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import  javax.persistence.Entity;
import  javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import  javax.persistence.Id;
import  java.util.Date;

@Entity
public class TransactionModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Id @GeneratedValue int Id;
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

    TransactionModel(Date transactionTime, int productId, String bodyMessage ){
        this.transactionTime = transactionTime;
        this.productId = productId;
        this.bodyMessage = bodyMessage;
    }

    public TransactionModel(){
        super();
    }
}
