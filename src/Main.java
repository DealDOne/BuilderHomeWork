public class Main {
    public static void main(String[] args) {
        Person mom = new PersonBuilder()
                .setName("Анна")
                .setSurname("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();
        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);

        try {
            // Не хватает обязательных полей
            new PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Не хватает обязательных полей
            new PersonBuilder()
                    .setAddress("Some address")
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            // Не хватает обязательных полей
            new PersonBuilder()
                    .setName("Some Name")
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Не хватает обязательных полей
            new PersonBuilder()
                    .setSurname("Some Surname")
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            // Возраст недопустимый
            new PersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println(mom.getAge());
        mom.happyBirthday();
        System.out.println(mom.getAge());
        System.out.println(son.hasAge());
        son.happyBirthday();
        System.out.println(son.getAge());

    }
}