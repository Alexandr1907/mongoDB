import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Scanner;

public class ConsoleReader {
    private static final Scanner console = new Scanner(System.in);


    public static void start() {
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("\nВыберите действие:\nc  - create\nr  - read\nra - readAll\nu  - update\nd  - delete\ne  - exit\n");
            String inp = console.nextLine();

            switch (inp) {
                case ("c") -> createNewUser();
                case ("ra") -> loadAllUser();
                case ("r") -> loadUserById();
                case ("u") -> updateUser();
                case ("d") -> deleteUserById();
                case ("e") -> {
                    System.out.println("Завершение программы");
                    isContinue = false;
                    return;
                }
            }
        }
    }

    public static void createNewUser() {
        System.out.println("Введите имя пользователя");
        String name = console.nextLine();
        System.out.println("Введите возраст пользователя");
        int age = console.nextInt();
        console.skip("\n");
        boolean isSuccess = UserService.create(new User(name, age));
        if (isSuccess) {
            System.out.println("Новый пользователь успешно сохранен");
        } else {
            System.out.println("При сохранении произошла ошибка");
        }
    }

    public static void loadAllUser(){
        UserService.getAll().forEach(System.out::println);
    }

    public static void loadUserById() {
        System.out.println("Введите ид пользователя");
        String hexId = console.nextLine();
        System.out.println(UserService.getById(hexId));
    }

    public static void updateUser(){
        System.out.println("Введите ид пользователя");
        String hexId = console.nextLine();
        System.out.println("Введите имя пользователя");
        String name = console.nextLine();
        System.out.println("Введите возраст пользователя");
        int age = console.nextInt();
        console.skip("\n");

        UserService.update(new User(new ObjectId(hexId), name, age));
    }

    public static void deleteUserById() {
        System.out.println("Введите ид пользователя");
        String hexId = console.nextLine();
        boolean isSuccess = UserService.delete(UserService.getById(hexId));
        if (isSuccess) {
            System.out.println("Пользователь успешно удален");
        } else {
            System.out.println("При удалении пользователя произошла ошибка");
        }
    }
}
