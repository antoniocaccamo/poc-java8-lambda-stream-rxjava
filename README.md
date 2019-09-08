# java8 lambda stream rxjava


With the Java 8 release, Java
provided supports for functional programming, new JavaScript engine, new APIs
for date time manipulation, new streaming API, etc.

New Features:

- Lambda expression

    Adds functional processing capability to Java.

- Method references
    
    Referencing functions by their names instead of invoking them directly. 
    
    Using functions as parameter.

- Default method

    Interface to have default method implementation.

- New tools

    New compiler tools and utilities are added like ‘jdeps’ to figure out dependencies.

- Stream API

    New stream API to facilitate pipeline processing.

- Date Time API

    Improved date time API.

- Optional

    Emphasis on best practices to handle null values properly.

- Nashorn, JavaScript Engine

    A Java-based engine to execute JavaScript code.


## Lambda expression

Lambda expressions are introduced in Java 8 and are touted to be the biggest feature of Java 8. 

Lambda expression facilitates functional programming, and simplifies the development a lot.

### Syntax

A lambda expression is characterized by the following syntax:

    parameter  -> expression body

Following are the important characteristics of a lambda expression:

- Optional type declaration

    No need to declare the type of a parameter
    The compiler can inference the same from the value of the parameter.

- Optional parenthesis around parameter

    No need to declare a single parameter in parenthesis. For multiple parameters, parentheses are required.

- Optional curly braces

    No need to use curly braces in expression body if the body contains a single statement.

- Optional return keyword

    The compiler automatically returns the value if the body has a single expression to return the value. Curly braces are required to indicate that expression returns a value.

Following are the important **points to be considered**:

- Lambda expressions are used primarily to define inline implementation of a functional interface, i.e., an interface with a single method only

- Lambda expression eliminates the need of anonymous class and gives a very simple yet powerful functional programming capability to Java.


## Method References
Method references help to point to methods by their names and is described using "::" symbol. 

A  method reference can be used to point the following types of methods:

- Static methods

- Instance methods

- Contructors using the `new` operator (`TreeSet::new`)

## Functional Interfaces
Functional interfaces have a single functionality to exhibit. 

For example, a Comparable interface with a single method ‘compareTo’ is used for comparison purpose. 

Java 8 has defined a lot of functional interfaces to be used extensively in
lambda expressions.

Following is the list of functional interfaces defined in
java.util.Function package:

|#|Interface | Description|
|------|----------|------------|
|1|`BiConsumer<T,U>`| Represents an operation that accepts two input arguments, and returns no result.|
|2|`BiFunction<T,U,R>`| Represents a function that accepts two arguments and produces a result.|
|3|`BinaryOperator<T>`| Represents an operation upon two operands of the same type, producing a result of the same type as the operands.|
|4|`BiPredicate<T,U>`| Represents a predicate (Boolean-valued function) of two arguments.|
|5|`BooleanSupplier` | Represents a supplier of Boolean-valued results.|
|6|`Consumer<T>`| Represents an operation that accepts a single input argument and returns no result.|
|7|`DoubleBinaryOperator ` |  Represents an operation upon two double-valued operands and producing a double-valued result.|
|8|`DoubleConsumer` |  Represents an operation that accepts a single double-valued argument and returns no result.|
|9|`DoubleFunction<R>` |  Represents a function that accepts a double-valued argument and produces a result.|
|10|`DoublePredicate ` |  Represents a predicate (Boolean-valued function) of one double-valued argument.|
|11|`DoubleSupplier` |  Represents a supplier of double-valued results.|
|12|`DoubleToIntFunction ` |  Represents a function that accepts a double-valued argument and produces an int-valued result.|
|13|`DoubleToLongFunction ` |  Represents a function that accepts a double-valued argument and produces a long-valued result.|
|14|`DoubleUnaryOperator ` |  Represents an operation on a single double-valued operand that produces a double-valued result.|
|15|`Function<T,R>` |  Representsepresents a function that accepts one argument and produces a result.|
|16|`IntBinaryOperator ` |  Represents an operation upon two int-valued operands and produces an int-valued result.|
|17|`IntConsumer` |  Represents an operation that accepts a single int-valued argument and returns no result.|
|18|`IntFunction<R>` |  Represents a function that accepts an int-valued argument and produces a result.|
|19|`IntPredicate` |  Represents a predicate (Boolean-valued function) of one int-valued argument.|
|20|`IntSupplier` |  Represents a supplier of int-valued results.|
|21|`IntToDoubleFunction` |  Represents a function that accepts an int-valued argument and produces a double-valued result.|
|22|`IntToLongFunction` |  Represents a function that accepts an int-valued argument and produces a long-valued result.|
|23|`IntUnaryOperator` |  Represents an operation on a single int-valued operand that produces an int-valued result.|
|24|`LongBinaryOperator` |  Represents an operation upon two long-valued operands and produces a long-valued result.|
|25|`LongConsumer ` |  Represents an operation that accepts a single long-valued argument and returns no result.|
|26|`LongFunction<R>` |  Represents a function that accepts a long-valued argument and produces a result.|
|27|`LongPredicate ` |  Represents a predicate (Boolean-valued function) of one long-valued argument.|
|28|`LongSupplier` |  Represents a supplier of long-valued results.|
|29|`LongToDoubleFunction` |  Represents a function that accepts a long-valued argument and produces a double-valued result.|
|30|`LongToIntFunction ` |  Represents a function that accepts a long-valued argument and produces an int-valued result.|
|31|`LongUnaryOperator ` |  Represents an operation on a single long-valued operand that produces a long-valued result. |
|32|`ObjDoubleConsumer<T> ` |  Represents an operation that accepts an object-valued and a double-valued argument, and returns no result.|
|33|`ObjIntConsumer<T>` |  Represents an operation that accepts an object-valued and an int-valued argument, and returns no result.|
|34|`ObjLongConsumer<T>` |  Represents an operation that accepts an object-valued and a long-valued argument, and returns no result.|
|35|`Predicate<T>` |  Represents a predicate (Boolean-valued function) of one argument.|
|36|`Supplier<T>` |  Represents a supplier of results.|
|37|`ToDoubleBiFunction<T,U>` |  Represents a function that accepts two arguments and produces a double-valued result.|
|38|`ToDoubleFunction<T>` |  Represents a function that produces a double-valued result.|
|39|`ToIntBiFunction<T,U>` |  Represents a function that accepts two arguments and produces an int-valued result.|
|40|`ToIntFunction<T>` |  Represents a function that produces an int-valued result.|
|41|`ToLongBiFunction<T,U>` |  Represents a function that accepts two arguments and produces a long-valued result.|
|42|`ToLongFunction<T>` |  Represents a function that produces a long-valued result.|
|43|`UnaryOperator<T>` |  Represents an operation on a single operand that produces a result of the same type as its operand.|

## Default Methods
Java 8 introduces a new concept of default method implementation in interfaces. 

This capability is added for backward compatibility so that old interfaces can be used to leverage the lambda expression capability of Java 8.

For example, `List` or `Collection` interfaces do not have `forEach` method declaration. 

Thus, adding such method will simply break the collection framework implementations. 

Java 8 introduces default method so that List/Collection interface can have a default implementation of `forEach` method, and the class implementing these interfaces need not implement the same.

An interface can also have static helper methods from Java 8 onwards.

```java
public interface Vehicle {

   default void print() {
      System.out.println("I am a vehicle!");
   }

   static void blowHorn() {
      System.out.println("Blowing horn!!!");
   }
}

public class Java8Tester {

   public static void main(String args[]) {
      Vehicle car = new Car();
      car.print();
      Vehicle.blowHorn();
   }
}
```

## Streams

Stream is a new abstract layer introduced in Java 8. Using stream, you can process data in a declarative way similar to SQL statements. For example, consider the following SQL statement.

```sql
SELECT max(salary), employee_id, employee_name FROM Employee
```
The above SQL expression automatically returns the maximum salaried employee's details, without doing any computation on the developer's end. 

Using collections framework in Java, a developer has to use loops and make repeated checks. Another concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code processing that can be pretty error-prone.

To resolve such issues, Java 8 introduced the concept of stream that lets the developer to process data declaratively and leverage multicore architecture without the need to write any specific code for it.

### What is Stream?
Stream represents a sequence of objects from a source, which supports aggregate operations. Following are the characteristics of a Stream −

- Sequence of elements − A stream provides a set of elements of specific type in a sequential manner. A stream gets/computes elements on demand. It never stores the elements.

- Source − Stream takes Collections, Arrays, or I/O resources as input source.

- Aggregate operations − Stream supports aggregate operations like filter, map, limit, reduce, find, match, and so on.

- Pipelining − Most of the stream operations return stream itself so that their result can be pipelined. These operations are called intermediate operations and their function is to take input, process them, and return output to the target. collect() method is a terminal operation which is normally present at the end of the pipelining operation to mark the end of the stream.

- Automatic iterations − Stream operations do the iterations internally over the source elements provided, in contrast to Collections where explicit iteration is required.

### Generating Streams
With Java 8, Collection interface has two methods to generate a Stream.

- `stream()`
    
    Returns a sequential stream considering collection as its source.

- `parallelStream()`

    Returns a parallel Stream considering collection as its source.
```java
List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
List<String> filtered = strings
            .stream()
            .filter(string -> !string.isEmpty())
            .collect(Collectors.toList())
;
```
### forEach
Stream has provided a new method ‘forEach’ to iterate each element of the stream. The following code segment shows how to print 10 random numbers using forEach.
```java
Random random = new Random();
random.ints().limit(10).forEach(System.out::println);
```
### map
The ‘map’ method is used to map each element to its corresponding result. The following code segment prints unique squares of numbers using map.
```java
List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

//get list of unique squares
List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
```
### filter
The ‘filter’ method is used to eliminate elements based on a criteria. The following code segment prints a count of empty strings using filter.

```java
List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

//get count of empty string
int count = strings.stream().filter(string -> string.isEmpty()).count();
```
### limit
The ‘limit’ method is used to reduce the size of the stream. The following code segment shows how to print 10 random numbers using limit.
```java
Random random = new Random();
random.ints().limit(10).forEach(System.out::println);
```
### sorted
The ‘sorted’ method is used to sort the stream. The following code segment shows how to print 10 random numbers in a sorted order.

```java
Random random = new Random();
random.ints().limit(10).sorted().forEach(System.out::println);
```
### Parallel Processing
parallelStream is the alternative of stream for parallel processing. Take a look at the following code segment that prints a count of empty strings using parallelStream.

    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

    //get count of empty string
    long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
It is very easy to switch between sequential and parallel streams.

### Collectors
Collectors are used to combine the result of processing on the elements of a stream. Collectors can be used to return a list or a string.

    List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

    System.out.println("Filtered List: " + filtered);
    String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
    System.out.println("Merged String: " + mergedString);
### Statistics
With Java 8, statistics collectors are introduced to calculate all statistics when stream processing is being done.
```java
    List numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());
```

## Optional Class
Optional is a container object used to contain not-null objects. Optional object is used to represent null with absent value. 

This class has various utility methods to facilitate code to handle values as ‘available’ or ‘not available’ instead of checking null values. It is introduced in Java 8 and is similar to what Optional is in Guava.

## New Date/Time API

Following are some of the important classes introduced in `java.time` package.

- `LocalDate/Time`

    Simplified date-time API with no complexity of timezone handling.

 - `ZonedDate/Time`
 
    Specialized date-time API to deal with various timezones.

and two specialized classes are introduced to deal with the time differences.

- `Period`
    
    It deals with date based amount of time.

- `Duration`

     It deals with time based amount of time
