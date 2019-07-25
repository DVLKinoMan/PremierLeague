/*
 * SeasonValueComparator კლასი იმპლემენტაციას უკეთებს Comparator<String>-ს
 * ქულების კლებით მიხედვით ალაგებს map-ს
 */
package PremierLeague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DaViD
 */
public class SeasonValueComparator implements Comparator<String>{
    Map<String,Integer> points=new HashMap<>();
    SeasonValueComparator(Map<String,Integer> l){
        this.points=l;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(String a,String b){
        if(points.get(a)>=points.get(b))
            return -1;
        else return 1;
    }
}