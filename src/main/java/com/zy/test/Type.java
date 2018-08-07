package com.zy.test;

public abstract class Type {

	protected final static String A = "A";
	protected final static String B = "B";
	protected final static String C = "C";
	protected final static String D = "D";
	
	public static Type create(String type) {
		switch (type) {
		case A:
			return new TypeA();
		case B:
			return new TypeB();
		case C:
			return new TypeC();
		case D:
			return new TypeD();
		default:
			return null;
		}
	}
	
	public abstract String getName();
	
	@Override
	public String toString() {
		return getName();
	}

	public static void main(String[] args) {
		
		System.out.println(Type.create(Type.A));
		System.out.println(Type.create(Type.B));
		System.out.println(Type.create(Type.C));
		System.out.println(Type.create(Type.D));
	}
}
