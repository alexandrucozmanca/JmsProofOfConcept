package jms.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "transaction")
@Entity
public class Transaction implements Serializable {

    @Id

    private long id;

    private String sender;

    private String receiver;

    private int amount;

    public Transaction() {
    }

    public Transaction(int id, String sender, String receiver, int amount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    @XmlElement(name = "sender")
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    @XmlElement(name = "receiver")
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return String.format("From: %s, to: %s, ammount: %d",getSender(), getReceiver(), getAmount());
    }
}
