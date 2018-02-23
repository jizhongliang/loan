package com.hwc.framework.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by   on 2017/10/30.
 */
public class NidGenerator {
    public static final Logger logger = LoggerFactory.getLogger(NidGenerator.class);
    protected final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static int getHashCode() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        return hashCode;
    }

    public static String getOrderNo() {
        int hashCode = getHashCode();
        return String.format("%011d", hashCode);
    }

    public static String getPayOrderNo() {
        int hashCode = getHashCode();
        return String.format("%010d", hashCode);
    }

    /**
     * 评分卡nid
     *
     * @return
     */
    public static String getCardNid() {
        int hashCode = getHashCode();
        return "CC" + String.format("%011d", hashCode);
    }

    /**
     * 评分项目nid
     *
     * @return
     */
    public static String getItemNid() {
        int hashCode = getHashCode();
        return "CI" + String.format("%011d", hashCode);
    }

    /**
     * 评分卡因子nid
     *
     * @return
     */
    public static String getFactorNid() {
        int hashCode = getHashCode();
        return "CF" + String.format("%011d", hashCode);
    }

    /**
     * 评分参数nid
     *
     * @return
     */
    public static String getParamNid() {
        int hashCode = getHashCode();
        return "CFP" + String.format("%010d", hashCode);
    }

    public static void main(String[] args) {
        logger.info(getCardNid());
        logger.info(getItemNid());
        logger.info(getFactorNid());
        logger.info(getParamNid());
        logger.info(getOrderNo());
    }
}
