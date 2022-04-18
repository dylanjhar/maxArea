import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Algorithms4 {
	
	/*
	 * Load vertices into array (O(n))
	 * Traverse through every other vertex starting at the second vertex
	 * because those are the points where changes in height and width occur
	 * If the current y is greater than the previous point's y,
	 * push the current point onto the stack because it's the start of a new rectangle
	 * If the current y is less than the previous point's y,
	 * While the stack isn't empty and the current y is less than the y of the point on the stack,
	 * pop the stack because it's the end of a rectangle and figure out it's area
	 * assign the area to max area if it's greater than the current max area
	 * If the stack isn't empty and the current y is greater than the y of the point on the stack,
	 * push a new point onto the stack because there is a new rectangle
	 * O(n) because traversing through n/2 points in array and at worst going through n/2 points
	 * on the stack by the end. Stack operations are O(1)
	 * At worst traversing through n points which is O(n)
	 */

	public static void main(String[] args) {
		try {
			File data = new File("data.txt");
			Scanner scanner = new Scanner(data);
			
			int length = scanner.nextInt();
			Point array[] = new Point[length];
			
			//Load vertices into array
			for(int i = 0; i < length; i++) {
				array[i] = new Point(scanner.nextInt(), scanner.nextInt());
			}
			
			Stack<Point> stack = new Stack<Point>();
			Point point;
			int prevX = 0, prevY = 0, area = 0, maxArea = 0;
			int n = 0;
			
			//Traverse through every other point in array starting with the second point
			for(int i = 1; i < length; i += 2) {
				n++;
				//If current point's y is greater than previous point's y, start of new rectangle
				if(array[i].y > prevY) {
					stack.push(array[i]);
				//If current point's y is less than previous point's y, end of a rectangle
				} else if(array[i].y < prevY) {
					while(!stack.isEmpty() && array[i].y < stack.peek().y) {
						n++;
						point = stack.pop();
						area = (array[i].x - point.x) * point.y;
						if(area > maxArea) {
							maxArea = area;
						}
						//Used to determine starting point of a new rectangle in next step if needed
						prevX = point.x;
					}
					//If current point's y is greater than y of point on stack, need to account for new rectangle
					if(!stack.isEmpty() && array[i].y > stack.peek().y) {
						stack.push(new Point(prevX, array[i].y));
					}
				}
				prevY = array[i].y;
			}
			System.out.println(maxArea);
			System.out.println("n: " + n);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
}
