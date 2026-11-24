# Java Exception Interview Answers

## Basic exception handling

1. An exception is an object describing an abnormal condition that interrupts the normal flow of a Java program.
2. Exception handling lets a program report or recover from failures and release resources instead of stopping unexpectedly.
3. An `Exception` represents a condition an application may be able to handle; an `Error` represents a serious JVM or environment problem that applications usually should not try to recover from.
4. A compile-time error is rejected by the compiler before execution; a runtime error occurs while the program is running, often as an exception or error.
5. `Throwable` is the root; its two main branches are `Exception` and `Error`. `RuntimeException` is a subclass of `Exception`; checked exceptions are `Exception` subclasses other than `RuntimeException` and its subclasses.
6. Checked exceptions must be caught or declared; unchecked exceptions (`RuntimeException` and `Error` subclasses) have no such compiler requirement.
7. Examples include `IOException`, `SQLException`, `ClassNotFoundException`, and `InterruptedException`.
8. Examples include `NullPointerException`, `IllegalArgumentException`, `ArithmeticException`, and `IndexOutOfBoundsException`.
9. `Exception` is the broader application-exception class and includes checked exceptions. `RuntimeException` is its unchecked subclass, commonly used for invalid arguments or state.
10. `Throwable` is the parent class of both exceptions and errors.
11. Yes. A method can declare a checked exception with `throws` and let its caller handle it; unchecked exceptions can also propagate without a declaration.
12. It propagates up the call stack until a matching handler is found. If it remains uncaught, the current thread terminates and its uncaught-exception handler is notified.
13. Yes. A `try` may have multiple `catch` blocks, and the first compatible one handles the exception.
14. Yes, when it has a `finally` block or is a try-with-resources statement; a plain `try` with neither `catch` nor `finally` is invalid.
15. No. A `catch` block must immediately follow a `try` block.

## try, catch, and finally

16. `try` encloses code that may fail, `catch` handles selected exception types, and `finally` performs cleanup as control leaves the statement.
17. `final` is a modifier, `finally` is a cleanup block, and `finalize()` is a legacy garbage-collection hook that is deprecated and unreliable; newer JDKs deprecate it for removal, so resource cleanup should use `AutoCloseable` and try-with-resources.
18. It runs on normal completion and ordinary abrupt completion such as a thrown exception or return, but not if the JVM is forcibly stopped or crashes.
19. It may not run if `System.exit` or `Runtime.halt` ends the JVM, the process is killed, the JVM crashes, or execution never leaves the `try` block.
20. No. Each `try` statement can have at most one `finally` block; nested `try` statements can each have their own.
21. It is legal, but should be avoided because its return overrides a pending return and can suppress an exception from the `try` or `catch` block.
22. The exception from `finally` propagates and can replace an exception or return already in progress, masking the original failure.
23. Yes. A `try` statement can appear inside another `try`, `catch`, or `finally` block.
24. Nested try-catch means placing one `try`/`catch` inside another so an inner operation can be handled locally while other failures propagate to the outer handler.
25. A new exception thrown in a `catch` block propagates to an enclosing handler or the caller; it is not handled by another `catch` attached to the same `try`.
26. Yes. A single `try` may be followed by multiple `catch` blocks.
27. Put more specific exception classes before their parent classes; the broadest handler belongs last.
28. If a parent class is caught first, it also catches its subclasses, making later child catches unreachable and causing a compile-time error.
29. Multi-catch, available since Java 7, catches unrelated exception types in one clause, for example `catch (IOException | SQLException e)`.
30. Usually no. If `System.exit(0)` successfully initiates JVM shutdown, the current `finally` is not guaranteed to run, though registered shutdown hooks may run.

## throw and throws

31. `throw` raises an exception object in executable code; `throws` declares possible exception types in a method or constructor signature.
32. Yes. Use `throw` with an existing exception or create one, such as `throw new IllegalArgumentException("Invalid value")`.
33. Yes. A method can declare multiple exception types separated by commas in its `throws` clause.
34. Yes. A method may use `throw` in its body and declare the checked exception type with `throws` in its signature.
35. Yes. The method must catch the checked exception or declare it with `throws` so callers can handle it.
36. Yes. An unchecked exception may be thrown without being declared or caught.
37. Yes. A method may declare a checked exception as part of its contract even if its current implementation does not throw one.
38. Yes. `main` may declare `throws Exception`, though handling expected failures in the program usually gives users clearer messages.
39. The method completes abruptly; Java searches its callers for a matching handler, and the exception keeps propagating if none is found.
40. Yes. A catch block can rethrow the same exception or wrap it in another exception while preserving it as the cause.

## Custom exceptions

41. A user-defined exception is a class a program creates to represent an application-specific failure or condition.
42. Extend `Exception`, add constructors (usually calling `super(message)`), then require callers to catch it or declare it.
43. Extend `RuntimeException` and add constructors; callers are not required by the compiler to catch or declare it.
44. Extending `Exception` creates a checked exception; extending `RuntimeException` creates an unchecked exception.
45. Create one when it names a meaningful domain condition that standard exceptions do not express clearly and callers need a distinct response.
46. Yes. It can define constructors for a message, a cause, or both, and pass them to the superclass.
47. Pass the message to the superclass constructor, such as `super(message)`, then callers can retrieve it with `getMessage()`.
48. Yes. An application can define multiple custom exception classes for distinct conditions.

## Advanced exception handling

49. Exception propagation is the movement of an uncaught exception up the calling-method chain until a matching handler catches it or the thread ends.
50. Exception chaining wraps a lower-level exception in a higher-level one while storing the original as its cause, preserving the reason for the failure.
51. `getMessage()` returns the exception's detail message, possibly `null`; `toString()` returns the class name and, when present, the localized message.
52. A stack trace is the recorded sequence of method-call frames associated with a throwable, often including class, file, and line information.
53. `printStackTrace()` writes the throwable and its stack frames to standard error by default; it is useful during debugging but production logs should include appropriate context.
54. Try-with-resources is a `try (resource ...)` statement that automatically closes resources implementing `AutoCloseable` when the block exits.
55. It closes resources on both normal and exceptional exits, in reverse order of creation, and preserves a primary failure while recording close failures as suppressed exceptions.
56. `AutoCloseable` is an interface with a `close()` method that try-with-resources calls automatically when leaving the block.
57. If the body already threw, a close-time exception is recorded as suppressed while the body exception remains primary; if the body succeeded, the close-time exception propagates.
58. Suppressed exceptions are secondary failures retained on a primary throwable, commonly close failures during try-with-resources; retrieve them with `getSuppressed()`.
59. `IOException` is the general checked exception for I/O failures; `FileNotFoundException` is a subclass used when a file cannot be opened because it is missing or otherwise unavailable.
60. Catch specific exceptions, handle or propagate them meaningfully, avoid empty catches, preserve causes, use try-with-resources, validate inputs, and avoid using exceptions for ordinary control flow.

## References

- [Java Language Specification, Chapter 11: Exceptions](https://docs.oracle.com/javase/specs/jls/se17/html/jls-11.html)
- [Java 17 `Throwable` API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Throwable.html)
- [Java 17 `AutoCloseable` API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/AutoCloseable.html)
- [Java 17 `Object.finalize()` API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#finalize())
