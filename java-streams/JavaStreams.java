import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solutions {
  public void basicOperations() {
    //primitive array
    int[] arr = {1,2,3,4};
    //object array
    Integer[] objectArr = {1,2,3,4};

    IntStream result1  = Arrays.stream(arr);
    result1.forEach(System.out::println);

    Stream<Integer> result2 = Stream.of(objectArr);
    result2.forEach(System.out::println);

    //List
    List<Integer> numbers =  Arrays.asList(1,2,3,4,5);

    numbers.stream().forEach(System.out::println);
  }

  public void intermediateOperations() {
    List<Integer> nums =  Arrays.asList(1,2,3,4,5,6);

    //filter
    //even numbers
    nums.stream()
        .filter(n -> n%2 == 0)
        .forEach(System.out::println);

    List<String> names = Arrays.asList("alice", "bob", "charlie");
    //map
    //capitalize
    names.stream()
          .map(name-> name.toUpperCase())
          .forEach(System.out::println);

    System.out.println(nums); //remains same
    System.out.println(names); //remains same

    //reduce
    Optional res1 = nums.stream().reduce(Integer::sum);
    //or
    Optional res2 = nums.stream().reduce((a,b)-> a+b);
    //or
    Integer res3 = nums.stream().reduce(0, Integer::sum);
    //or
    Integer res4 = nums.stream().reduce(0, (a,b)-> a+b);
    /*
    0 is the identity or starting value. Start at 0 and keep adding each number to the running result
    a     b
    0  +  1  → 1
    1  +  2  → 3
    3  +  3  → 6
    6  +  4  → 10
    */

    System.out.println(res1);
    System.out.println(res2);
    System.out.println(res3);
    System.out.println(res4);

    //flatmap
    List<List<String>> listOfList = Arrays.asList(
        Arrays.asList("a", "b"),
        Arrays.asList("c", "d"),
        Arrays.asList("e", "f")
    );
    listOfList.stream()
              .flatMap(Collection::stream)
              .map(String::toUpperCase)
              .forEach(System.out::println);

    //peek
    List<Integer> res5 = nums.stream()
                              .peek(System.out::println)
                              .map(n -> n*n)
                              .toList();
    System.out.println(res5);

    //distinct
    List<Integer> arr = Arrays.asList(1,1,2,3,4,4);
    arr.stream()
        .distinct()
        .forEach(System.out::println);

    //sorted
    arr.stream()
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);

    //sort based on length of string
    names.stream()
          .sorted(Comparator.comparingInt(String::length))
          .forEach(System.out::println);

    //skip
    arr.stream()
        .skip(0)
        .forEach(System.out::println);

    //limit
    arr.stream()
        .limit(3)
        .forEach(System.out::println);
  }

  public void terminalOperations() {
    List<Integer> nums =  Arrays.asList(1,2,3,4,5,6);

    List<Integer> res1 = nums.stream()
                              .filter(n -> n%2 == 0)
                              .map(n -> n*n)
                              .toList();
    System.out.println(res1);

    //groupingBy
    List<Employees> emps = Arrays.asList(
        new Employees("alice", "IT", 100000),
        new Employees("bob", "Arts", 20000),
        new Employees("charlie", "IT", 50000),
        new Employees("jay", "Arts", 40000));

    Map<String, List<Employees>> res2 = emps.stream()
                                            .collect(Collectors.groupingBy(Employees::getDepartment));

    res2.forEach((dept, employee) -> {
      System.out.println("Department:"+ dept);
      employee.forEach(System.out::println);
    });

    //partitionBy
    Map<Boolean, List<Employees>> res3 = emps.stream()
                                            .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 50000));
    res3.forEach((bool, employee) -> {
      System.out.println("Greater than 50000:"+ bool);
      employee.forEach(System.out::println);
    });

    //min, max
    Optional<Integer> res4 = nums.stream()
        .min(Comparator.naturalOrder());
    Optional<Integer> res5 = nums.stream()
        .max(Comparator.naturalOrder());
    System.out.println(res4);
    System.out.println(res5);

    //findFirst
    Optional res6 = nums.stream()
                        .filter(n -> n % 2==0)
                        .findFirst();
    System.out.println(res6);

    //anyMatch
    Boolean res7 = nums.stream()
                        .anyMatch(n -> n % 2==0);
    System.out.println(res7);

    //count
    long count = nums.stream().count();
    System.out.println(count);

    //sum
    int sum = nums.stream().mapToInt(Integer::intValue).sum();
    System.out.println(sum);
  }
}

class Employees {
  String name;
  String department;
  int salary;

  public Employees(String name, String department, int salary) {
    this.name = name;
    this.department = department;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Employee {" +
        " name:" +name +
        " dept:" +department+
        " salary:" +salary +"}";
  }
}

public class JavaStreams {
  public static void main(String[] args) {
    Solutions solutions = new Solutions();
    solutions.basicOperations();
    solutions.intermediateOperations();
    solutions.terminalOperations();
  }
}
