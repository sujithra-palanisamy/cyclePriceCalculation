package com.example.demo.execution;

import java.util.List;

public interface PriceExecution<E> {
	void execute(final List<E> cyclePriceRequestList);
}
