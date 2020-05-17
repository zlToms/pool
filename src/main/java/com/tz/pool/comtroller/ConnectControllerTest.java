package com.tz.pool.comtroller;

import com.tz.pool.datasoucepool2.ConnectObject;
import com.tz.pool.datasoucepool2.ConnectPoolObject;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CountDownLatch;

@Controller
public class ConnectControllerTest {

    static CountDownLatch latch =new CountDownLatch(50);

    @RequestMapping("pooltest")
    public String test() throws Exception {
        ConnectObject conn= ConnectPoolObject.getConnect();
        conn.doWork();
        ConnectPoolObject.returnConnet(conn);
        conn = null;
        System.out.println(ConnectPoolObject.getMaxNums());
        return "djsijd";
    }

    @RequestMapping("pooltest2")
    public String test02() throws InterruptedException {

        for(int i = 0;i<50;i++){
          new Thread(new Work()).start();
        }
        latch.await();

        return "djia";
    }

    public static class Work implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            ConnectObject conn= ConnectPoolObject.getConnect();
            conn.doWork();
            ConnectPoolObject.returnConnet(conn);
            conn = null;
            System.out.println(Thread.currentThread().getId()+"--:maxNum="+ConnectPoolObject.getMaxNums()
            +"--numIdel="+ConnectPoolObject.getNumIdle()+"--activeNum="+ConnectPoolObject.getActiveConnect()+"--总数:"+
                    (ConnectPoolObject.getNumIdle()+ConnectPoolObject.getActiveConnect())+"++"+ConnectPoolObject.getMaxNums());
            latch.await();
        }
    }


}
