package com.company;

public class Main {

    public static void main(String[] args) {
	    Rectangle rect1 = new Rectangle();
	    rect1.setWidth(40);
	    rect1.setHeight(4);
	    rect1.setCenter(0, 0);

	    Rectangle rect2 = new Rectangle();
	    rect2.setWidth(35.9);
	    rect2.setHeight(3.5);
	    rect2.setCenter(2, 2);

	    CheckCollision collision = new CheckCollision();
	    collision.getRect1(rect1.center_x, rect1.center_y, rect1.width, rect1.height);
	    collision.getRect2(rect2.center_x, rect2.center_y, rect2.width, rect2.height);
	    collision.checkCollisionOfTwoRects();
    }
}
