import java.util.Scanner;
import java.util.ArrayList;



public class Main {

   

   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      String ret = "";
      String[] input = new String[3];
      ArrayList<String[]> scenarios = new ArrayList<String[]>();
      
      while(scan.hasNextLine()){
         ret = scan.nextLine();
         input = ret.split(" ");
         /*if(input.length != 3){
            System.out.println("Syntax error");
            continue;
         }*/

         System.out.println(input[0] + ":" + input[1] + ":" + input[2]);
         
         if(!checkOperation(input[1])) {
            System.out.println("Syntax error");
            continue;
         }

         scenarios.add(input);
      }

      //note singular and plural
      for(String[] scenario : scenarios){
         MegaNumber n1 = new MegaNumber(scenario[0]);
         MegaNumber n2 = new MegaNumber(scenario[2]);
         operate(n1,n2,scenario[1]);
      }
   }

   public static MegaNumber operate(MegaNumber n1, 
      MegaNumber n2, String op){
         
      switch(op) {
         case "h" :
            System.out.println(n1.toString());
            n1 = n1.halve();
            System.out.println(n1.toString());
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






