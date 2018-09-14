import static java.lang.System.out;
import java.util.Scanner;
import java.util.Arrays;

public class Lab2Question3
{

    public static boolean less(Comparable a, Comparable b)
    {
        return (a.compareTo(b) < 0);  //if a less b returns -1
    }  // if -1 less than 0

    public static void exchange(Comparable[] input, int x, int y)
    {
        Comparable temp = input[x];  //take the data from x index
        input[x] = input[y];  //put y index data into x index
        input[y] = temp;  //put the x data into the y index
    }

    public static void sort(Comparable[] input)
    {
        int length = input.length;
        int counter = 0;

        out.println(Arrays.toString(input));
        for (int x = 1; x < length; x++)  //iterates through the array from index 1
        {
            for (int y = x; y > 0 && less(input[y], input[y - 1]); y--)  //iterates through the array that has not been
            {  //iterated. checks if index y - 1 item is bigger than index y if true and goes in
                exchange(input, y, y - 1);
                out.println(Arrays.toString(input));
                counter++;
                //out.printf("Swap number: %d\n", counter);
            }
        }

        out.printf("Amount of swap(s): %d\n", counter);

        for (int x = 0; x < (input.length / 2); x++)
        {
            exchange(input, x, input.length - (x + 1));
        }

        out.println(Arrays.toString(input));
    }

    public static void main(String [] args)
    {
        Comparable[] intArray = null;
        out.println("Input array size: ");
        Scanner in = new Scanner(System.in);
        String size = in.nextLine();
        int s = Integer.parseInt(size);
        intArray = new Comparable[s];
        out.println("Input array items without space: ");
        String input = in.nextLine();

        for (int x = 0; x < input.length(); x++)
        {
            intArray[x] = Character.getNumericValue(input.charAt(x));
        }

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }

        Lab2Question3 sorting = new Lab2Question3();
        sorting.sort(intArray);

        for (int x = 0; x < input.length(); x++)
        {
            out.println(intArray[x]);
        }
    }
}

