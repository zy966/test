package com.zy.pool;

public abstract class Element {

	private ElementPool pool;
	
	public Element(ElementPool pool) {
		this.pool = pool;
	}

	protected abstract void reset();
	
	public boolean release() {
		reset();
		return pool.release(this);
	}
	
}
