package com.example.demo.model;

import java.time.LocalDateTime;

public class SalesPageData {
	private boolean done;
	private int page;
	private int pageSize;
	private LocalDateTime start;
	private LocalDateTime end;
	public SalesPageData(boolean done, int page, int pageSize, LocalDateTime start, LocalDateTime end) {
		super();
		this.done = done;
		this.page = page;
		this.pageSize = pageSize;
		this.start = start;
		this.end = end;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
}
