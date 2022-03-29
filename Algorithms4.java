import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Algorithms4 {

	public static void main(String[] args) {
		try {
			File data = new File("data.txt");
			Scanner scanner = new Scanner(data);
			
			int length = scanner.nextInt();
			Point array[] = new Point[length];
			
			for(int i = 0; i < length; i++) {
				array[i] = new Point(scanner.nextInt(), scanner.nextInt());
			}
			
			Stack<Point> stack = new Stack<Point>();
			Point point;
			int prevX = 0, prevY = 0, area = 0, maxArea = 0;
			
			for(int i = 1; i < length; i += 2) {
				if(array[i].y > prevY) {
					stack.push(array[i]);
				} else if(array[i].y < prevY) {
					while(!stack.isEmpty() && array[i].y < stack.peek().y) {
						point = stack.pop();
						area = (array[i].x - point.x) * point.y;
						if(area > maxArea) {
							maxArea = area;
						}
						prevX = point.x;
					}
					if(!stack.isEmpty() && array[i].y > stack.peek().y) {
						stack.push(new Point(prevX, array[i].y));
					}
				}
				prevY = array[i].y;
			}
			System.out.println(maxArea);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
}
