# Student Result Portal Reflection Answers

1. **When should a condition be checked, and when should an exception be caught?** For an expected missing email, checking `email == null` handles the normal condition directly. The other lab methods catch the specific exceptions they are required to recover from and return the requested fallback values.
2. **What is the difference between `throw` and `throws`?** `throw` raises one exception object in a method body; `throws` declares exception types in a method signature.
3. **Why must `AttendanceShortageException` be handled but `InvalidMarksException` does not have to be?** `AttendanceShortageException` extends `Exception`, so it is checked and must be caught or declared. `InvalidMarksException` extends `RuntimeException`, so Java does not require callers to catch or declare it.
4. **When does `finally` run?** It runs as control leaves the `try`/`catch` statement in ordinary execution, including when a return or exception is pending. It is not guaranteed if the JVM is forcibly stopped or crashes.
5. **Why is checking `email == null` better than catching `NullPointerException`?** The check treats missing email as an expected input condition and handles it where it occurs; catching `NullPointerException` could hide an unrelated programming bug.
6. **What additional custom exception could the portal use?** `ResultDeliveryException` could be a checked exception for a failed email delivery, because the caller may need to retry or report the delivery failure.
