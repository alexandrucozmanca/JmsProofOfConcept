package jms;

import jms.generated.PlayerDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("ClubEnricher")
public class ClubEnricher implements Enrichable<PlayerDetails> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClubEnricher.class);

    @Override
    public void enrich(PlayerDetails playerDetails) {
        LOGGER.debug("Enriching player [{}] with club data", new Object[] { playerDetails.getSurname() });
        // Simulating accessing DB or some other service
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error("Exception while sleeping occured", e);
        }
        playerDetails.setTeamName("Borussia Dortmund");
    }
}
