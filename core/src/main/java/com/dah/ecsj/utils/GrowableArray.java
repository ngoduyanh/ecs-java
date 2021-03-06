package com.dah.ecsj.utils;

import java.util.Arrays;
import java.util.Objects;

public class GrowableArray<T> {
    private Object[] elements;
    private int size;
    private final float growScale;

    private static final int INIT_ARR_SIZE = 16;
    private static final float GROW_SCALE = 0.5f;
    public GrowableArray() {
        this(INIT_ARR_SIZE, GROW_SCALE);
    }

    public GrowableArray(int initArrSize, float growScale) {
        elements = new Object[initArrSize];
        this.growScale = growScale;
    }

    public void set(int idx, T element) {
        if(elements.length <= idx) {
            elements = new Object[Math.max(idx + 1, (int) (elements.length * growScale))];
        }

        if(size <= idx) size = idx + 1;

        elements[idx] = element;
    }

    @SuppressWarnings("unchecked")
    public T setAndReturnOld(int idx, T element) {
        if(elements.length <= idx) {
            elements = new Object[Math.max(idx + 1, (int) (elements.length * growScale))];
        }

        if(size <= idx) size = idx + 1;

        var ret = elements[idx];
        elements[idx] = element;
        return (T) ret;
    }

    @SuppressWarnings("unchecked")
    public T remove(int idx) {
        Object element = elements[idx];
        elements[idx] = null;
        if(idx == size - 1) size--;
        return (T) element;
    }

    @SuppressWarnings("unchecked")
    public T get(int idx) {
        if(idx >= size) return null;
        return (T) elements[idx];
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    public int indexOf(T t) {
        for(int i = 0; i < size; i++) {
            if(Objects.equals(t, elements[i]))  return i;
        }
        return -1;
    }

    public int indexOfIdentity(T t) {
        for(int i = 0; i < size; i++) {
            if(t == elements[i])    return i;
        }
        return -1;
    }
}
