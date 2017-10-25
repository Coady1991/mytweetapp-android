package coady.mytweetapp.model;


import java.util.Date;
import java.util.Random;

public class Tweeting {

    public String tweet;
    public String date;
    private Long id;

    public Tweeting(String tweet, String date){
        id = unsignedLong();
        this.tweet = tweet;
        this.date = date;
    }

    /**
     * Generate a long greater than zero
     * @return Unsigned Long value greater than zero
     */
    private Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }
}
