package com.ccloomi.core.component.binery.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BytesLength
 * 类 描 述：Byte数组长度
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月13日-下午11:51:37
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BytesLength {
	String ref();
}
