import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.security.*;
import java.io.*;

public class Elgamal {
	
	BigInteger p;
	BigInteger g;
	BigInteger x;
	BigInteger y;
	BigInteger k;
	BigInteger a;
	BigInteger b;
	BigInteger m;
	BigInteger help;
	String Filename;
	
	public Elgamal(String _Filename)
	{
		Filename = _Filename;
		Random rnd = new Random();
		p = new BigInteger(5, rnd);if (p.isProbablePrime(20));
		do
		{
			g = new BigInteger(5, rnd);
			x = new BigInteger(5, rnd);
		}
		while (p.compareTo(g) >= 1 && p.compareTo(x) >= 1);
		y = g.modPow(x, p);
	}
	
	public BigInteger NWD( BigInteger a, BigInteger b)
	{
		BigInteger t;
		  while(b.compareTo(BigInteger.valueOf(0)) != 0)
		  {
		    t = b;
		    b = a.mod(b);
		    a = t;
		  }
		  return a;
	}
	
	public byte[] md5Hash(String message) throws IOException, NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		try (InputStream is = Files.newInputStream(Paths.get("file.txt"))) {
		  DigestInputStream dis = new DigestInputStream(is, md);
		  /* Read stream to EOF as normal... */
		}
		byte[] digest = md.digest();
		return digest;
	}
	
	   public  String getMD5Checksum(String filename) throws Exception {
	       byte[] b = MD5Checksum.createChecksum(filename);
	       String result = "";

	       for (int i=0; i < b.length; i++) {
	           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
	       }
	       return result;
	   }
	   
	   public BigInteger pow(BigInteger base, BigInteger exponent) {
		   BigInteger result = BigInteger.ONE;
		   while (exponent.signum() > 0) {
		     if (exponent.testBit(0)) result = result.multiply(base);
		     base = base.multiply(base);
		     exponent = exponent.shiftRight(1);
		   }
		   return result;
		 }
	
	public void Signature() throws Exception
	{
		do
		{
			Random rnd = new Random();
			k = new BigInteger(5, rnd);
		}
		while(NWD(p,k).compareTo(BigInteger.valueOf(1)) != 0);
		a = g.modPow(k, p);
		String hash = getMD5Checksum(Filename);
		System.out.println(hash);
		help = new BigInteger(hash, 16);
		System.out.println(help);
		b = help.subtract(x.multiply(a));
		b = b.divide(k);
		b = b.mod(p.subtract(BigInteger.valueOf(1)));
		//b = b.modPow(BigInteger.valueOf(-1), p.subtract(BigInteger.valueOf(1)));
		System.out.println("Oto twoje klucze publiczne:");
		System.out.println("y:" + y);
		System.out.println("g:" + g);
		System.out.println("p:" + p);
		System.out.println("Oto twoj klucz prywatny:");
		System.out.println("x:" + x);
		System.out.println("Twoj podpis cyfrowy:");
		System.out.println("a:" + a);
		System.out.println("b:" + b);

	}
	
	public void Check_Signature()
	{
		BigInteger zmienna, zmienna2, zmienna3;
		zmienna = g.modPow(help, p);
		zmienna2 = pow(y, a);
		zmienna2 = zmienna2.multiply(pow (a,b));
		zmienna2 = zmienna2.mod(p);
		System.out.println(zmienna2);
		System.out.println(zmienna);
		if(zmienna2.equals(zmienna))
		{
			System.out.println("Stwierdzono integralnosc podpisu.");
		}
		else
		{
			System.out.println("Stwierdzono naruszenie integralnosci podpisu.");
		}
	}
}
