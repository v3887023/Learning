package com.example.learning;

import android.os.Bundle;

/**
 * 工厂方法接口
 *
 * @param <T>
 */
public interface Instantiable<T> {
    T newInstance(Bundle args);
}
