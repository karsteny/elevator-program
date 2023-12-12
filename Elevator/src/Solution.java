import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
	public static void main(String[] args) {
//		ElevatorSystem es = new ElevatorSystem(9,50);
//		es.requestElevator(23);
//		es.requestElevator(3);
//		System.out.println(es);
	}
}

class ElevatorSystem{
	private Elevator[] elevators;
	private final int numberOfFloors;
	
	ElevatorSystem(int elevatorCount, int numberOfFloors){
		elevators = new Elevator[elevatorCount];
		for(int i = 0; i<elevators.length; i++) {
			elevators[i] = new Elevator(numberOfFloors);
		}
		this.numberOfFloors = numberOfFloors; 
	}
	
	//Pressing the button outside the elevator
	int requestElevator(int floor) {
		if(floor >= 1 && floor <= numberOfFloors) {
			int bestDistance = numberOfFloors;
			int bestElevatorIndex = 0;
			for(int i = 0; i < elevators.length; i++) {
				int currentDistance = elevators[i].getDistanceToFloor(floor);
				if(currentDistance < bestDistance && currentDistance != -1) {
					bestDistance = currentDistance;
					bestElevatorIndex = i;
				}
			}
			elevators[bestElevatorIndex].requestFloor(floor);
			System.out.println("Elevator #"+(bestElevatorIndex)+" for Floor "+floor);
			return bestElevatorIndex;
		}else {
			System.out.println("Invalid Request");
			return -1;
		}
	}
	
	//Pressing the button inside the elevator
	void requestElevatorInside(int elevator, int floor) {
		elevators[elevator].requestFloor(floor);
	}
	
	//Simulates 1 movement of each elevator
	void moveElevators() {
		for(Elevator e : elevators) {
			int nextFloor = e.move();
			e.setPositionAndDirection(nextFloor >= 0 ? nextFloor : e.getPosition(), e.getDirection());
		}
	}
	
	void setElevatorPositionAndDirection(int elevator, int position, int direction) {
		if(elevator < 0 || elevator > elevators.length-1 || position < 0 || position >= numberOfFloors) {
			throw new IllegalArgumentException();
		}
		elevators[elevator].setPositionAndDirection(position, direction);
	}
	
	
	int getElevatorPosition(int elevator) { return elevators[elevator].getPosition(); }
	int getElevatorDirection(int elevator) { return elevators[elevator].getDirection(); }
//	int getElevatorNextStop(int elevator) { return elevators[elevator].getNextStop(); }
	
	public String toString() {
		String r = "Elv#: Flr, Dir";
		for(int i=0; i<elevators.length; i++) {
			r+="\n";
			r+=(i+1)+": "+elevators[i].toString();
			
		}
		return r;
	}
}

class Elevator{
	private int currentFloor;
	private int numFloors;
	private boolean[] requests;
	private int direction; 
	
	Elevator(int numFloors){
		currentFloor = 0;
		this.numFloors = numFloors;
		requests = new boolean[numFloors];
		direction = 0;
	}
	
	void requestFloor(int floor) {
		//Don't add the floor if the elevator is already at it
		//or if the request queue already contains it
		if(currentFloor != floor) {
			requests[floor] = true;
			
			if(direction == 0) {
				//If the elevator is idle, set the direction
				direction = floor-currentFloor >= 1 ? 1 : -1;
			}
		}
	}
	
	
	int move() {
		if(direction == 0) return -1;
		else return currentFloor+direction;
	}
	
//	int getNextStop() {
//		return requests.get(0);
//	}

	void setPositionAndDirection(int position, int direction) {
		currentFloor = position;
		this.direction = direction;
	}
	
	int getPosition() { return currentFloor; }
	
	int getDirection() { return direction; }
	
	/**
	 *  
	 * @param floor The floor number to get the distance for.
	 * @return The number of floors the elevator is from the given floor, -1 if the elevator is going the wrong way for the floor.
	 */
	public int getDistanceToFloor(int floor) {
		if(floor-currentFloor > 0) {
			//If the floor given is above the current floor...
			//Return the distance or -1 if the elevator is going down
			return direction >= 0 ? floor-currentFloor : -1;
		}else if(floor-currentFloor < 0) {
			//If the floor given is below the current floor...
			//Return the distance (positive) or -1 if the elevator is going up
			return direction <= 0 ? -(floor-currentFloor) : -1;
		}else {
			//If the floor given is the current floor...
			//Return 0 if the elevator is stopped or -1 if it is moving (as it will be "too late" if it is moving)
			return direction == 0 ? 0 : -1;
		}
	}
	
	public String toString() {
		return currentFloor + ", " + direction;
	}
}