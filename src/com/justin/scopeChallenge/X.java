package com.justin.scopeChallenge;

import java.util.Scanner;

public class X {
  private int x;

  public void displayTimeTable(){
	System.out.println("Please Enter An Integer");
	this.x = new Scanner(System.in).nextInt();
	System.out.println("Printing out time table for " + this.x);
	Counter x = new Counter();
	mutiply(x);
  }

  private void mutiply(Counter x){
	if(x.getX() > 12) return;
	System.out.println(this.x + " x " + x.getX() + " = " + this.x * x.getX());
	x.setX(x.getX() + 1);
	mutiply(x);
  }

  private class Counter{
    private int x = 1;

	public int getX() {
	  return x;
	}

	public void setX(int x) {
	  this.x = x;
	}
  }
}
