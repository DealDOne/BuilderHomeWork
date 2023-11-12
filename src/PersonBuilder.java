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
}

