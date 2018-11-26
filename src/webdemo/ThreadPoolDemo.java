package webdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	
    static ExecutorService cachedThreadPool;
    //static ExecutorService fixedThreadPool;
    //static ScheduledExecutorService scheduledThreadPool;
    //static ExecutorService singleThreadPool;
    
    static {
    	cachedThreadPool = Executors.newCachedThreadPool();
    }
	
	public static void main(String[] args) {
		 
        String param1 = "1";
        String param2 = "2";
        System.out.println("start");
        cachedThreadPool.execute(()->{
        	System.out.println("param1:"+param1);
        	try {
        		System.out.println("Thread.sleep(1000)");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	System.out.println("param2:"+param2);
        });
        System.out.println("end");
	}

}
