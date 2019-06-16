package com.hlj.proj.utils;



import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @author  zhangyujin
 * @param <T>
 */
public final class EmptyUtil<T> {

    private static final Integer INTEGER_0 = 0;
    private static final Long LONG_0 = 0L;
    private static final BigDecimal BIGDECIMAL_0 = BigDecimal.ZERO;


    public final static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public final static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public final static boolean isEmpty(String[] array) {
        if (array == null || array.length == 0) {
            return true;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public final static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public final static boolean isNullOr0(BigDecimal decimal) {
        return (decimal == null || BIGDECIMAL_0.compareTo(decimal) == 0) ? true : false;
    }

    public final static boolean isNullOr0(Integer integer) {
        return (integer == null || INTEGER_0.compareTo(integer) == 0) ? true : false;
    }

    public final static boolean isNullOr0(Long l) {
        return (l == null || LONG_0.compareTo(l) == 0) ? true : false;
    }

    public final static boolean isNotNullAndEquals(String str, String... existStr) {
        if (str != null) {
            for (String value : existStr) {
                if (StringUtils.equals(value, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final static boolean isNotNullAndEquals(BigDecimal decimal, BigDecimal... existDecimal) {
        if (decimal != null) {
            for (BigDecimal value : existDecimal) {
                if (decimal.compareTo(value) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public final static boolean isNotNullAndEquals(Integer integer, Integer... existInteger) {
        if (integer != null) {
            for (Integer value : existInteger) {
                if (integer.compareTo(value) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public final static boolean isNotNullAndEquals(Long l, Long... existLong) {
        if (l != null) {
            for (Long value : existLong) {
                if (l.compareTo(value) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 比较大小
     *
     * @param classType
     * @param value
     * @param otherVale * 1、如果有一个参数为空，则返回 -2 ,没有匹配的类型返回2
     *                  * 2、大小 返回 1、  = 返回0、 小于 -1
     */
    public static Integer compare(Class classType, Object value, Object otherVale) {
        if (value == null || otherVale == null) {
            return -2;
        }
        if (classType.equals(Long.class)) {

            return Long.valueOf(value.toString()).compareTo(Long.valueOf(otherVale.toString()));

        } else if (classType.equals(BigDecimal.class)) {

            return new BigDecimal(value.toString()).compareTo(new BigDecimal(otherVale.toString()));

        } else if (classType.equals(Integer.class)) {

            return Integer.valueOf(value.toString()).compareTo(Integer.valueOf(otherVale.toString()));
        }

        return 2;
    }


}
