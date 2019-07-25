/*
 * Season კლასი ახდენს პრემიერ ლიგის ერთი სეზონის გენერირებას
 * სულ პრემიერ ლიგის კლუბების რაოდენობა 20-ია და ყველა გუნდი თითოეულს ხვდება ორჯერ: ერთხელ სახლში,მეორეჯერ-სტუმრად
 */
package PremierLeague;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author DaViD
 */
public class Season {

    /**
     * @return the year
     */
    public static int getYear() {
        return year;
    }
    //პრემიერ ლიგაში მონაწილე კლუბები
    private final String[] Clubs={"Man Utd","Chelsea","Arsenal","Man City","Spurs",
          "Liverpool","Southampton","West Ham","Swansea","Newcastle",
          "Stoke","Everton","West Brom","Sunderland","Hull",
          "Aston Villa","Crystal Palace","Leicester","QPR","Burnley"}; 
    
    private final int[] WinningMatches=new int[20];//თითოეული კლუბის მოგებათა რაოდენობა //მაგალითად: Man Utd-ს მოგებათა რაოდენობა
    private final int[] LossingMatches=new int[20];//თითოეული კლუბის წაგებათა რაოდენობა //იქნება WinningMatches[0] ინდექსზე,
    private final int[] DrawMatches=new int[20];//თითოეული კლუბის ფრეების რაოდენობა    //ხოლო Chelsea-ს WinningMatches[1]-ზე
    
    private Match match;//მონაცემი ერთი მატჩის გენერირებისთვის
    
    private final Map<String,Integer> Points=new HashMap<>();//კლუბების მიერ დაგროვებული ქულები
    private final SeasonValueComparator comparator=new SeasonValueComparator(Points);
    private final TreeMap<String,Integer> sortedPoints=new TreeMap<>(comparator);//კლუბები ქულების კლებით მიხედვით დალაგებული
    
    private final List<String> SeasonMatches=new ArrayList<>();//სეზონში ყველა ჩატარებული მატჩის შედეგები
    
    private String HomeTeam;//სახლში და სტუმრად მოთამაშე გუნდები
    
    private String AwayTeam;//სახლში და სტუმრად მოთამაშე გუნდები
    
    private Long seasonTime;//სეზონის გენერირების დრო
    
    private static int year=2014;//სეზონის წელი
    private final Calendar seasonBeginning=Calendar.getInstance();//სეზონის დასაწყისის დრო
    
    //უპარამეტრო კონსტრუქტორი.ყოველი კლუბისთვის წაგებული და მოგებული თამაშების განულება
    //სეზონის დასაწყისის მითითება
    Season(){
        seasonTime=System.currentTimeMillis();
        year++;
        seasonBeginning.set(year-1, 7, 18);
        for(int i=0;i<20;i++){
            WinningMatches[i]=0;
            LossingMatches[i]=0;
            DrawMatches[i]=0;
        }
    } 
    //აგენერირებს სეზონს
    void startSeason(){
        for(int i=0;i<20;i++){//ყოველი კლუბი ხვდება ყველა დანარჩენს
            HomeTeam=getClubs()[i];
            for(int j=0;j<20;j++){
                if(j!=i){
                    AwayTeam=getClubs()[j];
                    match=new Match(getHomeTeam(), getAwayTeam());
                    getMatch().startMatch();
                    getSeasonMatches().add(getHomeTeam()+" vs "+getAwayTeam());
                    this.matchResult(getHomeTeam(), getAwayTeam());
                }                   
            }
        }
        this.countPoints();
        getSortedPoints().putAll(getPoints());
        seasonTime=System.currentTimeMillis()-getSeasonTime();
    }
    //ორ გუნდს შორის გამართული შეხვედრის შედეგების დათვლა
    private void matchResult(String team1,String team2){
        if(getMatch().getResult()==3){//მოიგო სახლში მოთამაშე გუნდმა
            getWinningMatches()[ClubsindexOf(team1)]++;
            getLossingMatches()[ClubsindexOf(team2)]++;
            getSeasonMatches().add(team2+"-მა მოუგო "+team1+"-ს");
        }
        if(getMatch().getResult()==-3){//მოიგო სტუმრად მოთამაშე გუნდმა
            getWinningMatches()[ClubsindexOf(team2)]++;
            getLossingMatches()[ClubsindexOf(team1)]++;
            getSeasonMatches().add(team1+"-მა წააგო "+team2+"-ის წინააღმდეგ");
        }  
        if(getMatch().getResult()==1){//დაფიქსირდა ფრე
            getDrawMatches()[ClubsindexOf(team1)]++;
            getDrawMatches()[ClubsindexOf(team2)]++;
            getSeasonMatches().add("დაფიქსირდა ფრე");
        }
    }
    //Clubs-ში კლუბის ინდექსს აბრუნებს
    int ClubsindexOf(String team){
        String club="";
        int i=0;
        while(club == null ? team != null : !club.equals(team)){
            club=getClubs()[i];
            i++;
        }
        return i-1;
    }
    //ქულების მთვლელი
    //მოგებაზე გუნდს ეწერება 3 ქულა, წაგებაზე-0,ფრეზე ორივე გუნდს ეწერება 1
    private void countPoints(){
        for(int i=0;i<20;i++)
            getPoints().put(getClubs()[i],getWinningMatches()[i]*3+getLossingMatches()[i]*0+getDrawMatches()[i]*1);
    }

    /**
     * აბრუნებს Clubs
     * @return 
     */
    public String[] getClubs() {
        return Clubs;
    }

    /**
     * @return 
     * @აბრუნებს WinningMatches
     */
    public int[] getWinningMatches() {
        return WinningMatches;
    }

    /**
     * @return 
     * @აბრუნებს LossingMatches
     */
    public int[] getLossingMatches() {
        return LossingMatches;
    }

    /**
     * @return 
     * @აბრუნებს DrawMatches
     */
    public int[] getDrawMatches() {
        return DrawMatches;
    }

    /**
     * @return 
     * @აბრუნებს match
     */
    public Match getMatch() {
        return match;
    }

    /**
     * @return 
     * @აბრუნებს Points
     */
    public Map<String,Integer> getPoints() {
        return Points;
    }

    /**
     * @return 
     * @აბრუნებს comparator
     */
    public SeasonValueComparator getComparator() {
        return comparator;
    }

    /**
     * @return 
     * @აბრუნებს sortedPoints
     */
    public TreeMap<String,Integer> getSortedPoints() {
        return sortedPoints;
    }

    /**
     * @return 
     * @აბრუნებს SeasonMatches
     */
    public List<String> getSeasonMatches() {
        return SeasonMatches;
    }

    /**
     * @return 
     * @აბრუნებს HomeTeam
     */
    public String getHomeTeam() {
        return HomeTeam;
    }

    /**
     * @return 
     * @აბრუნებს AwayTeam
     */
    public String getAwayTeam() {
        return AwayTeam;
    }

    /**
     * @return 
     * @აბრუნებს seasonTime
     */
    public Long getSeasonTime() {
        return seasonTime;
    }

    /**
     * @return 
     * @აბრუნებს seasonBeginning
     */
    public Calendar getSeasonBeginning() {
        return seasonBeginning;
    }
}