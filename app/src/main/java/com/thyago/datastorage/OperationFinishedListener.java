package com.thyago.datastorage;

/**
 * Created by thyago on 6/3/16.
 */
public interface OperationFinishedListener<T> {
    public void onFinish(T result);
}
