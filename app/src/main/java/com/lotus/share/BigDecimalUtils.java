package com.lotus.share;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Thl on 2017/1/4.
 */

public class BigDecimalUtils {

    public static String DEFAULT_AMOUNT_FORMAT="###############0.00";
    public static String AUTO_SHOW_FORMAT="###############0.##";
    /**
     * 保留几位有效数字
     * @param divisor 除数
     * @param dividend 被除数
     * @param figures 保留小数位数
     * @return
     */
    public static BigDecimal divide(BigDecimal divisor , BigDecimal dividend , int figures ){
        if (divisor==null){
            divisor=BigDecimal.ZERO;
        }
        if (dividend==null){
            dividend=BigDecimal.ONE;
        }
        return divisor.divide(dividend, new MathContext(figures, RoundingMode.HALF_UP));
    }

    /**
     *  乘法计算
     * @param divisor
     * @param dividend
     * @return
     */
    public static BigDecimal multiply(BigDecimal divisor , BigDecimal dividend){
        if (divisor==null){
            divisor=BigDecimal.ZERO;
        }
        if (dividend==null){
            dividend=BigDecimal.ZERO;
        }
       return divisor.multiply(dividend);
    }

    /**
     * 减法计算
     * @param divisor
     * @param dividend
     * @return
     */
    public static BigDecimal subtract(BigDecimal divisor , BigDecimal dividend){
        if (divisor==null){
            divisor=BigDecimal.ZERO;
        }
        if (dividend==null){
            dividend=BigDecimal.ZERO;
        }
        return divisor.subtract(dividend);
    }

    /**
     * 加法计算
     * @param divisor
     * @param dividend
     * @return
     */
    public static BigDecimal add(BigDecimal divisor , BigDecimal dividend){
        if (divisor==null){
            divisor=BigDecimal.ZERO;
        }
        if (dividend==null){
            dividend=BigDecimal.ZERO;
        }
        return divisor.add(dividend);
    }

    /**
     * 格式化
     * @param value
     * @return
     */
    public static String formatAmount(BigDecimal value){
        if (value==null){
            value=BigDecimal.ZERO;
        }
        DecimalFormat df=new DecimalFormat(DEFAULT_AMOUNT_FORMAT);
        return df.format(value);
    }

    public static int compareTo(BigDecimal divisor , BigDecimal dividend){
        if (divisor==null){
            divisor=BigDecimal.ZERO;
        }
        if (dividend==null){
            dividend=BigDecimal.ZERO;
        }
        return divisor.compareTo(dividend);
    }




}
