package com.info.pojo;

public class Marks {
	private int marks; 
	private String subject;
	@Override
	public String toString() {
		return "Marks [marks=" + marks + ", subject=" + subject + "]";
	}
	public Marks(int marks, String subject) {
		this.marks = marks;
		this.subject = subject;
	}
}
