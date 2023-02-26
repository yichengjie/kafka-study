package com.yicj.aop.anno;


import com.yicj.aop.beans.Boss;
import com.yicj.aop.config.BartenderConfiguration;
import com.yicj.aop.importselector.BarImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({Boss.class, BartenderConfiguration.class, BarImportSelector.class})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableTavern {


}
