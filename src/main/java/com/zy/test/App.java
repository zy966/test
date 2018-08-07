package com.zy.test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	App app = new App();
        System.out.println(app.inc());
    }
    
    public int inc() {
    	int x;
    	try {
    		x = 1;
    		return x;
    	} catch(Exception e) {
    		x = 2;
    		return x;
    	} finally {
    		x = 3;
    		System.out.println("finally");
    	}
    }
}
