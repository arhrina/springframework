package com.biz.app;

public class ScoreVO {
	
	
	
	
	public void setKor(int kor) {
		this.kor = kor;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public void setSci(int sci) {
		this.sci = sci;
	}
	public void setMus(int mus) {
		this.mus = mus;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	public int getKor() {
		return kor;
	}
	public int getEng() {
		return eng;
	}
	public int getMat() {
		return mat;
	}
	public int getSci() {
		return sci;
	}
	public int getMus() {
		return mus;
	}
	public int getSum() {
		return sum;
	}
	public int getAvg() {
		return avg;
	}
	
	public ScoreVO(int kor, int eng, int mat, int sci, int mus, int sum, int avg) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.sci = sci;
		this.mus = mus;
		this.sum = sum;
		this.avg = avg;
	}
	
	public ScoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ScoreVO [kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", sci=" + sci + ", mus=" + mus + ", sum="
				+ sum + ", avg=" + avg + "]";
	}


	private int kor;
	private int eng;
	private int mat;
	private int sci;
	private int mus;
	
	private int sum;
	private int avg;
}
