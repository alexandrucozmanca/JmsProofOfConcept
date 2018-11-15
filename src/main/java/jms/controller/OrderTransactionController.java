package jms.controller;

import jms.converter.XMLConverter;
import jms.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Destination;


@RestController
@RequestMapping("/transaction")
public class OrderTransactionController {

    @Autowired private JmsTemplate jmsTemplate;

    @Value("${queue.name}")
    private String queue;


    @PostMapping("/send")
    public void send(@RequestBody String objectAsString){
        jmsTemplate.convertAndSend(queue, objectAsString);
    }
}
