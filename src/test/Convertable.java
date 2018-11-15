package jms;

public interface Convertable<PlayerDetails> {
    String marshal(PlayerDetails playerDetails);

    PlayerDetails unmarshal(String playerAsString);

}
