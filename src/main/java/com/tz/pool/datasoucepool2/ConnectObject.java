package com.tz.pool.datasoucepool2;

public class ConnectObject {

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public  int state  =0; // 0表示关闭 1表示开启

    public void doWork()throws Exception{
        if(state==1){
            System.out.println("nihao");
        }else{
            throw  new Exception();
        }
    };
}
