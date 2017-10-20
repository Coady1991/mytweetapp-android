package coady.mytweetapp.model;


import java.util.Date;
import java.util.Random;

public class Tweeting {

    public String tweet;
    private Long date;
    private Long id;

    public Tweeting(String tweet){
        id = unsignedLong();
        this.tweet = tweet;
        date = new Date().getTime();
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
