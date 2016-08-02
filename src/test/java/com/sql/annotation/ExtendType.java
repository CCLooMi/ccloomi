package com.sql.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ExtendType
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午8:59:18
 */
@Documented
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface ExtendType {
	int value() default 0;
}
