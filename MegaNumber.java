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

      if(num.charAt(0) == '-'){
         number.add(Integer.parseInt(num.substring(0,2)));
         //reduce where we are looking
         start = 2;
      }
      
      //fill number array with individual integers.
      for(int i=start; i < num.length();i++){
         number.add(Integer.parseInt(num.substring(i,i+1)));
         System.out.println(number.get(i));
      }   
   }

   public MegaNumber(ArrayList<Integer> _number){
      number = _number;
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
      ArrayList<Integer> result = new ArrayList<Integer>();
      int num;
      //j is the index we're at
      int carry = 0, j=0;
      float temp;
      for(int i = 0;i<number.size();i++){
         num = number.get(i);
         temp = ((float) num) / 2.0f;
         if(temp < 1.0 && carry == 0){
            carry = (int) (temp*10);
         } else {
            result.add(j, (carry + ((int) temp)));
            temp = (temp - (num/2));
            carry = (int) (temp*10);
            j++;
         }
      }
      
      return new MegaNumber(result);
   }

   /**
    * -1 if o1 LT o2
    *  0 if o1 EQ o2
    *  1 if o1 GT o2
    */
   public int compareTo(MegaNumber o2){
      ArrayList<Integer> n1 = this.getNumber();
      ArrayList<Integer> n2 = o2.getNumber();
      
      //easy way to compare sizes is size of the number
      // i.e. how many digits
      if(n1.size() < n2.size()){
         return -1;
      }
      if(n1.size() > n2.size()){
         return 1;
      }

      //therefore must be equal in size
      for(int i=0;i<n1.size();i++){
         if(n1.get(i) < n2.get(i)){
            return -1;
         } else if (n1.get(i) > n2.get(i)){
            return 1;
         }
      }
      //all numbers are the same, therefore equal
      return 0;  
   }

   public MegaNumber multipliedBy(MegaNumber o2){
      ArrayList<Integer> result = new ArrayList<Integer>();
      ArrayList<Integer> n1 = this.getNumber();
      ArrayList<Integer> n2 = o2.getNumber();

      for(int i=0;i<n1.size();i++){

         for(int j=0;j<n2.size();j++){
         
         }

      }


      return result;


   }

   public MegaNumber dividedBy(MegaNumber o2){
      return null;
   }

   public MegaNumber greatestCommonDivisor(MegaNumber o2){
      return null;
   }

   public ArrayList<Integer> getNumber(){
      return number;
   }

   public String toString(){
      String out = "";
      for(int i =0;i<number.size();i++){
         out += number.get(i);
      }
      return out;
   }


}

