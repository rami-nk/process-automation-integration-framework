package io.miragon.servicetaskapi.impl;

import io.miragon.servicetaskapi.api.Worker;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@AllArgsConstructor
public class WorkerAnnotationBeanProcessor implements BeanPostProcessor {

    private final WorkerRegistry workerRegistry;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithMethods(bean.getClass(), method -> {
            if (method.isAnnotationPresent(Worker.class)) {
                this.workerRegistry.register(this.buildWorker(bean, method));
            }
        });
        return bean;
    }

    private WorkerInfo buildWorker(final Object bean, final Method method) {
        final Class<?>[] inputParameterTypes = method.getParameterTypes();

        if (inputParameterTypes.length > 1) {
            throw new IllegalArgumentException("Too many parameters");
        }

        final Class<?> inputParameter = inputParameterTypes.length == 0 ? null : inputParameterTypes[0];
        return new WorkerInfo(method.getName(), inputParameter, method.getReturnType(), bean, method);
    }
}