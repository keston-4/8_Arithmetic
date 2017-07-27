import java.util.Scanner;

public class Main {

   

   public static void main(String[] args){
      Scanner scan = new Scanner(System.in);
      String ret = "";
      String[] input = new String[3];
      ArrayList<String[]> scenarios = new ArrayList<String[]>();
      
      while(scan.hasNextLine()){
         ret = scan.nextLine();
         if(ret.split(" ").length != 3){
            System.out.println("Syntax error");
            continue;
         }
         
         input = ret.split(" ");
         //if input[0] is num
         if(!checkOperation(input[1]) {
            System.out.println("Syntax error");
            continue;
         }

         //if input[2] is num
         
         scenarios.add(input);

      }

      //note singular and plural
      for(String[] scenario : scenarios){
      
      }


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
               break;
            case default :
               return false;
         }


      }


   }



}



