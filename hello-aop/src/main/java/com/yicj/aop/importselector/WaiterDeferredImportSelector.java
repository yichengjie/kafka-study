package com.yicj.aop.importselector;

import com.yicj.aop.beans.Waiter;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

public class WaiterDeferredImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("WaiterDeferredImportSelector invoke ...");
        return new String[] {Waiter.class.getName()};
    }
}
