import java.util.ArrayList;
import java.util.TreeSet;

public class MegaNumber implements Comparable<MegaNumber>{
   
   private ArrayList<Integer> number;

   public MegaNumber(String num){
      int start =0;
 //     System.out.println("::" + num);
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
 //     System.out.println(num);
      //fill number array with individual integers.
      for(int i=start; i < num.length();i++){
         number.add(Integer.parseInt(num.substring(i,i+1)));
         //System.out.println(number.get(i));
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
      ArrayList<Integer> n1 = this.getNumber();
      ArrayList<Integer> n2 = o2.getNumber();
      ArrayList<Integer> tn;
      MegaNumber t1;
      if(this.compareTo(o2) < 0){
         t1 = o2.minus(this);
         tn = t1.getNumber();
         tn.set(0, 0-tn.get(0));
         return new MegaNumber(tn);
      }

      // x - -x
      if(n2.get(0) < 0 && n1.get(0) > 0){
         n2.set(0, Math.abs(n2.get(0)));
         return this.add(o2);
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
            //get index where there is a number we can remove from
            while((l1-j) >= 0 && n1.get(l1-j) == 0 ){
               j++;
            }
            if((l1-j) < 0){
               //???
               break;
            }

            n1.set(l1-j, n1.get(l1-j)-1);
            j--;
            while(j > 0){
               n1.set(l1-j, 9);
               j--;
            }
            //n1.set(l1-j, (n1.get(l1-j)-1));
            n = 10 - Math.abs(n);
         } 
         result = n + result;
         l2--;
         l1--;
      }
      
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
            /*t1 = this.minus(o2);
            tn = t1.getNumber();
            tn.set(0, (tn.get(0)));
            return new MegaNumber(tn);*/
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
      //MegaNumber t1 = new MegaNumber(n1);
      //MegaNumber t2 = new MegaNumber(n2);

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

   public MegaNumber dividedBy(MegaNumber o2){
      ArrayList<Integer> n1 = new ArrayList<Integer>(this.getNumber());
      ArrayList<Integer> n2 = new ArrayList<Integer>(o2.getNumber());
      MegaNumber t1 = new MegaNumber(n1);
      MegaNumber t2 = new MegaNumber(n2);
      
      boolean negn1 = false, negn2 = false;
      if((n1.size() < n2.size())){
         return new MegaNumber(0);
      }
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
      
      int n = 0;
      t1 = new MegaNumber(n1);
      t1 = t1.minus(t2);

      if(t1.getNumber().get(0) < 0){
          return new MegaNumber(0);
      }

      n++;
      while(t1.compareTo(t2) >= 0){
         n++;
         t1 = t1.minus(t2);
      }
      
      if(!(negn1 == negn2)){
         return new MegaNumber((0-n));
      } 
      return new MegaNumber(n);


   }

   public MegaNumber gcd(MegaNumber o2){
      MegaNumber gcd = new MegaNumber(1);
      TreeSet<MegaNumber> set1 = new TreeSet<MegaNumber>();
      TreeSet<MegaNumber> set2 = new TreeSet<MegaNumber>();
      MegaNumber num = new MegaNumber(4);
      
      while(num.compareTo(o2) <= 0 && num.compareTo(this) <= 0){
         set1.add(this.dividedBy(num));
         set2.add(o2.dividedBy(num));
         num = num.add(new MegaNumber(1));
      }
      
      System.out.println("Set 1: "+set1);
      System.out.println("Set 2: "+set2);
      for(MegaNumber n1 : set1){
        for(MegaNumber n2 : set2){
            if(n1.compareTo(n2) == 0){
               if(n1.compareTo(gcd) > 0){
                  gcd = n1;
               }
            }
        }
      }

      return gcd;



   }

   public ArrayList<Integer> getNumber(){
      return number;
   }

   public void setNumber(ArrayList<Integer> _number){
      number = _number;
   }

   public String toString(){
      String out = "";
      for(int i =0;i<number.size();i++){
         out += number.get(i);
      }
      return out;
   }


}

