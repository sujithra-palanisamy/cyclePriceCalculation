package com.example.demo.execution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.CyclePriceRequest;
import com.example.demo.service.CycleResponseEntity;
import com.example.demo.service.CycleService;

@Component
public class PriceTest implements PriceExecution<CyclePriceRequest> {
	
	@Autowired
	private CycleService cycleService;

	@Override
	public void execute(final List<CyclePriceRequest> cyclePriceRequestList) {
		final int totalExecution = cyclePriceRequestList.size();
		int initialCount = 0;
		int cycle=1;
		int totalCycle = cyclePriceRequestList.size();
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(300);
        final Queue<CyclePriceRequest> queue = new LinkedList<CyclePriceRequest>();
        queue.addAll(cyclePriceRequestList);
        final LinkedList<Integer> cycleSize=new LinkedList<Integer>();
       for(int i=1;i<totalCycle;i++) {
    	   cycleSize.add(i);
       }
        // core and max pool size will be 10 threads
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,5, TimeUnit.MINUTES,blockingQueue);
        threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executor.execute(r);
            }
        });
        threadPoolExecutor.prestartAllCoreThreads();
        while (true){
            Runnable r = () -> {
            	try {
                    Thread.sleep(500);
                    Optional<CyclePriceRequest> cyclePriceRequest = Optional.ofNullable(queue.poll());
                    cyclePriceRequest.ifPresent(
                    		x -> {
                    			CycleResponseEntity cycleResponseEntity = this.cycleService.calculateCyclePrice(x.getFrame(), x.getHandleandBreak(), x.getSeating(), x.getWheel(), x.getAssembly());
                    			if(!(cycleSize.size()==0)) {
                    			System.out.println("ByCycle Number :"+String.valueOf(cycleSize.get(0)));
                    			cycleSize.remove(0);
                    			}
                    			System.out.println("Frame Price = "+String.valueOf(cycleResponseEntity.getFrameprice()));
		            			System.out.println("Handle Price = "+String.valueOf(cycleResponseEntity.getHandlePrice()));
		            			System.out.println("Seat Price = "+String.valueOf(cycleResponseEntity.getSeatPrice()));
		            			System.out.println("Wheel Price = "+String.valueOf(cycleResponseEntity.getWheelPrice()));
		            			System.out.println("Chain Price = "+String.valueOf(cycleResponseEntity.getChainPrice()));
		            			System.out.println("Bycycle Total Price = "+String.valueOf(cycleResponseEntity.getCycleTotalPice()));
                    			}
                    		);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            threadPoolExecutor.execute(r);
            initialCount++;
            if(totalExecution == initialCount){
                threadPoolExecutor.shutdown();
                break;
            }
        }
		
	}
}