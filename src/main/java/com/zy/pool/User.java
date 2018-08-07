package com.zy.pool;

public class User extends Element {

	private String name;
	private int gender;
	private int age;
	private int id;
	
	private byte[] info;
	
	private int elementId;
	
	public User(ElementPool pool) {
		super(pool);
		this.info = new byte[102400];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	@Override
	protected void reset() {
		this.name = null;
		this.gender = -1;
		this.age = -1;
		this.id = -1;
	}
	
	public String toString() {
		return name + ", " + gender + ", " + age + ", " + id + ", " + elementId;
	}

}
