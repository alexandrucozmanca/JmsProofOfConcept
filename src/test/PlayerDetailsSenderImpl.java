package jms;

import jms.generated.PlayerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;

@Component
public class PlayerDetailsSenderImpl implements Sender<PlayerDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDetailsSenderImpl.class);

    private Destination destination;

    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public void sendMessage(final PlayerDetails playerDetails) throws JMSException{

        LOGGER.debug("Sending [{}] to topic [{}]", new Object[] { playerDetails, destination });
        jmsTemplate.convertAndSend(destination, playerDetails);

    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
