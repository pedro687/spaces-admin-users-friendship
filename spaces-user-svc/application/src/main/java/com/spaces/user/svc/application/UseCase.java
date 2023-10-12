package com.spaces.user.svc.application;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN aInput);
}
