package coady.mytweetapp.model;

import android.content.Context;

import java.util.Date;
import java.util.Random;

import coady.mytweetapp.main.TweetApp;

public class Tweeting {

    public String _id;
    public String tweet;
    public String date;
    public String tweeter;
    //private Long id;
    public Marker marker = new Marker();

    public Tweeting(String tweet, String date) { //double lat, double lng
        //id = unsignedLong();
        this.tweet = tweet;
        this.date = date;
        tweeter = TweetApp.getCurrentUser();
        //this.marker.coords.latitude = lat;
        //this.marker.coords.longitude = lng;
    }
    public String getTweetId(){
        return _id;
    }


    /*public String getTweetReport () {
        String report = "Tweet: " + tweet + " Date: " + date;
        return report;
    }*/

//    /**
//     * Generate a long greater than zero
//     * @return Unsigned Long value greater than zero
//     */
//    private Long unsignedLong() {
//        long rndVal = 0;
//        do {
//            rndVal = new Random().nextLong();
//        } while (rndVal <= 0);
//        return rndVal;
//    }
}
