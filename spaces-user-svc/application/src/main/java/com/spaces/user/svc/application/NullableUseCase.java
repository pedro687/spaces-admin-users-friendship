package com.spaces.user.svc.application;

public abstract class NullableUseCase<IN> {
    public abstract void execute(IN aInput);
}
