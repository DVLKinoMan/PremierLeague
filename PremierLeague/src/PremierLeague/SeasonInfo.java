/*
 * SeasonInfo კლასი სეზონის შესახებ ინფორმაციას ბეჭდავს
 */
package PremierLeague;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DaViD
 */
public class SeasonInfo {
    
    Season season=new Season();
    
    //ბეჭდავს სეზონის ცხრილს და ქმნის ფაილს რომელშიც წერია სეზონის მატჩები
    SeasonInfo(Season season){
        this.season=season;
        this.printSeasonTable();//ბეჭდავს ცხრილს
        this.printSeasonMatches();//seasonMatches.txt-ში ბეჭდავს ყველა გამართული მატჩის შედეგს
    }
    //პრემიერ ლიგის სეზონის ცხრილის ბეჭდვა
    private void printSeasonTable(){
        String[] sortClubs=season.getClubs().clone();
        int i=0;
        System.out.println("\n\n"+season.getSeasonBeginning().get(Calendar.YEAR)+" წლის ინგლისის პრემიერ ლიგის სეზონის ცხრილი:");
        for(Map.Entry<String,Integer> entry:season.getSortedPoints().entrySet()){
            System.out.println(i+1+")"+entry.getKey()+" "+"ქულები:"+season.getPoints().get(entry.getKey())+"   მოგება:"+season.getWinningMatches()[season.ClubsindexOf(entry.getKey())]+
                    " წაგება:"+season.getLossingMatches()[season.ClubsindexOf(entry.getKey())]+" ფრე:"+season.getDrawMatches()[season.ClubsindexOf(entry.getKey())] );
            sortClubs[i]=entry.getKey();
            i++;
        }
       System.out.println("\n\n"+season.getSeasonBeginning().get(Calendar.YEAR)+" წლის სეზონის გენერირების დრო წამებში: "+season.getSeasonTime()/1000.0);
       System.out.println("\n\n\n"+season.getSeasonBeginning().get(Calendar.YEAR)+" წელს პრემიერ ლიგა მოიგო: "+sortClubs[0]);
       System.out.println(season.getSeasonBeginning().get(Calendar.YEAR)+" წელს პრემიერ ლიგა დატოვეს: "+sortClubs[season.getClubs().length-1]+","+sortClubs[season.getClubs().length-2]+","+sortClubs[season.getClubs().length-3]+","+sortClubs[season.getClubs().length-4]); 
    } 
    //ფაილში სეზონის ყველა მატჩის ბეჭდვა
    private void printSeasonMatches(){
        try{
            int year=season.getSeasonBeginning().get(Calendar.YEAR);
            StringBuilder txt=new StringBuilder();
            txt.append(season.getSeasonBeginning().get(Calendar.YEAR));
            txt.append("seasonMatches");
            try (FileWriter file = new FileWriter(txt+".txt")) {
                for(int i=0;i<season.getSeasonMatches().size();i+=2)
                    file.write(season.getSeasonMatches().get(i)+" "+season.getSeasonMatches().get(i+1)+"\n");
            }
        }
        catch(IOException exc){
            System.out.println(exc.getMessage());
        }       
    }
    //კლავიატურიდან კონკრეტული მატჩის შეყვანა და შედეგის გაგება
    String matchInfo(){
        this.printClubs();
        System.out.println("\n\n\nგუნდების სახელები შეიყვანეთ ზემოთ მოცემული სახელწოდებებიდან");
        System.out.println("შეიტანეთ მატჩის გუნდების სახელწოდებები( vs ნიშნით) მაგალითად:Man Utd vs Chelsea");
        Scanner scan=new Scanner(System.in);
        String Match=scan.nextLine();
        int index;
        index=season.getSeasonMatches().indexOf(Match);
        if(index>=0){
        System.out.println(season.getSeasonBeginning().get(Calendar.YEAR)+" წლის მატჩის შედეგი:");
        System.out.println(season.getSeasonMatches().get(index+1));
        }
        else{
            System.out.println("\n\n\nშეცდომითაა შეყვანილი გუნდების სახელები.გთხოვთ შეიტანოთ თავიდან:");
            this.matchInfo();
        }
        return Match;
    }
    //გადაცემულ მატჩზე შედეგის გაგება
    void matchInfo(String match){
        int index;
        index=season.getSeasonMatches().indexOf(match);
        System.out.println(season.getSeasonBeginning().get(Calendar.YEAR)+" წლის მატჩის შედეგი:");
        System.out.println(season.getSeasonMatches().get(index+1));
    }
    //ბეჭდავს ყველა კლუბს
    private void printClubs(){
        System.out.print("\n\n");
        for(int i=0;i<20;i++)
            System.out.print(season.getClubs()[i]+" ");
    }
    
}