package jms;

import jms.generated.PlayerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;

@Component
public class ListenerImpl implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerImpl.class);

    @Autowired
    private Convertable<PlayerDetails> playerDetailsConverter;

    @Autowired
    private List<Enrichable<PlayerDetails>> listOfEnrichers;

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    @Qualifier("destinationPlayerSender")
    private Sender<PlayerDetails> sender;

    @Override
    public void onMessage(Message message) {

        if (!(message instanceof BytesMessage)) {
            LOGGER.error("Wrong msg!");
            return;
        }

        PlayerDetails playerDetails = null;
        try {
            playerDetails = (PlayerDetails) messageConverter.fromMessage(message);

            LOGGER.debug("Enriching the input message");
            for (Enrichable<PlayerDetails> enrichable : listOfEnrichers) {
                enrichable.enrich(playerDetails);
            }
            LOGGER.debug("Enriched text message: [{}]", new Object[] { playerDetailsConverter.marshal(playerDetails) });
            sender.sendMessage(playerDetails);
        } catch (Exception e) {
            LOGGER.error("Exception occured", e);
        }

    }
}
