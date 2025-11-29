package com.pwr.lab03.kayak.app;


import com.pwr.lab03.kayak.service.EmployeeService;
import com.pwr.lab03.kayak.model.Task;
import com.pwr.lab03.kayak.exception.DataException;


import java.util.List;
import java.util.Scanner;


public class EmployeeApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final EmployeeService service = new EmployeeService();


    public static void main(String[] args) {
        while(true) {
            System.out.println("1. View open tasks");
            System.out.println("2. Update reservation status");
            System.out.println("3. Close task");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(sc.nextLine());


            try {
                switch(choice) {
                    case 1:
                        List<Task> tasks = service.viewTasks();
                        tasks.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Reservation ID: ");
                        int resId = Integer.parseInt(sc.nextLine());
                        System.out.print("New status: ");
                        String status = sc.nextLine();
                        service.updateReservationStatus(resId, status);
                        System.out.println("Reservation status updated.");
                        break;
                    case 3:
                        System.out.print("Task ID: ");
                        int taskId = Integer.parseInt(sc.nextLine());
                        service.closeTask(taskId);
                        System.out.println("Task closed.");
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (DataException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}