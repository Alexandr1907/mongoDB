import java.util.List;
import java.util.Scanner;

public class ConsoleReader {
    private static final Scanner console = new Scanner(System.in);


    public static void start() {
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("\nВыберите действие:\nc  - create\nr  - read\nra - readAll\nu  - update\nd  - delete\ne  - exit\n");
            String inp = console.nextLine();

            try {
                switch (inp) {
                    case "c" :
                        createNewUser();
                        break;
                    case "ra" :
                        loadAllUser();
                        break;
                    case "r" :
                        loadUserById();
                        break;
                    case "u" :
                        updateUser();
                        break;
                    case "d" :
                        deleteUserById();
                        break;
                    case "e" :
                        System.out.println("Завершение программы");
                        isContinue = false;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    public static void loadAllUser() {
        List<User> users = UserService.getAll();
        if (users.isEmpty()){
            System.out.println("Нет ни одного пользователя");
        } else {
            UserService.getAll().forEach(System.out::println);
        }
    }

    public static void loadUserById() {
        System.out.println("Введите ид пользователя");
        String hexId = console.nextLine();
        System.out.println(UserService.getById(hexId));
    }

    public static void updateUser() {
        List<User> users = UserService.getAll();
        System.out.println("Введите индекс редактируемого пользователя");
        for (int i = 1; i <= users.size(); i++) {
            System.out.println(i + ". " + users.get(i - 1));
        }
        int index = console.nextInt();
        console.skip("\n");
        if (index < 1 || index > users.size()) {
            System.out.println("введен некорректный индекс");
            return;
        }
        User editableUser = users.get(index - 1);
        System.out.println("Введите имя пользователя");
        String name = console.nextLine();
        editableUser.setName(name);
        System.out.println("Введите возраст пользователя");
        int age = console.nextInt();
        console.skip("\n");
        editableUser.setAge(age);

        boolean isSuccess = UserService.update(editableUser);
        if (isSuccess) {
            System.out.println("Пользователь успешно изменен");
        } else {
            System.out.println("При сохранении изменений произошла ошибка");
        }
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
