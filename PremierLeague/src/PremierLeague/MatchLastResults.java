/*
 * კლასი MatchLastResults გუნდებში გამართული ბოლო 6 შეხვედრის შედეგებს იღებს StatsForLastSixMatches.txt ფაილიდან
 * და წერს შესაბამის მონაცემებში
 */
package PremierLeague;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DaViD
 */
public class MatchLastResults{
    private int Draw=0;//ორ გუნდს შორის გამართულ ბოლო 6 შეხვედრაში ფრეების რაოდენობა
    private int WinningHomeTeam=0;//ორ გუნდს შორის გამართულ ბოლო 6 შეხვედრაში სახლში მოთამაშე გუნდის მოგებების რაოდენობა
    private int WinningAwayTeam=0;//ორ გუნდს შორის გამართულ ბოლო 6 შეხვედრაში სტუმრად მოთამაშე გუნდის მოგებების რაოდენობა
    private final List<String> Stats=new ArrayList<>();//ბოლო 6 შეხვედრაში გამართული მატჩების სტატისტიკა
    //მატჩის ბოლო 6 შეხვედრის შედეგების დათვლა
    void startCountingMatchLastResults(String HomeTeam,String AwayTeam){
        this.readStatSixMatches();//ფაილიდან კითხვა მონაცემების
        int index=getStats().indexOf(HomeTeam+" vs "+AwayTeam);
        String result=getStats().get(index+1);//HomeTeam vs AwayTeam მატჩის ბოლო 6 შეხვედრის შედეგები
        for(int i=0;i<result.length();i++){//დათვლა შედეგების
            if(result.charAt(i)=='0') Draw++;
            if(result.charAt(i)=='1') WinningHomeTeam++;
            if(result.charAt(i)=='2') WinningAwayTeam++;
        }
    }
    //StatsForLastSixMatches.txt ფაილიდან მონაცემების კითხვა და List<String> Stats-ში ჩაწერა
    private void readStatSixMatches(){
        Scanner ImportMatches;
        ImportMatches = null;
       try{
          ImportMatches = new Scanner(new BufferedReader(new FileReader("StatsForLastSixMatches.txt")));
            while(ImportMatches.hasNextLine()){
                getStats().add(ImportMatches.nextLine());
            }
       }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }
         finally {
            if (ImportMatches != null) {
                ImportMatches.close();
            }    
        }
     }

    /**
     * @return 
     * @აბრუნებს Draw
     */
    public int getDraw() {
        return Draw;
    }

    /**
     * @return 
     * @აბრუნებს WinningHomeTeam
     */
    public int getWinningHomeTeam() {
        return WinningHomeTeam;
    }

    /**
     * @return 
     * @აბრუნებს WinningAwayTeam
     */
    public int getWinningAwayTeam() {
        return WinningAwayTeam;
    }

    /**
     * @return 
     * @აბრუნებს Stats
     */
    public List<String> getStats() {
        return Stats;
    }
}