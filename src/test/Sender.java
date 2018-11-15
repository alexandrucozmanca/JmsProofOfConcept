package jms;

import javax.jms.JMSException;

public interface Sender<PlayerDetails> {

    void sendMessage(PlayerDetails playerDetails) throws JMSException;
}
