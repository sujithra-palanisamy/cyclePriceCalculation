package com.example.demo.execution;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.service.CyclePriceRequest;
import com.example.demo.service.CycleService;

@Component
public class PriceExecutionQueue implements PriceExecution<CyclePriceRequest> {
	
	@Override
	public void execute(List<CyclePriceRequest> cyclePriceRequestList) {
		final int totalExecution = cyclePriceRequestList.size();
		int initialCount = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(300);
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
            threadPoolExecutor.execute(new PriceCalculator(cyclePriceRequestList.get(initialCount)));
            initialCount++;
            if(totalExecution == initialCount){
                threadPoolExecutor.shutdown();
                break;
            }
        }
	}
}

@Component
class PriceCalculator implements Runnable{
	
	@Autowired
	private CycleService cycleService;
	
	@Autowired
    private CyclePriceRequest cyclePriceRequest;
    public PriceCalculator(final CyclePriceRequest cyclePriceRequest){
        this.cyclePriceRequest = cyclePriceRequest;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            Optional<CyclePriceRequest> cyclePriceRequest = Optional.ofNullable(this.cyclePriceRequest);
            cyclePriceRequest.ifPresent(
            		x -> {
            			this.cycleService.calculateCyclePrice(x.getFrame(), x.getHandleandBreak(), x.getSeating(), x.getWheel(), x.getAssembly());
            			}
            		);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}