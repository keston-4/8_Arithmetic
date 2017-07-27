import java.util.ArrayList;


public class MegaNumber {
   
   //private int[] number;
   private ArrayList<Integer> number;

   public MegaNumber(String num){
      int start =0;
      //negative numbers
      // reduce array size (two indicines contain one value
      //  in the string if negative)
      number = new ArrayList<Integer>();
      if(num.charAt(i) == '-'){
         number.add(Integer.parseInt(num.substring(0,2)));
         //reduce where we are looking
         start = 2;
      }
      
      //fill number array with individual integers.
      for(int i=start; i < num.length();i++){
         number.add(Integer.parseInt(num.substring(i,i+1)));
      }   
   }

   public MegaNumber(Integer num){
      this(num.toString());
   }

   public MegaNumber(int num){
      this("" + num);
   }

   //operations
   
   public MegaNumber minus(MegaNumber o2){
      return null;
   } 

   public MegaNumber add(MegaNumber o2){
      return null;
   }

   public MegaNumber halve(){
      return null;
   }

   /**
    * -1 if o1 LT o2
    *  0 if o1 EQ o2
    *  1 if o1 GT o2
    */
   public int compareTo(MegaNumber o2){
      return 0;
   }

   public MegaNumber multipliedBy(MegaNumber o2){
      return null;
   }

   public MegaNumber dividedBy(MegaNumber o2){
      return null;
   }

   public MegaNumber greatestCommonDivisor(MegaNumber o2){
      return null;
   }


   public String toString(){
      String out = "";
      for(Integer i : number){
         out += number.get(i);
      }
      return out;
   }


}

