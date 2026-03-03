package zyz.steve.immutable;

import java.util.List;
import java.util.Objects;

public final class Person {
    private final String name;
    private final int age;
    private final List<String> addresses;
    // 2. 通过构造函数一次性赋值，内部做 defensive copy
    private Person(String name, int age, List<String> addresses) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.age = age;
        if (addresses == null) {
            this.addresses = List.of(); // 空不可变列表（Java 9+）
        } else {
            // 防御式拷贝 + 包装成不可变
            this.addresses = List.copyOf(addresses); // Java 10+，内部会做 defensive copy
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private int age;
        private List<String> addresses;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder addresses(List<String> addresses) {
            this.addresses = addresses;
            return this;
        }

        public Person build() {
            return new Person(name, age, addresses);
        }
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    /**
     * 返回不可变视图，防止外部修改内部集合
     */
    public List<String> getAddresses() {
        return addresses; // 已经是不可变 List.copyOf 得到的
    }

    public Builder toBuilder(){
        return  new Builder().name(this.name)
                .addresses(this.addresses)
                .age(this.age);
    }

    public static void main(String[] args) {
        Person p = Person.builder()
                .name("Steve")
                .age(30)
                .addresses(List.of("Address 1", "Address 2"))
                .build();
        System.out.println("Name: " + p.getName());
        Person p2 = p.toBuilder().name("hello2").build();
        p2.getAddresses().add("add3");

        System.out.println(p2.name);
        System.out.println(p2.age);

        System.out.println(p.getAddresses());
        System.out.println(p2.getAddresses());
    }

}
