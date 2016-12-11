package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;


public class MyLock {

	public static void withLock(ReentrantLock lock, Runnable runner) {
		lock.lock();
		System.out.println("isLocked : " + lock.isLocked());
		try {
			runner.run();
		} finally {
			lock.unlock();
			System.out.println("isLocked : " + lock.isLocked());
		}
	}

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		MyLock.withLock(lock, () -> {System.out.println("test runner");});
	}
}
