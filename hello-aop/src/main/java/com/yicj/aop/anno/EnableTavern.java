package com.yicj.aop.anno;


import com.yicj.aop.beans.Boss;
import com.yicj.aop.config.BartenderConfiguration;
import com.yicj.aop.importselector.BarImportSelector;
import com.yicj.aop.importselector.WaiterDeferredImportSelector;
import com.yicj.aop.registrar.WaiterRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({Boss.class,
        BarImportSelector.class,
        WaiterRegistrar.class,
        WaiterDeferredImportSelector.class,
        BartenderConfiguration.class})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableTavern {


}
