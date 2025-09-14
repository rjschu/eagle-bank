package com.eaglebank.api.entity;

public interface Patchable<T, R> {

    public R patch(T patched);
}
