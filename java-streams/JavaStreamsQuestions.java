import java.util.*;
import java.util.stream.Collectors;

/**
 * Find duplicate numbers
 * Find unique numbers
 * Find maximum/minimum
 * Find second-highest number
 * Count occurrences
 * Group employees by department
 * Count employees per department
 * Find the highest salary
 * Find the highest salary per department
 * Sort employees by salary
 * Get names of employees earning > 50K
 * Find the first non-repeated character
 * Find duplicate characters in a string
 * Partition employees based on salary
 */
class Questions {
  public void findDuplicates(List<Integer> nums) {
    Set<Integer> duplicate = new HashSet<>();
    List<Integer> res = nums.stream()
                            .filter(n -> !duplicate.add(n)) //.add returns false if n was already there
                            .distinct()
                            .toList();
    System.out.println(res);
  }

  public void findUnique(List<Integer> nums) {
    List<Integer> res = nums.stream()
                            .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                            .entrySet()
                            .stream()
                            .filter((k) -> k.getValue() == 1)
                            .map(Map.Entry::getKey)
                            .toList();

    System.out.println(res);
  }

  public void findMaxMin(List<Integer> nums) {
    Optional<Integer> max = nums.stream()
                                .max(Comparator.naturalOrder());
    System.out.println(max);

    Optional<Integer> min = nums.stream()
                                .min(Comparator.naturalOrder());
    System.out.println(min);
  }

  public void findSecondHighest(List<Integer> nums) {
    Optional<Integer> secMax = nums.stream()
                                    .distinct()
                                    .sorted(Comparator.reverseOrder())
                                    .limit(2)
                                    .min(Comparator.naturalOrder());
    System.out.println(secMax);

    //OR
    secMax = nums.stream()
                  .distinct()
                  .sorted(Comparator.reverseOrder())
                  .skip(1)
                  .findFirst();
    System.out.println(secMax);
  }

  public void countOccurrences(List<String> names) {
    Map<String, Long> map = names.stream()
                                  .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    System.out.println(map);
  }

  public void countEmployeesPerDept(List<Employees> emps) {
    Map<String, Long> map =  emps.stream()
                                  .collect(Collectors.groupingBy(Employees::getDepartment,
                                                                 Collectors.counting()));
    System.out.println(map);
  }

  public void findHighestSalary(List<Employees> emps) {
    Optional<Integer> highest =  emps.stream()
                                      .max(Comparator.comparing(Employees::getSalary))
                                      .map(Employees::getSalary);
    System.out.println(highest);
  }

  public void findHighestSalaryPerDept(List<Employees> emps) {
    Map<String, Optional<Integer>> map =
        emps.stream()
            .collect(Collectors.groupingBy(Employees::getDepartment,
                      Collectors.mapping(Employees::getSalary,
                                  Collectors.maxBy(Integer::compareTo))));

    System.out.println(map);
  }

  public void sortBySalary(List<Employees> emps) {
    List<Employees> ascOrder =  emps.stream()
                                    .sorted(Comparator.comparing(Employees::getSalary)).toList();
    System.out.println(ascOrder);
  }

  public void findEmployeeEarningAbove5k(List<Employees> emps) {
    List<String> topEmployees =  emps.stream()
                                      .filter(e -> e.getSalary() > 5000)
                                      .map(Employees::getName)
                                      .toList();
    System.out.println(topEmployees);
  }

  public void firstNonRepeatingChar(String str) {
    Optional<Character> ch = str.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.groupingBy(c -> c,
                                    LinkedHashMap::new,
                                    Collectors.counting()))
                                .entrySet()
                                .stream()
                                .filter((k) -> k.getValue() == 1)
                                .map(Map.Entry::getKey)
                                .findFirst();
    System.out.println(ch);
  }

  public void findDuplicateChars(String str) {
    List<Character> ch = str.chars()
                            .mapToObj(c -> (char) c)
                            .collect(Collectors.groupingBy(c -> c,
                                LinkedHashMap::new,
                                Collectors.counting()))
                            .entrySet()
                            .stream()
                            .filter((k) -> k.getValue() > 1)
                            .map(Map.Entry::getKey)
                            .toList();
    System.out.println(ch);
  }

  public void partitionBySalary(List<Employees> employees) {
    Map<Boolean, List<Employees>> map = employees.stream()
        .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));

    List<Employees> highSalary = map.get(true);
    List<Employees> lowSalary = map.get(false);
    System.out.println("above 5k : " + highSalary);
    System.out.println("less than or equal to 5k: " + lowSalary);
  }
}

public class JavaStreamsQuestions {

  public static void main(String[] args) {
    List<Integer> num1 = Arrays.asList(1,2,3,4,5);
    List<Integer> num3 = Arrays.asList(1,1,1,1,2,2,3,3,3,4,5);

    List<String> names = Arrays.asList("alice", "alice", "bob", "charlie","charlie","charlie");
    List<Employees> employees = Arrays.asList(
        new Employees("alice", "IT", 100000),
        new Employees("bob", "Arts", 2000),
        new Employees("charlie", "IT", 50000),
        new Employees("jay", "Arts", 40000));

    String str1 = "swiss";

    Questions questions =  new Questions();
    questions.findDuplicates(num3);
    questions.findUnique(num3);
    questions.findMaxMin(num1);
    questions.findSecondHighest(num1);
    questions.countOccurrences(names);
    questions.countEmployeesPerDept(employees);
    questions.findHighestSalary(employees);
    questions.findHighestSalaryPerDept(employees);
    questions.sortBySalary(employees);
    questions.findEmployeeEarningAbove5k(employees);
    questions.partitionBySalary(employees);
    questions.firstNonRepeatingChar(str1);
    questions.findDuplicateChars(str1);
  }
}
