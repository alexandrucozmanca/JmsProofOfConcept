package jms;

public interface Enrichable<PlayerDetails> {
    void enrich(PlayerDetails playerDetails);
}
