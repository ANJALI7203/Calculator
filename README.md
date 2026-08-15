# Java CLI Calculator

A command-line calculator built in Java that evaluates full mathematical expressions — not just single operations. Supports operator precedence, associativity, nested parentheses, and robust input validation, built incrementally across four stages with no external parsing libraries.

## Features

- Evaluates full expression strings like `2 + 3 * ( 4 - 1 )`, not just `a op b`
- Correct operator precedence (`*`, `/` before `+`, `-`)
- Correct left-associativity for chained operators (e.g. `10 - 4 - 3` = `3`, not `9`)
- Nested parentheses support, e.g. `( 2 + ( 3 * 4 ) )`
- Input validation via regex — rejects malformed expressions (missing operands, invalid operators, unbalanced parentheses, empty brackets) with clear error messages instead of crashing
- Divide-by-zero handling
- Supports negative numbers and decimals
- Menu-driven loop — evaluate multiple expressions in one run

## Example usage

```
Choose from the menu
1. Calculate Expression
2. Exit
1
Enter the expression to evaluate
( 2 + 3 ) * 4
Expression: ( 2 + 3 ) * 4
Solution: 20.0
```

```
Enter the expression to evaluate
10 / 0
Error caught: Invalid Expression: Cannot Divide a number by 0.
```

## Project structure

This project was built incrementally, with each stage adding real functionality on top of the last:

| Stage | What it adds |
|-------|---------------|
| `Stage_1` | Basic two-operand calculator, then upgraded to full expression parsing with correct operator precedence using a two-pass array approach |
| `Stage_2` | Refactored into OOP — logic separated from `main` into a dedicated `expression` class with single-purpose methods (`add`, `sub`, `mul`, `div`) |
| `Stage_3` | Proper exception handling and input validation — regex-based checks catch malformed expressions before evaluation, replacing abrupt program termination with graceful error messages |
| `Stage_4` | Full parentheses support (including nested) via a stack-based shunting-yard-style algorithm, plus expanded validation for unbalanced/empty brackets |

## How to run

```bash
javac Stage_4/S_4.java
java Stage_4.S_4
```

Or open the project in IntelliJ IDEA and run `S_4.java` directly.

**Note on input format:** expressions must have spaces between every token, including parentheses — e.g. `( 2 + 3 ) * 4`, not `(2+3)*4`.

## Known limitations

- No support for scientific operations (sqrt, power, etc.) yet
- Requires spaced tokens (no support for compact notation like `2+3`)
- No unit tests yet

## Tech used

Core Java — OOP, `Stack`, `ArrayList`, regex (`Pattern`/`String.matches`), exception handling. No external libraries.