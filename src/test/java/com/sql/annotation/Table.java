package com.sql.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Table
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午9:03:47
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
	String name() default "";
	String schema() default "";
}
