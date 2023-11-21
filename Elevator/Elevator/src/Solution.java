import java.util.ArrayList;

public class Solution {
	public static void main(String[] args) {
		ElevatorSystem es = new ElevatorSystem(1,5);
		es.requestElevator(3);
		System.out.println(es);
	}
}

class ElevatorSystem{
	private Elevator[] elevators;
	
	private final int numberOfFloors;
	
	ElevatorSystem(int elevatorCount, int numberOfFloors){
		elevators = new Elevator[elevatorCount];
		for(int i = 0; i<elevators.length; i++) {
			elevators[i] = new Elevator();
		}
		this.numberOfFloors = numberOfFloors; 
	}
	
	void requestElevator(int floor) {
		if(floor >= 1 && floor <= numberOfFloors) {
			int bestDistance = -1;
			int bestElevatorIndex = -1;
			for(int i = 0; i < elevators.length; i++) {
				int currentDistance = elevators[i].getDistanceToFloor(floor);
				
				if(currentDistance < bestDistance) {
					
				}
			}
		}	
	}
	
	public String toString() {
		String r = "Elv#|Flr.|Dir";
		
		return r;
	}
}

class Elevator{
	private int currentFloor;
	private ArrayList<Integer> requests;
	private int direction; 
	
	Elevator(){
		currentFloor = 1;
		requests = new ArrayList<Integer>();
		direction = 0;
	}
	
	void requestFloor(int floor) {
		if(!requests.contains(floor)) {
			requests.add(floor);
		}
	}
	
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
}