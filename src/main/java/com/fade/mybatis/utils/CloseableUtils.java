/**
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: fade </p>
 */
package com.fade.mybatis.utils;/**
 * Created by qingquanzhong on 2016/12/18.
 */

/**
 * Description: {一句话描述类是干什么的}<br/>
 *
 * @author qingquanzhong
 * @version 1.0
 * @date: 2016-12-18
 * @since JDK 1.8
 */
public final  class CloseableUtils {
    private CloseableUtils(){
    }

    public static void closeQuietly(AutoCloseable close) {
        try {
            closeQuietly(close, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 关闭资源,throwEx 为true则抛出异常 */
    public static void closeQuietly(AutoCloseable close, boolean throwEx) throws Exception {
        if (null != close) {
            try {
                close.close();
            } catch (Exception e) {
                if (throwEx)
                    throw e;
            } finally {
                close = null;
            }
        }
    }
}
