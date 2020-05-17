package com.tz.pool.datasoucepool2;

import java.util.ArrayList;
import java.util.List;

public class ConnectPoolObject {
    public static List<ConnectObject> list= new ArrayList<>();
    public static List<ConnectObject> numList= new ArrayList<>();

    public static int activeConnect=0; //活跃连接对象 ， 即借出去的连接对象

    public  static int numIdle=0; //闲置连接对象

    public static int getActiveConnect() {
        return activeConnect;
    }

    public static void setActiveConnect(int activeConnect) {
        ConnectPoolObject.activeConnect = activeConnect;
    }

    public static int getNumIdle() {
        return numIdle;
    }

    public static void setNumIdle(int numIdle) {
        ConnectPoolObject.numIdle = numIdle;
    }

    public static int getMaxNums() {
        return maxNums;
    }

    public static void setMaxNums(int maxNums) {
        ConnectPoolObject.maxNums = maxNums;
    }

    public static int maxNums =10; //池对象维护最大连接对象



    public  synchronized void remove(ConnectObject obj) {
        list.remove(obj);
        setActiveConnect(getActiveConnect()-1);
    }

    public synchronized static void returnConnet(ConnectObject obj){
        numList.add(obj);
        setNumIdle(getNumIdle()+1);
        list.remove(obj);
        setActiveConnect(getActiveConnect()-1);
    }
    public  synchronized static  ConnectObject getConnect()throws Exception{
        if(getActiveConnect()>=maxNums){
           throw new Exception("连接池已满，请稍后再试！");
        }else if(getNumIdle()>0){
            ConnectObject connectObject = numList.get(0);
            numList.remove(connectObject);
            setNumIdle(getNumIdle()-1);
            list.add(connectObject);
            setActiveConnect(getActiveConnect()+1);
            return connectObject;
        }else{
            ConnectObject connectObject =new ConnectObject();
            connectObject.setState(1);
            list.add(connectObject);
            setActiveConnect(getActiveConnect()+1);
            return connectObject;
        }
    }
}
