// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) {
	  File file = new File(filename);
	  try {
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			BigInteger b = new BigInteger(sc.nextLine());
			if (!primes.getList().contains(b)) {
				primes.addPrime(b);
			}
		}
		 sc.close();
	} catch (FileNotFoundException e) {
		return false;
	}
	 
	  
	  return true;
  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
	  File file = new File(filename);
	  try {
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			String[] tokens = s.split(",");
			BigInteger left = new BigInteger(tokens[0]);
			BigInteger right = new BigInteger(tokens[1]);
			Pair<BigInteger> p = new Pair<BigInteger>(left,right);
			
			if (!primes.getCrossList().contains(p)) {
				primes.addCross(p);
			}
		}
		 sc.close();
	} catch (FileNotFoundException e) {
		System.out.print("File Not Found");
		return false;
	}
	 
	  
	  return true;
  }
  

  
  public static boolean savePrimes(Primes primes, String filename)
  {

	String out = "";
	for(BigInteger prime : primes.getList()) {
		out = out + Integer.toString(prime.intValue()) + "\n";
	}
		
		 File file = new File(filename);
		 FileWriter fw = null;
		 try {
			 fw = new FileWriter(file);
			 fw.write(out);
		 }	
		 catch (IOException e) {
			 e.printStackTrace();
			 return false;
		 }
		 finally {
			 try {
				 fw.close();
			 }
			 catch (IOException e) {
				 e.printStackTrace();
				 return false;
			 }
		 }
  	return true;
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
		String out = "";
		for(Pair<BigInteger> prime : primes.getCrossList()) {
			out = out + Integer.toString(prime.left().intValue()) + "," + Integer.toString(prime.right().intValue()) + "\n";
		}
			
			 File file = new File(filename);
			 FileWriter fw = null;
			 try {
				 fw = new FileWriter(file);
				 fw.write(out);
			 }	
			 catch (IOException e) {
				 e.printStackTrace();
				 return false;
			 }
			 finally {
				 try {
					 fw.close();
				 }
				 catch (IOException e) {
					 e.printStackTrace();
					 return false;
				 }
			 }
	  	return true;
  }
}