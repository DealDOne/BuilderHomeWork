public class Main {
    public static void main(String[] args) {
        Person mom = new PersonBuilder()
                .setName("����")
                .setSurname("�����")
                .setAge(31)
                .setAddress("������")
                .build();
        Person son = mom.newChildBuilder()
                .setName("�������")
                .build();
        System.out.println("� " + mom + " ���� ���, " + son);

        try {
            // �� ������� ������������ �����
            new PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // �� ������� ������������ �����
            new PersonBuilder()
                    .setAddress("Some address")
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            // �� ������� ������������ �����
            new PersonBuilder()
                    .setName("Some Name")
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // �� ������� ������������ �����
            new PersonBuilder()
                    .setSurname("Some Surname")
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            // ������� ������������
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