package com.zy.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamiccProxyTest {

	static interface IHello {
		void sayHello();
	}
	
	static class Hello implements IHello {

		@Override
		public void sayHello() {
			System.out.println("Hello!");
		}
		
	}
	
	static class ProxyHandler implements InvocationHandler {

		private Object source;
		
		public Object bindTo(Object source) {
			this.source = source;
			return Proxy.newProxyInstance(source.getClass().getClassLoader(), source.getClass().getInterfaces(), this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.print("Welcome ");
			return method.invoke(source, args);
		}
		
	}
	
	public static void main(String[] args) {
		ProxyHandler handler = new ProxyHandler();
		IHello hello = (IHello) handler.bindTo(new Hello());
		hello.sayHello();
	}

}
