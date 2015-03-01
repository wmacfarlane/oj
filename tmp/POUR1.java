import java.util.*;

class POUR1 {
	static int a, b;
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		int numTests = sc.nextInt();
		TESTS:
		for (int test = 1; test <= numTests; test++) {
			a = sc.nextInt();
			b = sc.nextInt();
			int c = sc.nextInt();
			LinkedList<State> q = new LinkedList<State>(); // "queue"
			HashSet<State> discovered = new HashSet<State>();
			State baseState = new State(0, 0, 0);
			q.add(baseState); // we'll start at the 0th index
			discovered.add(baseState);
			while (!q.isEmpty()) {
				State currentState = q.poll(); // remove what's next in the queue
				if (currentState.x == c || currentState.y == c) {
					System.out.println(currentState.steps);
					continue TESTS;
				}
				ArrayList<State> potentialStates = new ArrayList<State>();
				potentialStates.add(new State(0, currentState.y, currentState.steps + 1));
				potentialStates.add(new State(currentState.x, 0, currentState.steps + 1));
				potentialStates.add(new State(a, currentState.y, currentState.steps + 1));
				potentialStates.add(new State(currentState.x, b, currentState.steps + 1));
				int newXLevel, newYLevel;
				// pouring x into y
				if (currentState.x + currentState.y > b) {
					newXLevel = currentState.x - (b - currentState.y);
					newYLevel = b;
				} else {
					newXLevel = 0;
					newYLevel = currentState.x + currentState.y;
				}
				potentialStates.add(new State(newXLevel, newYLevel, currentState.steps + 1));
				// pouring y into x
				if (currentState.x + currentState.y > a) {
					newYLevel = currentState.y - (a - currentState.x);
					newXLevel = a;
				} else {
					newXLevel = currentState.x + currentState.y;
					newYLevel = 0;
				}
				potentialStates.add(new State(newXLevel, newYLevel, currentState.steps + 1));
				for (State s : potentialStates) { // read this as "for each state, let's call it s, in potentialStates"
					if (!discovered.contains(s)) {
						discovered.add(s);
						q.add(s);
					}
				}              
			} //end while
			System.out.println(-1); // if the queue became empty then we couldn't reach the goal state. Output -1.
		} //end tests
	} //end main
	static class State {
		int x, y, steps;
		State(int x, int y, int s) {
			this.x = x; this.y = y; steps = s;
			// Debugging.
			if (x > a || y > b) {
				throw new IllegalArgumentException(this.toString());
			}
		}
		// You have to override this function if you're going to use a HashSet with a class you made yourself.
		// There have been numerous papers written about hashing functions. My understanding is that you normally multiply
		// one of the state variables by a prime number and add the other. 
		public int hashCode() {
			return x * 37 + y;
		}
		// Debugging.
		public String toString() {
			return x + " " + y + " " + steps;
		}
		// if you override hashCode you must override equals, or else your HashSet doesn't behave
		@Override
		public boolean equals(Object o) {
			State s = (State) o;
			return x == s.x && y == s.y;
		}
	}
}