/*
 * აგენერირებს პრემიერ ლიგის ორ სეზონს
 * ორივე სეზონის მატჩებს ჩაწერს ფაილებში
 * ეკრანზე გამოაქვს პრემიერ ლიგის ცხრილი ორივე სეზონისათვის
 * ეკრანზე გამოაქვს ვინ მოიგო პრემიერ ლიგა,ვინ გავარდა პრემიერ ლიგიდან,სეზონის გენერირების დრო
 * კლავიატურიდან შეტანილ რაიმე მატჩზე გამოიტანს შედეგს ორივე სეზონისათვის
 */
package PremierLeague;

/**
 *
 * @author DaViD
 */
public class PremierLeagueGenerator {
    public static void main(String[] args){
        String match;//კლავიატურიდან შეტანილი მატჩის დასამახსოვრებლად
        
        Season s1=new Season();
        Season s2=new Season();
        
        s1.startSeason();
        s2.startSeason();
        
        SeasonInfo info1=new SeasonInfo(s1);
        SeasonInfo info2=new SeasonInfo(s2);
        
        match=info1.matchInfo();
        info2.matchInfo(match);
    }
}