package com.zq.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * 视频：182丨Java事件-监听器编程模型：为什么Java中没有提供标准实现？.mp4
 * PPT: 第十七章 Spring 事件（Events）.pdf
 *
 * Java 事件/监听器编程模型
 * 	• 设计模式 - 观察者模式扩展
 * 		• 可观者对象（消息发送者） - java.util.Observable
 * 		• 观察者 - java.util.Observer
 * • 标准化接口
 * 		• 事件对象 - java.util.EventObject
 * 		• 事件监听器 - java.util.EventListener
 *
 *
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-10-17 20:30:28
 */
public class ObserverDemo {
	public static void main(String[] args) {
		// Observable 是被观察者，也是事件发布者
		// Observer 是观察者（监听者），也是事件监听者
		// 在 Java 中不成明的规定，观察者要实现 EventListener 接口
		// 另外，可以把事件发布者所关联的对象使用 EventObject 包装一下

		Observable observable = new EventObservable();

		// 添加事件观察者（事件监听者）
		observable.addObserver(new EventObserver());
		// 发布事件（消息）
		observable.notifyObservers("Hello, World"); // changed 属性是false，所以需要改变这个值
	}

	// 事件发布者
	static class EventObservable extends Observable {

		public void notifyObservers(Object arg) {
			setChanged();
			super.notifyObservers(new EventObject(arg));
			clearChanged();
		}
	}

	// 事件观察者
	static class EventObserver implements Observer, EventListener {

		/**
		 *
		 * @param o 事件发布者
		 * @param arg 事件发布者所关联的数据，也就是事件发布者发布的数据
		 */
		@Override
		public void update(Observable o, Object arg) {
			EventObject eventObject = (EventObject) arg;
			System.out.println("收到事件：" + eventObject);
		}
	}
}
