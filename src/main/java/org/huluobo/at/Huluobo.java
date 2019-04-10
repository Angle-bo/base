package org.huluobo.at;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author G8670
 *
 */
@Target(ElementType.METHOD) //作用域
@Retention(RetentionPolicy.RUNTIME) //生命周期
@Documented // 生成doc时会包含的注解
public @interface Huluobo {

	String parameter1() default "";

	int parameter2() default -1;
}
