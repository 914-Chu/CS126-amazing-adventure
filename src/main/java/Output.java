import java.util.*;

public class Output {

    public static void printDirections(List<Direction> directionList) {

        int listSize =  directionList.size();
        if(listSize == 0) {
            System.err.println("Empty direction list");
            return;
        }
        else if(listSize > 1) {

            for (int i = 0; i < listSize - 1; i++) {

                System.out.print(directionList.get(i).getDirectionName());
                System.out.print(", ");
            }
            System.out.printf("or %s\n", directionList.get(listSize-1).getDirectionName());
        }else {

            System.out.println(directionList.get(0).getDirectionName());
        }
    }

}
