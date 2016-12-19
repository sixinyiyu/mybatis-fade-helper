/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.utils;/**
 * Created by qingquanzhong on 2016/12/18.
 */

import java.util.Collection;
import java.util.Map;

/**
 * Description: 集合工具<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18 13:44:11
 * @since JDK 1.8
 */
public final  class CollectionUtil {

    private CollectionUtil(){

    }

    public static boolean isEmpty(Collection<?> col) {
        return null == col || col.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> col) {
        return !isEmpty(col);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**初始化map的容量、减少扩容影响*/
    public static int capacitySize(int expectedSize) {
        if (expectedSize < 3) return expectedSize + 1;
        return (int) ((float) expectedSize / 0.75F + 1.0F);
    }
}
