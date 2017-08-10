/* Etude 8 - Arithmetic
 * Authors: Kurt Weston & James Douglas
 */
import java.util.ArrayList;
import java.util.TreeSet;

public class MegaNumber implements Comparable<MegaNumber>{
   
   private ArrayList<Integer> number;
   private MegaNumber remainder;

   public MegaNumber(String num){
      int start =0;
      //negative numbers
      // reduce array size (two indicines contain one value
      //  in the string if negative)
      number = new ArrayList<Integer>();
      if(num.length() == 0){
         return;
      }

      if(num.charAt(0) == '-'){
         number.add(Integer.parseInt(num.substring(0,2)));
         
         //reduce where we are looking
         start = 2;
         if(start == num.length()){
            return;
         }
      }
      
      for(int i=0;i<num.length();i++){
         if(num.charAt(i) == '0'){
            start++;
         } else {
            break;
         }
      }

      //if the entire number is zero
      if(start == num.length()){
         number.add(new Integer(0));
         return;
      }
      //fill number array with individual integers.
      for(int i=start; i < num.length();i++){
         number.add(Integer.parseInt(num.substring(i,i+1)));
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

   public MegaNumber minus(MegaNumber o2){
      ArrayList<Integer> n1 = new ArrayList<Integer>(this.getNumber());
      ArrayList<Integer> n2 = new ArrayList<Integer>(o2.getNumber());
      ArrayList<Integer> tn;
      MegaNumber t1;
      MegaNumber t2;

      if(this.compareTo(o2) < 0){
         t1 = o2.minus(this);
         tn = t1.getNumber();
         tn.set(0, 0-tn.get(0));
         return new MegaNumber(tn);
      }

      // x - -x
      if(n2.get(0) < 0 && n1.get(0) > 0){
         n2.set(0, Math.abs(n2.get(0)));
         t2 = new MegaNumber(n2);
         return this.add(t2);
      }
      
      //-x - x
      if(n1.get(0) < 0 && n2.get(0) > 0){
         n2.set(0, 0-(n2.get(0)));
         return this.add(new MegaNumber(n2));
      }

      String result = "";
      int l1 = n1.size()-1;
      int l2 = n2.size()-1;
      int j = 1;
      int n = 0, postremain = 0, inremain;
      
      while(l1 >= 0 && l2>= 0){
         n = n1.get(l1) - n2.get(l2);
         
         if(n < 0) {
            j = 1;
            //get index where there is a number we can remove from
            while((l1-j) >= 0 && n1.get(l1-j) == 0 ){
               j++;
            }
            if((l1-j) < 0){
               break;
            }
            
            //j is an offset!
            n1.set(l1-j, n1.get(l1-j)-1);
            j--;
            while(j > 0){
               n1.set(l1-j, 9);
               j--;
            }
            n = 10 - Math.abs(n);
         } 
         n1.set(l1, n);
         result = n + result;
         l2--;
         l1--;
      }
      //add the remaining n1/n2 values to result
      for(int i=l1;l1>=0;l1--){
         result = n1.get(l1) + result;
      }
      for(int i=l2;l2>=0;l2--){
         result = n2.get(l2) + result;
      }
      return new MegaNumber(result);
   } 

   public MegaNumber add(MegaNumber o2){
      ArrayList<Integer> n1 = new ArrayList<Integer>(this.getNumber());
      ArrayList<Integer> n2 = new ArrayList<Integer>(o2.getNumber());
      ArrayList<Integer> tn;
      MegaNumber t1 = new MegaNumber(n1);
      MegaNumber t2 = new MegaNumber(n2);
      String result = "";
      
      int l1 = n1.size()-1;
      int l2 = n2.size()-1;
      int n = 0, carry = 0;
      boolean negn1 = false, negn2 = false;

      if(n1.get(0) < 0 ){
         n1.set(0, Math.abs(n1.get(0)));
         t1.setNumber(n1);
         negn1 = true;
      }
      if(n2.get(0) < 0){
         n2.set(0, Math.abs(n2.get(0)));
         t2.setNumber(n2);
         negn2 = true;
      }
      
      if(!(negn1 == negn2)){
         //-x + y >> y - x
         if(negn1){
            t1 = t2.minus(t1);
            tn = t1.getNumber();
            tn.set(0, (tn.get(0)));
            return new MegaNumber(tn);
         //x + -y >> x - y
         } else {
            return t1.minus(t2);
         } 
      }

      while(l1 >= 0 && l2>=0){
         n = n1.get(l1) + n2.get(l2);
         n = carry + n;
         carry = 0;
         if(n >= 10){
            carry = 1; //carry max is 1 (when n+n >=10)
            n = n - 10; // 9+9+ = 18, maximum possible carry is 1 (10)
         }
         result = n + result;
         l1--;
         l2--;         
      }     
      for(int i=l1; l1 >= 0; l1--){
         n = carry + n1.get(l1);
         carry = 0;
         if(n>=10){
            carry = 1;
            n = n - 10;
         }
         result = n + result;
      }
      for(int i=l2; l2 >= 0; l2--){
         n = carry + n2.get(l2);
         carry = 0;
         if(n>=10){
            carry = 1;
            n = n - 10;
         }
         result = n + result;
      }
      if(carry > 0){
         result = carry + result;
      }
      if(negn1){
         return new MegaNumber("-"+result);
      }
      return new MegaNumber(result);
   }

   public MegaNumber halve(){
      ArrayList<Integer> result = new ArrayList<Integer>();
      int num;
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

   /**
    * Essentially multiplication is performed just as you would
    *  in primary school, times the first num in list 1, by the first num
    *  in list 2, then the first num is list 1 by the second
    *  num in list 2, and so on. When you reach the end of a 
    *  list, add it to a total sum (in this case 'output')
    */
   public MegaNumber multipliedBy(MegaNumber o2){
      ArrayList<Integer> n1 = new ArrayList<Integer>(this.getNumber());
      ArrayList<Integer> n2 = new ArrayList<Integer>(o2.getNumber());

      MegaNumber output = new MegaNumber(0);
      boolean negn1 = false, negn2 = false;
      String result = "";
      int n = 0;
      int exp = 0;
      
      if(n1.get(0) < 0){
         n1.set(0, Math.abs(n1.get(0)));
         negn1 = true;
      }
      
      if(n2.get(0) < 0){
         n2.set(0, Math.abs(n2.get(0)));
         negn2 = true;
      }
      
      //for every number in n1
      for(int i=n1.size()-1;i>=0;i--){
         //multiply it by every number in n2
         for(int j=n2.size()-1;j>=0;j--){
            exp = ((n1.size()-1)-i) + ((n2.size()-1)-j);
            result = (""+(n1.get(i) * n2.get(j))) + getZeroes(exp);
            output = output.add(new MegaNumber(result));
         }
      }
      if(!(negn1==negn2)){
         n1 = output.getNumber();
         n1.set(0, 0-n1.get(0));
         return new MegaNumber(n1);
      }
      return output;
   }

   public String getZeroes(int num){
      int count = 0;
      String out = "";
      while(count < num){
         out += "0";
         count++;
      }
      return out;
   }

   /** Simply minus n2 from n1, n2 is scaled by 10^n such that 
    *  they n1 is slightly bigger than n2, then minus n2 from n1.
    *
    */
   public MegaNumber dividedBy(MegaNumber o2){
      ArrayList<Integer> n1 = new ArrayList<Integer>(this.getNumber());
      ArrayList<Integer> n2 = new ArrayList<Integer>(o2.getNumber());
      ArrayList<Integer> n3 = new ArrayList<Integer>();
      MegaNumber t1 = new MegaNumber(n1);
      MegaNumber t2 = new MegaNumber(n2);
      MegaNumber res = new MegaNumber(n2);
      MegaNumber out;
      boolean negn1 = false, negn2 = false;
      
      if(n1.get(0) < 0){
         n1.set(0, Math.abs(n1.get(0)));
         t1.setNumber(n1);     
         negn1 = true;
      }

      if(n2.get(0) < 0){
         n2.set(0, Math.abs(n2.get(0)));
         t2.setNumber(n2);
         negn2 = true;
      }
      
      if(t1.compareTo(t2) < 0){
         out = new MegaNumber(0);
         out.setRemainder(this);
         return out;
      }
      
      int n = 0;
      int j = 0;
      String v = "";
      out = new MegaNumber(0);
      
      while(t1.compareTo(t2) >= 0){
         n = 0;
         j = 0;
         res = new MegaNumber(n2);
         while(res.getNumber().size() < t1.getNumber().size()-1){
            res = res.multipliedBy(new MegaNumber(10));
            j++;
         }

         if((res.multipliedBy(new MegaNumber(10))).compareTo(t1) < 0){
            res = res.multipliedBy(new MegaNumber(10));
            j++;
         }
         while(t1.compareTo(res) >= 0){
            n++;
            t1 = t1.minus(res);
         }
      
         out = out.add(new MegaNumber(n + getZeroes(j)));
      }

      //make negative
      if(!(negn1 == negn2)){
         n3 = out.getNumber();
         n3.set(0, 0-n3.get(0));
         out = new MegaNumber(n3);
      }
      out.setRemainder(t1);
      return out;
   }

   /** Uses Euclidean GCD algorithm to find GCD.
    *    Essentially, recursively find the gcd of the
    *    n2 and the remainder from n1/n2.
    */
   public MegaNumber gcd(MegaNumber o2){
      MegaNumber t1 = new MegaNumber(this.getNumber());
      MegaNumber t2 = new MegaNumber(o2.getNumber());
      MegaNumber n;
      
      if(t1.compareTo(new MegaNumber(0)) == 0
            && t2.compareTo(new MegaNumber(0)) == 0){
         return new MegaNumber(0);
      }
      
      if(t1.compareTo(new MegaNumber(0)) == 0){
         return t2; //stop
      }
      if(t2.compareTo(new MegaNumber(0)) == 0){
         return t1; //stop
      }
      
      n = t1.dividedBy(t2);
      return t2.gcd(n.getRemainder());

   }

   public ArrayList<Integer> getNumber(){
      return number;
   }

   public void setNumber(ArrayList<Integer> _number){
      number = _number;
   }

   public void setRemainder(MegaNumber n){
      remainder = n;
   }

   public MegaNumber getRemainder(){
      return remainder;
   }

   public String toString(){
      String out = "";
      for(int i =0;i<number.size();i++){
         out += number.get(i);
      }
      return out;
   }
}
