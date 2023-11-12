import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;

public class PersonBuilder {

    protected String name = null;
    protected String surname = null;
    protected Integer age = null;
    protected String address = null;

    public PersonBuilder setName(String name) {
        this.name = name;
        System.out.println(name);
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Некорректное значение возраста");
        } else {
            this.age = age;
            return this;
        }
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            int memory = 0;
            for (Field field : fields) {
                if (field.getName().equals("name") && field.get(this) == null) {
                    memory += 1;
                } else if (field.getName().equals("surname") && field.get(this) == null) {
                    if (memory == 1) {
                        throw new IllegalStateException("Не заполнены обязательные поля: " + "name" + "; " + "surname");
                    } else {
                        throw new IllegalStateException("Не заполнены обязательные поля: " + "surname");
                    }
                } else if (memory == 1) {
                    throw new IllegalStateException("Не заполнены обязательные поля: " + "name");
                }
            }
            if (this.age == null && this.address == null) {
                return new Person(this.name, this.surname);
            } else if (this.age == null) {
                return new Person(this.name, this.surname, this.address);
            } else if (this.address == null) {
                return new Person(this.name, this.surname, this.age);
            } else {
                return new Person(this.name, this.surname, this.age, this.address);
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            return null;
        }
    }
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

