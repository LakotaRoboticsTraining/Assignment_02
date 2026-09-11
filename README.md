# Lesson 2: Primitives and Variables

Goal: Store values in variables, use =, +, >, <, and print with string concatenation.

Estimated Time: 30 - 45 minutes

Prerequisites: Lesson one - print statements

### You will learn

- Variables and primitive types (int, double, boolean, char) plus String
- Operators: = (assign), + (add or join text), > and < (compare)
- Concatenation for printing labels with values
### Declaring and assigning

```java
int teamNumber = 1038;
```

```java
int batteryPercent;
batteryPercent = 75;
```

`=` stores a value in a variable.

### Printing with concatenation

```java
System.out.println("Battery Percentage: " + batteryPercent);
```

`+` joins text and values into one String for printing. Include spaces inside the quotes when you need them.

#### Math Expressions

You can combine multiple operators in one line (expression). Java follows math rules (PEMDAS) to decide what to calculate first.

```java
int result = (10 + 5) * 2;
System.out.println("Expression result: " + result);
```

### Operators

#### = assignment

Stores a value. Example: cargoCount = 3;

#### + add numbers or join text

#### - subtraction

Subtracts numbers.

```java
int remainingBalls = 5 - 2;
System.out.println("Remaining: " + remainingBalls);
```

#### * multiplication

Multiplies numbers.

```java
int totalPoints = 3 * 5;
System.out.println("Total: " + totalPoints);
```

#### / division

Divides numbers.

```java
double average = 10 / 2;
System.out.println("Average: " + average);
```

#### > and < compare

Produce a boolean.

```java
boolean batteryOk = batteryPercent > 50;
```



```java
System.out.println("Battery OK: " + batteryOk);
```

#### `>=` and `<=` compare

Greater than or equal to, and less than or equal to.

```java
boolean atLimit = count >= 10;
boolean isLow = batteryPercent <= 20;
System.out.println("Battery critical: " + isLow);
```

## Try it yourself

Edit `Main.java`. Put **all** of your code inside `main`. Use variables and `System.out.println` with concatenation (`"label: " + value`).

### Challenge 1 - Match info

Declare variables for your team number, driver name, and match number. Print **three lines** that include these labels (values can be yours):

```
Team: 1038
Driver: Alex
Match: 3
```

### Challenge 2 - Drive settings

Print **two more lines** with a drive speed (number) and whether autonomous is on (`true` or `false`):

```
Drive speed: 0.75
Autonomous: true
```

### Challenge 3 - Cargo tracking

1. Set `int cargoCount = 0;` and print it with a label that includes `Cargo` and `0`.
2. Update with `cargoCount = cargoCount + 3;` and print again (label includes `Cargo` and `3`).
3. Set `boolean hasCargo = cargoCount > 0;` and print it (label includes `Has cargo` and `true`).

Example output for this challenge:

```
Cargo: 0
Cargo: 3
Has cargo: true
```

## Check your understanding

`"Score: " + 12` prints `Score: 12`. If battery is `40`, then `battery > 50` is `false`.
