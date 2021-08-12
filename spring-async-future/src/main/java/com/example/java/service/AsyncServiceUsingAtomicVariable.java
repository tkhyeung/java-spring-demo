package com.example.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class AsyncServiceUsingAtomicVariable {
    /**
     * ++i is i.incrementAndGet()  (returning updated value)
     * i++ is i.getAndIncrement()
     * --i is i.decrementAndGet()
     * i-- is i.getAndDecrement()
     * i = x is i.set(x)
     * x = i is x = i.get()
     */

    public static class AtomicVariableTest{
        private AtomicInteger count = new AtomicInteger(0);

        private static void sleep(int second){
            try {
                TimeUnit.SECONDS.sleep(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        public void add(int num, int id){
//            log.info("id:{} doing addAndGet({}), before count:{}", id, num, count.get());
//            int intAfterAdded = count.addAndGet(num);
//            log.info("id:{} after intAfterAdded:{} count:{}", id, intAfterAdded, count.get());

            boolean updated = false;
            while(!updated){
                int currentValue = count.get();
                int newValue = currentValue + num;
                sleep(1);
                log.info("id:{} doing compareAndSet({}, {}) (adding {})", id,currentValue, newValue, num);
                updated = count.compareAndSet(currentValue, newValue);
            }

        }


        public void minus(int num, int id){
            boolean updated = false;
            while(!updated){
                int currentValue = count.get();
                int newValue = currentValue - num;
                sleep(1);
                log.info("id:{} doing compareAndSet({}, {}) (subtracting {})", id,currentValue, newValue, num);
                updated = count.compareAndSet(currentValue, newValue);
            }
        }
    }

    private AtomicVariableTest test = new AtomicVariableTest();

    @Async
    public CompletableFuture<Void> add(int num, int id){
        return CompletableFuture.runAsync( () -> {
            test.add(num,id);
        });
    }

    @Async
    public CompletableFuture<Void> minus(int num, int id){
        return CompletableFuture.runAsync( () -> {
            test.minus(num,id);
        });
    }

    public int getValue(){
        return test.count.get();
    }

}
