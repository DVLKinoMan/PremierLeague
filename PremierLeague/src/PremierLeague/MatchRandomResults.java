/*
 * MatchRandomResults კლასი ბოლო 6 შეხვედრაზე დაყრდნობით
 * "შემთხვევით" შედეგს ითვლის შესაბამისი მატჩისთვის
 */
package PremierLeague;

import java.util.Random;

/**
 *
 * @author DaViD
 */
public class MatchRandomResults extends MatchLastResults{
    private int RandDraw;//შემთხვევითი რიცხვი ფრისათვის
    private int RandWinHomeTeam;//შემთხვევითი რიცხვი სახლში მოთამაშე გუნდის მოგებისათვის
    private int RandWinAwayTeam;//შემთხვევითი რიცხვი სტუმრად მოთამაშე გუნდის მოგებისათვის
    //მატჩისთვის შედეგის გენერირება
    int startRandom(String HomeTeam,String AwayTeam){
       super.startCountingMatchLastResults(HomeTeam,AwayTeam);//უკანასკნელი 6 შეხვედრის შედეგების დათვლა
       
       double PrOfDraw=(((double)super.getDraw())/((double)6))*100;//ფრის ალაბათობა
       double PrOfWinningHomeTeam=(((double)super.getWinningHomeTeam())/((double)6))*100;//სახლში მოთამაშე გუნდის მოგების ალბათობა
       double PrOfWinningAwayTeam=(((double)super.getWinningAwayTeam())/((double)6))*100;//სტუმრად მოთამაშე გუნდის მოგების ალბათობა
       
       Random rand=new Random();
       RandDraw=rand.nextInt((int)PrOfDraw+1);
       RandWinHomeTeam=rand.nextInt((int)PrOfWinningHomeTeam+1);
       RandWinAwayTeam=rand.nextInt((int)PrOfWinningAwayTeam+1);
       
       return randomResult();
   }
    //შემთხვევითი შედეგის გამოთვლა
    int randomResult(){
       if(Math.max(getRandDraw(), Math.max(getRandWinHomeTeam(), getRandWinAwayTeam()))==getRandWinAwayTeam()) return -3;//სახლში მოთამაშე გუნდმა წააგო
       if(Math.max(getRandDraw(), Math.max(getRandWinHomeTeam(), getRandWinAwayTeam()))==getRandWinHomeTeam()) return 3;//სახლში მოტამაშე გუნდმა მოიგო
       else return 1;//დაფიქსირდა ფრე
   }

    /**
     * @return 
     * @აბრუნებს RandDraw
     */
    public int getRandDraw() {
        return RandDraw;
    }

    /**
     * @return 
     * @აბრუნებს RandWinHomeTeam
     */
    public int getRandWinHomeTeam() {
        return RandWinHomeTeam;
    }

    /**
     * @return 
     * @აბრუნებს RandWinAwayTeam
     */
    public int getRandWinAwayTeam() {
        return RandWinAwayTeam;
    }
}