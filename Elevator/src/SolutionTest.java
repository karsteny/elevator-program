import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class SolutionTest {
	ElevatorSystem es;
	@Test
	void test1() {
		es = new ElevatorSystem(3,5);
		es.requestElevator(5);
		assertEquals(1, es.getElevatorDirection(0));
		assertEquals(0, es.getElevatorDirection(1));
		assertEquals(0, es.getElevatorDirection(2));
	}
	
	@Test
	void test2() {
		//All elevators stopped
		es = new ElevatorSystem(3,5);
		es.setElevatorPositionAndDirection(0, 3, 0);
		es.setElevatorPositionAndDirection(1, 1, 0);
		es.setElevatorPositionAndDirection(2, 5, 0);
		assertEquals(0, es.requestElevator(4));
		assertEquals(1, es.getElevatorDirection(0));
		assertEquals(0, es.getElevatorDirection(1));
		assertEquals(0, es.getElevatorDirection(2));
		//All elevators stopped and elevator already at floor
		es = new ElevatorSystem(3,5);
		es.setElevatorPositionAndDirection(0, 3, 0);
		es.setElevatorPositionAndDirection(1, 1, 0);
		es.setElevatorPositionAndDirection(2, 5, 0);
		assertEquals(2, es.requestElevator(5));
		assertEquals(0, es.getElevatorDirection(0));
		assertEquals(0, es.getElevatorDirection(1));
		assertEquals(0, es.getElevatorDirection(2));
		//All Elevators except 1 moving away
		es = new ElevatorSystem(3,5);
		es.setElevatorPositionAndDirection(0, 3, -1);
		es.setElevatorPositionAndDirection(1, 1, -1);
		es.setElevatorPositionAndDirection(2, 5, 0);
		assertEquals(2, es.requestElevator(4));
		assertEquals(-1, es.getElevatorDirection(0));
		assertEquals(-1, es.getElevatorDirection(1));
		assertEquals(-1, es.getElevatorDirection(2));
		//Two elevators moving toward but one is closer
		es = new ElevatorSystem(3,5);
		es.setElevatorPositionAndDirection(0, 1, 0);
		es.setElevatorPositionAndDirection(1, 2, 1);
		es.setElevatorPositionAndDirection(2, 5, -1);
		assertEquals(1, es.requestElevator(3));
		assertEquals(0, es.getElevatorDirection(0));
		assertEquals(1, es.getElevatorDirection(1));
		assertEquals(-1, es.getElevatorDirection(2));
	}

	@Test
	void test3() {
		//All elevators moving away
		es = new ElevatorSystem(3,5);
		es.setElevatorPositionAndDirection(0, 2, -1);
		es.setElevatorPositionAndDirection(1, 4, 1);
		es.setElevatorPositionAndDirection(2, 5, 1);
		assertEquals(0, es.requestElevator(3));
		assertEquals(-1, es.getElevatorDirection(0));
		assertEquals(1, es.getElevatorDirection(1));
		assertEquals(1, es.getElevatorDirection(2));
	}
	
	@Test
	void test4() {
		es = new ElevatorSystem(1,10);
		es.setElevatorPositionAndDirection(0, 4, 1);
		
		es.requestElevatorInside(0, 2);
		es.requestElevatorInside(0, 9);
		es.requestElevatorInside(0, 6);
		es.requestElevatorInside(0, 3);
		
		
		assertEquals(6, es.getElevatorNextStop(0));
	}
}
