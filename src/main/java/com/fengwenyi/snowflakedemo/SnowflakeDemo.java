package com.fengwenyi.snowflakedemo;

/**
 * @author Erwin Feng
 * @since 2020/9/23
 */
public class SnowflakeDemo {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        int ID_NUMBER = 5000000;

        for (int i = 0; i < ID_NUMBER; i++) {
            // long id = SnowflakeDemo.getId();
            // System.out.println(id);
        }

        long endTime = System.nanoTime();

        double spendTime = (endTime - startTime) / 1000_000D;

        System.out.println(String.format("生成 %d 个ID，共耗时 %f 毫秒", ID_NUMBER, spendTime));
    }

}
