import java.lang.Math;
import java.math.BigInteger;
import java.math.BigDecimal; 
import java.lang.Object;
import java.lang.Number;

public class NaiveTest
{
	
	   static BigInteger power(BigInteger x, BigInteger d, BigInteger n) { 
	        
	        BigInteger res = BigInteger.ONE; 
	          
	        x = x.mod(n);  
	  
	        while (d.intValue() > 0) { 
	              
	            if ((d.intValue() & 1) == 1) 
	                res = (res.multiply(x)).mod(n); 
	          
	            d = BigInteger.valueOf(d.intValue() >> 1); 
	            x = (x.multiply(x)).mod(n); 
	        } 
	          
	        return res; 
	    } 
	    
	    static boolean miillerTest(BigInteger d, BigInteger n) { 
	        
	        BigInteger a = BigInteger.valueOf(2 + (int)(Math.random() % (n.subtract(BigInteger.valueOf(4)).intValue())));     
	        BigInteger x = power(a, d, n); 
	      
	        if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) 
	            return true; 
	      
	        while (!d.equals(n.subtract(BigInteger.ONE))) { 
	            x = (x.multiply(x)).mod(n); 
	            d = BigInteger.valueOf(d.intValue()*2);
	          
	            if (x.equals(BigInteger.ONE)) 
	                return false; 
	            if (x.equals(n.subtract(BigInteger.ONE))) 
	                return true; 
	        } 
	        return false; 
	    } 
	    
	    static boolean isPrime(BigInteger n) { 
	        int k = 3; // the higher the number the greater the accuracy
	        
	        if (n.intValue() <= 1 || n.intValue() == 4) 
	            return false; 
	        if (n.compareTo(BigInteger.valueOf(4)) == -1) 
	            return true; 
	      
	        BigInteger d = n.subtract(BigInteger.ONE); 
	          
	        while (d.mod(BigInteger.valueOf(2)) == BigInteger.ZERO) 
	            d = d.divide(BigInteger.valueOf(2)); 
	      
	        for (int i = 0; i < k; i++) 
	            if (!miillerTest(d, n)) 
	                return false; 
	      
	        return true; 
	    } 
	    
}
