import java.util.*;
import java.lang.Math;  // To call abs() function

/*
    Five bluetooth devices tries to connect at the same time. Only one out the 5 connects using various test cases.
 */
public class BlueTooth {

    public void latestConnects(String[] arr){
        //The stack is updated with the values of the devices that connected in the car bluetooth system.

        Stack stack = new Stack<String>();
        for(int i = 0; i < 5 ; i++){
            if(arr[i] != null)
                stack.push(arr[i]);

        }
        // The Device recently connected will be the device present in the top index.
        System.out.println("The Device ### "+stack.peek()+" ### connects to your bluetooth System");

    }

    public void oldestconnects(String[] arr){

        /*Queue is implemented to connect to the oldest connected device
         Queue is updated with the devices each time the function gets called */
        Queue<String> queue = new LinkedList<> ();

        for(int i = 0; i < 5 ; i++){
            queue.add(arr[i]);
        }

        // The oldest device connected will be the element in the front index of the queue
        System.out.println("The Device ### "+queue.peek()+" ### connects to your bluetooth System");

    }

    public void closest_band_connects(String[] arr){

         Scanner sc = new Scanner(System.in);
        // The bandwidth of the blutooth devices and the bluetooth system of the car is initialized.
         int car_band = 2440;
         int[] band = new int[5];
         int[] diff = new int[5];
         for(int i = 0; i < 5; i++){
             System.out.println("Enter the band width of the device "+ arr[i]);
             Integer band_element = sc.nextInt();
             band[i] = band_element;
         }
         //Hash Map is declared to map the Device with its bandwidth.
         HashMap<String, Integer> hm = new HashMap<>();

         for(int i = 0; i < arr.length; i++){
             hm.put(arr[i], band[i]);
         }
         /* We calculate the difference in the bandwidth between the device and car system.
            The minimum of the difference is chosen*/
         for(int i = 0; i < 5; i++){
             diff[i] = Math.abs(car_band - hm.get(arr[i]));
         }
         //The minimum difference is taken and the corresponding device is connected.
         int min_diff = getMin(diff);
         int device_index = 0;
         for(int i = 0; i < 5; i++){
             if(diff[i] == min_diff){
                 device_index = i;
             }
         }
         System.out.println("The Device "+ arr[device_index]+" connects");
    }

    public void user_choice(String[] arr, int index){

        // The device from the user's choice gets connected.
        for(int i = 0; i < arr.length; i++){
            if(i == index){
                System.out.println("The device ### "+ arr[index]+ " ### is connected");
            }
        }
    }

    public int getMin(int[] arr){  // The minimum value of the given array is returned..
        int min = arr[0];
        for(int i = 0; i < 5;  i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    public boolean isEmpty(String[] arr){ // Checks whether the given array is empty
        boolean flag = true;
        for(int i = 0; i < 5; i++){
            if(arr[i] != null){
                flag = false;
            }
            else{
                flag = true;
            }

        }
        return flag;
    }
    public void insert(String str, String[] arr, int index){ // The given element is inserted in the given array
        for (int i = 0; i < 5; i++) {                        // In the given index...
            if (i == index) {
                if(i == 0){
                    arr[i] = str;
                    String temp = arr[i];
                    arr[i] = arr[arr.length-1];
                    arr[arr.length-1] = temp;

                    temp = arr[1];
                    arr[1] = arr[i];
                    arr[0] = temp;
                }
                else{
                    arr[i] = str;
                    String temp = arr[i];
                    arr[i] = arr[arr.length-1];
                    arr[arr.length-1] = temp;
                }

            }
        }
    }
    public void display(String[] arr){       // To Display the given array...
        for(int i = 0; i < 5 ;i++){
            System.out.println(arr[i] + " ");
        }
    }
    public void delete(int index, String[] arr){  // Delete the element from the array in the given index.
        int n = arr.length;
        for(int i = 0; i < n; i++){
            if(i == index){
                arr[i] = null;
            }
        }
    }

    public static void main(String[] args){
        BlueTooth bt = new BlueTooth();
        Scanner sc = new Scanner(System.in);
        String[] devices = new String[]{"d1", "d2", "d3", "d4", "d5"};
        boolean flag = true;

        while(flag){
            System.out.println("--------------------------Want to Insert a new device (1/0)------------------------");
            int insert = sc.nextInt();
            if(insert == 1) {
                System.out.println("Enter the device name that should be added: ");
                sc.useDelimiter("\n");
                String new_device = sc.next();
                if(bt.isEmpty(devices)){
                    int index = 0;
                    for(int i = 0 ; i < devices.length ; i++){

                        if(devices[i] == ""){
                            index = i;
                        }
                    }
                    bt.insert(new_device, devices,index);
                }
                else {
                    System.out.println("-----------Queue already full-----------\n" +
                            "Which device do you want to disconnect\n" +
                            "Available Devices are - \n");
                    bt.display(devices);
                    System.out.println("Enter the element's position: (1 - 5)");
                    int delete_choice = sc.nextInt();
                    bt.delete(delete_choice-1, devices);
                    bt.insert(new_device, devices, delete_choice-1);
                }

                System.out.println("Do you want to continue (1/0)");
                int exit_choice = sc.nextInt();
                if(exit_choice == 0){
                    flag = false;
                }
                else if(exit_choice == 1){
                    flag= true;
                }

            }
            else if(insert == 0){
                System.out.println("=======Welcome=======\n" +
                        "Five Devices trying to connect");
                bt.display(devices);
                System.out.println("----Choose the Test case:---- \n" +
                        "1: The latest device connects\n"+
                        "2: The oldest device connects\n" +
                        "3: The closest bandwidth connects\n" +
                        "4: Based on your Choice");
                int choice = sc.nextInt();
                if(choice == 1){
                    bt.latestConnects(devices);
                }
                else if (choice == 2) {
                    bt.oldestconnects(devices);
                }
                else if(choice == 3){
                    bt.closest_band_connects(devices);
                }
                else if(choice == 4){
                    System.out.println("Your Preference Please: ");
                    int connect_choice = sc.nextInt();
                    bt.user_choice(devices, connect_choice - 1);
                }

                System.out.println("Do you want to continue (1/0)");
                int exit_choice = sc.nextInt();
                if(exit_choice == 0){
                    flag = false;
                }
                else if(exit_choice == 1){
                    flag= true;
                }
            }


        }


    }
}
