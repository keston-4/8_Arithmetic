/* Etude 8 - Arithmetic 
 * Authors: Kurt Weston & James Douglas
 */


import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {

   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      String ret = "";
      String[] input = new String[3];
      
      while(scan.hasNextLine()){

         ret = scan.nextLine();
         if(ret.isEmpty() || ret.charAt(0) == '#'){
            continue;
         }
         System.out.println(ret);
         input = ret.split(" ");
         if(input.length != 3 || !checkOperation(input[1])
                  || !isNumber(input[0],input[2])){
            System.out.println("# Syntax error\n");
            continue;
         } 
         
         MegaNumber n1 = new MegaNumber(input[0]);
         MegaNumber n2 = new MegaNumber(input[2]);
         operate(n1,n2,input[1]);
      }

   }

   public static boolean isNumber(String n1, String n2){
      String s;
      int j = 0;
      
      if(n1.charAt(0) == '-'){
         j=1;
      }
      
      for(int i=j;i<n1.length();i++){
         try {
            s = ""+n1.charAt(i);
            Integer.parseInt(s);
         } catch (NumberFormatException e){
            return false;
         }
      }
      j=0;
      if(n2.charAt(0) == '-'){
         j=1;
      }
      
      for(int i=j;i<n2.length();i++){
         try {
            s = ""+n2.charAt(i);
            Integer.parseInt(s);
         } catch (NumberFormatException e){
            return false;
         }
      }
      return true;
   }

   public static MegaNumber operate(MegaNumber n1, 
      MegaNumber n2, String op){
      MegaNumber t;
      switch(op) {
         case "=" :
            System.out.println("# "+(n1.compareTo(n2) == 0) +"\n");
            break;
         case "<" :
            System.out.println("# "+(n1.compareTo(n2) == -1) +"\n");
            break;
         case ">" :
            System.out.println("# "+(n1.compareTo(n2) == 1) + "\n");
            break;
         case "+" :
            System.out.println("# "+n1.add(n2)+"\n");
            break;
         case "*" :
            System.out.println("# "+n1.multipliedBy(n2)+"\n");
            break;

         case "-" :
            System.out.println("# "+n1.minus(n2)+"\n");
            break;
         case "/" :
            t = n1.dividedBy(n2);
            System.out.println("# "+t+" "+t.getRemainder()+"\n");
            break;

         case "gcd" :
            System.out.println("# "+n1.gcd(n2)+"\n");
            break;

         default:
            System.out.println("Unsupported");
            break;
      }
      return null;
   }


   public static boolean checkOperation(String s){
      switch(s){
         case "+" :
         case "-" :
         case "*" :
         case "/" :
         case "gcd" :
         case "<" :
         case ">" :
         case "=" :
            return true;
         default :
            return false;
      }
   }
}
