package jms.receiver;

import jms.converter.XMLConverter;
import jms.domain.Transaction;
import jms.repository.OrderTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class OrderTransactionReceiver {

    @Autowired
    private OrderTransactionRepository transactionRepository;


    @JmsListener(destination = "${queue.name}", containerFactory = "${factory.name}")
    public void receiveMessage(String objectAsString){

        XMLConverter<Transaction> transactionXMLConverter = new XMLConverter<>(Transaction.class);
        Transaction transaction = transactionXMLConverter.convertToObject(objectAsString);

        System.out.println("Received <" + transaction +">");
        transactionRepository.save(transaction);
    }

}
