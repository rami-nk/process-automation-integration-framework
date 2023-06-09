package io.miragon.servicetaskapi.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Method;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkerInfo {

    private String type;

    private Class<?> inputType;

    private Class<?> outputType;

    private Object instance;

    private Method method;
}