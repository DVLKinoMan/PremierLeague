/*
 * Match ახდენს ერთი მატჩის გენერირებას
 * მას კონსტრუქტორში გადაეცემა მოთამაშე გუნდების სახელები 
 * პირველ ადგილზე წერია სახლში მოთამაშე გუნდი, მეორეზე სტუმრად მოთამაშე
 */
package PremierLeague;

/**
 *
 * @author DaViD
 */
public class Match {
    private final String HomeTeam;//სახლში მოთამაშე გუნდი
    private final String AwayTeam;//სტუმრად მოთამაშე გუნდი
    private int Result;//შედეგი. 1 -ფრე; 3 -სახლში მოთამაშე გუნდის მოგება; -3 -სტუმრად მოთამაშე გუნდის მოგება;
    Match(String H,String A){
        HomeTeam=H; AwayTeam=A;
    }
    //მატჩის შედეგის გენერირება
    void startMatch(){
        MatchRandomResults match=new MatchRandomResults();
        match.startRandom(getHomeTeam(), getAwayTeam());
        Result=match.randomResult();
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
     * @rაბრუნებს Result
     */
    public int getResult() {
        return Result;
    }
}