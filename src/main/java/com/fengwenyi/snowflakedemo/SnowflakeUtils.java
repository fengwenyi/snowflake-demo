package com.fengwenyi.snowflakedemo;

import java.util.Random;

/**
 * 雪花算法工具类
 * @author Erwin Feng
 * @since 2020/9/23
 */
public class SnowflakeUtils {

    // 左边第一位的长度
    private static final long SYMBOL_LEN = 1;

    // 时间戳长度
    private static final long TIME_STAMP_LEN = 41;

    // 机器码长度
    private static final long MACHINE_NUMBER_LEN = 5;

    // 数据吗长度
    private static final long DATA_NUMBER_LEN = 5;

    // 序列码长度
    private static final long SEQUENCE_LEN = 12;

    // 初始化时间戳
    private static final long START_TIME_STAMP = 1600867367483L;

    // 上一次生成ID的时间戳
    private static long LAST_TIME_STAMP = -1L;

    // 最大的机器码
    private static final long MAX_MACHINE_NUMBER = ~(-1L << MACHINE_NUMBER_LEN);

    // 最大的数据码
    private static final long MAX_DATA_NUMBER = ~(-1L << DATA_NUMBER_LEN);

    // 最大的序列号
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_LEN);

    // 生成一个ID
    public static long nextId(long workId, long dataId, long sequence) {
        if (workId < 0
                || dataId < 0
                || sequence < 0
                || workId > MAX_MACHINE_NUMBER
                || dataId > MAX_DATA_NUMBER
                || sequence > MAX_SEQUENCE) {
            throw new RuntimeException("参数不合法");
        }

        LAST_TIME_STAMP = getSystemTimeStamp();

        long timeStamp = getTimeStamp();

        LAST_TIME_STAMP = timeStamp;

        timeStamp = timeStamp - START_TIME_STAMP;

        timeStamp = timeStamp << (SEQUENCE_LEN + DATA_NUMBER_LEN + MACHINE_NUMBER_LEN);

        long machine = getMachineNumber();
        machine = machine << (SEQUENCE_LEN + DATA_NUMBER_LEN);

        long data = getDataNumber();

        data = data << SEQUENCE_LEN;

        //long sequence = new Random().nextInt(4096);

        return timeStamp | machine | data | sequence;
    }

    private static long getSymbol() {
        return 0;
    }

    private static long getTimeStamp() {
        long timestamp = getSystemTimeStamp();
        while (timestamp < LAST_TIME_STAMP) {
            timestamp = getSystemTimeStamp();
        }
        return timestamp;
    }

    private static long getSystemTimeStamp() {
        return System.currentTimeMillis();
    }

    private static long getMachineNumber() {
        return 1;
    }

    private static long getDataNumber() {
        return 1;
    }

    private static String toBinary(long value) {
        StringBuilder result = new StringBuilder(Long.toBinaryString(value));
        while (result.length() < 64) {
            result.append("0").append(result);
        }
        return result.toString();
    }

}
