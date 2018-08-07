package com.zy.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ElementPool {

	private BlockingQueue<Element> queue;
	private AtomicInteger createNum;
	
	public ElementPool() {
		this.queue = new LinkedBlockingQueue<Element>();
		this.createNum = new AtomicInteger();
	}

	protected abstract Element createElement(ElementPool pool); 
	
	public Element acquire() {
		Element ret = queue.poll();
		if(ret == null) {
			ret = createElement(this);
			createNum.incrementAndGet();
		}
		return ret;
	}

	public boolean release(Element e) {
		return queue.offer(e);
	}
	
	public int createElementNum() {
		return createNum.get();
	}
	
	public int queueSize() {
		return queue.size();
	}
	
}
