package com.instacart.encryption;

public class UserEncryption {
	
	
	
	public static String PasswordEncryption(String userPassword)
	{
		String en = null;
		
		int en1 = userPassword.hashCode();        
        String encrypt1 = String.valueOf(en1);
        
        int en2 = encrypt1.hashCode();
        String encrypt2 = String.valueOf(en2);
        
        int en3 = encrypt2.hashCode();        
        String encrypt3 = String.valueOf(en3);
        
        int en4 = encrypt3.hashCode();
        String encrypt4 = String.valueOf(en4);
        
        int en5 = encrypt4.hashCode();
        String encrypt5 = String.valueOf(en5);
        
        int en6 = encrypt5.hashCode();
        String encrypt6 = String.valueOf(en6);
        
        int en7 = encrypt6.hashCode();
        String encrypt7 = String.valueOf(en7);
        
        int en8 = encrypt7.hashCode();
        String encrypt8 = String.valueOf(en8);
        
        int en9 = encrypt8.hashCode();
        String encrypt9 = String.valueOf(en9);
        
        int en10 = encrypt9.hashCode();
        en = String.valueOf(en10);
        
		return en;
	}
}
