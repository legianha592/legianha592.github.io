package com.company;

public abstract class ComparableNumber<T> implements Comparable<T>, ArithmeticOperation<T>{
    abstract T value();
}
