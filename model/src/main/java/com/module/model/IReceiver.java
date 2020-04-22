package com.module.model;

public interface IReceiver {
	public void init();
	public void register(ICallable callback);
}
