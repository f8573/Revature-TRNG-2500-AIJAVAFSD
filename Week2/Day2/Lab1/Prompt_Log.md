# SmartStore AI Pair Programming Lab Log

## Tool and record note

I completed this solution with the Codex assistant. I did not run GitHub Copilot inside Spring Tool Suite (STS), so the result summaries below are Codex-generated, not a Copilot transcript. The prompt texts are prepared for each lab interaction. Use them in Copilot if the course requires proof from Copilot. The code and calculations below were compiled and checked locally.

## Exercise 1 — Set up the Java project

**Prompt used:** None. This is the manual setup step.

**AI output:** No AI response was needed. I created package `com.smartstore.billing` with `BillingApplication` and a separate `BillingApplicationOriginal` class for the before/after comparison.

**Changed or rejected:** I used one input line per value and `nextLine()` so product names containing spaces are read fully. No Copilot suggestions were involved.

## Exercise 2 — Generate Java code

**Prompt used:**

```text
You are an experienced Java developer.

Develop a simple Java billing application for a retail store named SmartStore.
The class must be named BillingApplication in package com.smartstore.billing.

Requirements:
1. Accept customer name, product name, product price and quantity.
2. Calculate the total purchase amount (price x quantity).
3. Apply a 5% discount if the purchase amount is between Rs. 1,000 and Rs. 4,999.
4. Apply a 10% discount if the purchase amount is Rs. 5,000 or above.
5. No discount below Rs. 1,000.
6. Display customer details, total amount, discount percentage, discount amount
   and final amount, with amounts shown to 2 decimal places.

Constraints:
- Use Java fundamentals only: variables, data types, if-else and arithmetic operators.
- Use Scanner for user input.
- Do not use advanced Java features.
- Write readable, beginner-friendly code.
- Include comments explaining important logic.

Generate a complete executable Java program with a main() method.
```

**AI output summary:** Produced a console bill using the SmartStore thresholds and a formatted receipt. The sample input Rahul / Laptop Bag / 1200 / 5 gives total Rs. 6,000.00, discount Rs. 600.00, and final amount Rs. 5,400.00.

**Changed or rejected:** Kept the beginner-level control flow. Used `nextLine()` for all input, and added small input-validation loops after review. Chose the handout's suggested interpretation for paise: 5% applies from Rs. 1,000 up to, but not including, Rs. 5,000.

## Exercise 3 — Explain the generated code

**Prompt used:**

```text
Act as a Java programming instructor.

Explain the selected Java code line by line.

For each important statement:
1. Explain what it does.
2. Explain why it is required.
3. Identify the Java concept being used.
4. Give a simple example where applicable.

Pay special attention to:
- Scanner and user input
- Variables and data types
- Arithmetic calculations
- If-else conditions
- Discount calculation
- Output statements

Explain everything in beginner-friendly language.
```

**AI output summary:** Explained Scanner input, variable types, multiplication, if/else discount branches, and formatted output. It also identified that `nextLine()` preserves spaces in the product name.

**Verification:** Ran the sample bill and checked the 10% branch for Rs. 6,000.00. The calculated discount and final amount matched the requirements.

**Changed or rejected:** No code change was needed from the explanation. I would still verify each statement in STS before accepting an IDE-generated explanation.

## Exercise 4 — Refactor the code

**Prompt used:**

```text
Review the existing SmartStore billing application.

Refactor the code using these requirements:
1. Move discount calculation into a separate method:
   public static double calculateDiscount(double totalAmount)
   It must return the discount amount in rupees (not the percentage).
2. Move bill printing into a separate method named printBill().
3. Use meaningful variable and method names.
4. Remove unnecessary code.
5. Improve readability and maintainability.
6. Do not change the existing business rules or the output format.
7. Do not introduce advanced Java concepts.

Explain the changes you made and why they improve the code.
```

**AI output summary:** Split the discount amount and percentage logic into methods and moved receipt formatting into `printBill`. The refactored program keeps the same receipt output.

**Verification:** Ran both classes with the sample input and compared their captured output. The original and refactored output matched exactly.

**Changed or rejected:** Accepted the method extraction. Kept the user-input validation helper methods as a separate review improvement; did not change the receipt text.

## Exercise 5 — Generate boundary test scenarios

**Prompt used:**

```text
Act as a Java software tester.

Analyze the SmartStore billing application and generate test cases.

Include:
1. Purchase amount below Rs. 1,000.
2. Purchase amount exactly Rs. 1,000.
3. Purchase amount Rs. 4,999.
4. Purchase amount exactly Rs. 5,000.
5. Purchase amount above Rs. 5,000.
6. Quantity greater than one.

For each test case provide:
- Test input (price and quantity)
- Expected discount percentage
- Expected discount amount
- Expected final amount

Identify any additional edge cases that should be tested.
```

**AI output summary and hand-checked cases:**

| Purchase total | Expected discount | Expected final amount | Result |
|---:|---:|---:|---|
| Rs. 999.99 | 0% / Rs. 0.00 | Rs. 999.99 | Pass |
| Rs. 1,000.00 | 5% / Rs. 50.00 | Rs. 950.00 | Pass |
| Rs. 4,999.00 | 5% / Rs. 249.95 | Rs. 4,749.05 | Pass |
| Rs. 4,999.50 | 5% / Rs. 249.98 | Rs. 4,749.52 | Pass |
| Rs. 5,000.00 | 10% / Rs. 500.00 | Rs. 4,500.00 | Pass |
| Rs. 6,000.00 (Rs. 1,200 x 5) | 10% / Rs. 600.00 | Rs. 5,400.00 | Pass |

**Changed or rejected:** The written band “Rs. 1,000 to Rs. 4,999” leaves fractional paise unclear. Followed the lab's suggested rule `total >= 1000 && total < 5000`, so Rs. 4,999.50 receives 5%.

## Exercise 6 — Generate and run JUnit 5 tests

**Prompt used:**

```text
Generate JUnit 5 tests for BillingApplication.calculateDiscount(double totalAmount).
Cover below Rs. 1,000, exactly Rs. 1,000, Rs. 4,999, Rs. 4,999.50,
exactly Rs. 5,000, and an amount above Rs. 5,000. Use descriptive test names
and assertEquals(expected, actual, 0.001) for doubles. Add a test for the final
amount if a calculation method is available. Explain what each test checks.
```

**AI output summary:** Created discount-boundary tests and one final-amount test. Added separate salary-calculator tests for the final challenge.

**Verification:** JUnit 5 ran 12 tests: all 7 billing tests and all 5 salary-calculator tests passed.

**Changed or rejected:** Added the Rs. 4,999.50 case because the lab identifies the paise boundary as ambiguous. No test was rejected.

## Exercise 7 — Responsible AI review and prompt engineering

**Prompt used:**

```text
Review the SmartStore billing application.

Identify:
1. Possible logical errors.
2. Incorrect discount calculations.
3. Potential invalid user inputs.
4. Possible security or reliability issues.
5. Improvements required before deploying this application.

Explain which suggestions are critical and which are optional.
Do not rewrite the code yet.
```

**AI output summary:** Flagged the unclear Rs. 4,999 paise boundary, malformed or non-positive input, binary floating-point currency concerns, and the need to verify the `>= Rs. 5,000` condition. It recommended validation and more exact currency handling for a real billing system.

**Changed or rejected:** Accepted checks for blank names, invalid/non-positive price, and invalid/non-positive quantity. Kept `double` to match the beginner lab and formatted all amounts to two decimal places. Rejected introducing a database or advanced architecture because they are outside this exercise.

## Final challenge — Employee Salary Calculator

**Prompt used:**

```text
Act as a Java developer writing code for beginners. Create a console class
SalaryCalculator that reads employee ID, employee name, and a non-negative
basic salary. Calculate HRA as 20% and DA as 10% of basic salary. Gross salary
is basic + HRA + DA. Deduct professional tax of Rs. 200 only when gross salary
is above Rs. 20,000. Print employee details and net salary to two decimal places.
Use a separate calculateGross() method and calculateProfessionalTax() method.
Validate blank details, malformed salary text, and negative or non-finite salary.
Keep the program beginner-friendly and output the complete Java code.
```

**AI output summary:** Produced a salary calculator and methods for HRA, DA, gross salary, and tax. For basic Rs. 16,000, gross is Rs. 20,800, tax is Rs. 200, and net is Rs. 20,600.

**Verification:** Ran the sample and tested gross salary at Rs. 20,000 and just above it. The boundary test rounds gross to cents before checking the strict `>` rule, avoiding a floating-point artifact at the threshold. Five salary JUnit tests passed.

**Changed or rejected:** Added invalid salary handling and cent rounding before the tax comparison. Kept the strict “above” condition, so exactly Rs. 20,000 has no tax.

## Completion supplement — Day 2 details

The additions below are Codex-authored worksheet answers and execution records. They are not GitHub Copilot chat transcripts.

### Exercise 3 — Code understanding worksheet

| Question | Answer |
|---|---|
| Which variables and types does the program use? | customerName and productName are String; price, totalAmount, discountAmount, discountPercentage, and finalAmount are double; quantity is int. The input Scanner reads each value. |
| Which if-else conditions decide the discount? | totalAmount >= 5000.00 selects 10%; otherwise totalAmount >= 1000.00 and totalAmount < 5000.00 selects 5%; all smaller totals select 0%. |
| How is the discount amount calculated? | The program multiplies the purchase total by its discount rate. The result is rounded to cents before it is printed or subtracted. |
| How is the final amount calculated? | It subtracts the rounded discount amount from the purchase total. |
| Which conditions change if the 10% threshold moves to Rs. 6,000? | Change the 10% condition to totalAmount >= 6000.00 and the 5% upper bound to totalAmount < 6000.00. |

**Follow-up question:** Why does the program store price as double and quantity as int, and what does %.2f mean?

**Answer:** Price can include paise, so it needs a decimal type in this beginner exercise. Quantity counts whole products, so it uses an integer. In a formatted print statement, %.2f displays a floating-point number with exactly two digits after the decimal point.

**Explanation check:** The sample with price Rs. 1,200 and quantity 5 produces total Rs. 6,000, which meets the >= 5000 condition. The discount is Rs. 600 and the final amount is Rs. 5,400. I confirmed this from the source and console run; I did not step through the STS debugger.

### Exercise 4 — Before and after sample output

Input for both versions: Rahul, Laptop Bag, 1200, 5. The receipt body was identical:

~~~text
========== CUSTOMER BILL - SMARTSTORE ==========
Customer         : Rahul
Product          : Laptop Bag
Price            : Rs. 1200.00
Quantity         : 5
Total            : Rs. 6000.00
Discount         : 10%
Discount amount  : Rs. 600.00
-----------------------------------------------
Final amount     : Rs. 5400.00
===============================================
~~~

### Exercise 5 — Complete eight-row execution sheet

| # | Price (Rs.) | Qty | Total (Rs.) | Discount | Discount amount (Rs.) | Expected final (Rs.) | Actual final (Rs.) | Pass? |
|---:|---:|---:|---:|---:|---:|---:|---:|:---:|
| 1 | 800.00 | 1 | 800.00 | 0% | 0.00 | 800.00 | 800.00 | Yes |
| 2 | 1,000.00 | 1 | 1,000.00 | 5% | 50.00 | 950.00 | 950.00 | Yes |
| 3 | 4,999.00 | 1 | 4,999.00 | 5% | 249.95 | 4,749.05 | 4,749.05 | Yes |
| 4 | 5,000.00 | 1 | 5,000.00 | 10% | 500.00 | 4,500.00 | 4,500.00 | Yes |
| 5 | 7,000.00 | 1 | 7,000.00 | 10% | 700.00 | 6,300.00 | 6,300.00 | Yes |
| 6 | 1,200.00 | 5 | 6,000.00 | 10% | 600.00 | 5,400.00 | 5,400.00 | Yes |
| 7 | 999.99 | 1 | 999.99 | 0% | 0.00 | 999.99 | 999.99 | Yes |
| 8 | 250.00 | 4 | 1,000.00 | 5% | 50.00 | 950.00 | 950.00 | Yes |

**Additional paise boundary:** Rs. 4,999.50 receives 5%. The discount rounds to Rs. 249.98 and the final amount is Rs. 4,749.52. Rounding the discount before subtracting keeps the displayed amounts consistent.

| Edge case | Current behavior | Desired behavior |
|---|---|---|
| Total Rs. 4,999.50 | Applies 5%; displays discount Rs. 249.98 and final Rs. 4,749.52. | Confirm the exact boundary with the client; this solution follows the lab's suggested rule of 5% below Rs. 5,000. |
| Quantity 0 | Rejects it and asks for a positive whole number. | Reject it and do not print a bill. |
| Negative price or quantity | Rejects each and prompts again. | Reject it and do not print a bill. |
| Text entered for price | Shows a validation message and prompts again. | Reject the invalid entry without printing a bill. |

### Exercise 7 Part B — Weak and stronger prompt comparison

The worksheet's weak prompt is “Write Java billing code.” Its stronger prompt assigns an experienced Java developer role, describes the retail-store billing context and discount task, limits the implementation to Java fundamentals, and asks for executable code with comments and sample output.

| Difference in requested result | R-T-C-C-O element |
|---|---|
| The stronger prompt is more likely to produce a complete bill flow instead of a short fragment because it names what to build and which inputs and calculations to include. | Task and Context |
| The stronger prompt is more likely to use beginner-level Java and avoid unrelated advanced features. | Constraints |
| The stronger prompt is more likely to include runnable code, comments, and a sample that can be checked. | Output |

This is a prompt-content comparison, not a record of two separate Copilot chat responses.

### Final challenge — Five executed salary scenarios

| # | Basic salary (Rs.) | HRA (Rs.) | DA (Rs.) | Gross (Rs.) | Tax (Rs.) | Expected net (Rs.) | Actual net (Rs.) | Pass? |
|---:|---:|---:|---:|---:|---:|---:|---:|:---:|
| 1 | 10,000.00 | 2,000.00 | 1,000.00 | 13,000.00 | 0.00 | 13,000.00 | 13,000.00 | Yes |
| 2 | 15,000.00 | 3,000.00 | 1,500.00 | 19,500.00 | 0.00 | 19,500.00 | 19,500.00 | Yes |
| 3 | 16,000.00 | 3,200.00 | 1,600.00 | 20,800.00 | 200.00 | 20,600.00 | 20,600.00 | Yes |
| 4 | 15,384.6153846154 | 3,076.92 | 1,538.46 | 20,000.00 | 0.00 | 20,000.00 | 20,000.00 | Yes |
| 5 | 15,384.63 | 3,076.93 | 1,538.46 | 20,000.02 | 200.00 | 19,800.02 | 19,800.02 | Yes |

An additional run with basic salary -100 printed “Invalid employee details or salary.” The JUnit suite now has five distinct salary tests, including zero salary.

## VS Code Copilot Chat session supplement — 2026-11-20

The initial worksheet log above was prepared with Codex. The entries below record prompts actually sent to GitHub Copilot Chat in VS Code, Copilot's returned content, and my decisions. I used Ask mode and did not paste real customer information.

### Exercise 2 — Generate the SmartStore application

**Prompt actually sent:**

```text
You are an experienced Java developer. Develop a simple Java billing application for a retail store named SmartStore. The class must be named BillingApplication in package com.smartstore.billing. Requirements: accept customer name, product name, product price and quantity; calculate total as price times quantity; apply 5% discount from Rs. 1,000 through Rs. 4,999; apply 10% at Rs. 5,000 or above; no discount below Rs. 1,000; display customer details, total, discount percentage, discount amount, and final amount to two decimal places. Use Java fundamentals only: variables, data types, if-else and arithmetic. Use Scanner for user input. Do not use advanced features. Write readable beginner-friendly code with comments. Use nextLine for text fields so product names with spaces work. Return executable code.
```

**Copilot produced:** A runnable console program that reads the customer and product details, calculates the total and discount tiers, and prints a formatted receipt. Its sample of Rs. 1,200 multiplied by five produced Rs. 6,000 total, Rs. 600 discount, and Rs. 5,400 final amount.

**Accepted / changed / rejected:** The business logic and `nextLine()` approach matched the assignment. I retained the existing implementation and validation helpers rather than replacing the full file with a second version.

### Exercise 3 — Explain the code and ask a follow-up

**Prompt actually sent:**

```text
Act as a Java programming instructor. Explain the selected Java code line by line. For each important statement: (1) Explain what it does. (2) Explain why it is required. (3) Identify the Java concept being used. (4) Give a simple example where applicable. Pay special attention to Scanner and user input; variables and data types; arithmetic calculations; if-else conditions; discount calculation; and output statements. Explain everything in beginner-friendly language.
```

**Copilot produced:** It explained `String`, `double`, `int`, input parsing and validation, total and discount calculations, methods, and formatted output. It used the Rs. 1,200 × 5 example and arrived at Rs. 6,000 total, Rs. 600 discount, and Rs. 5,400 final amount.

**Follow-up prompt actually sent:**

```text
Why is price stored as double and quantity as int, and what does %.2f mean in the bill output? Use sample price Rs. 1,200 and quantity 5. Answer in three bullets.
```

**Copilot produced:** Price can contain paise, quantity counts whole items, and `%.2f` displays two digits after the decimal point. It repeated the Rs. 6,000 / Rs. 600 / Rs. 5,400 example. I checked the arithmetic against the code; no code change was needed.

### Exercise 4 — Refactor review

**Prompt actually sent:**

```text
Review the existing SmartStore billing application. Refactor it using these requirements: (1) Move discount calculation into public static double calculateDiscount(double totalAmount), returning the discount amount in rupees. (2) Move bill printing into printBill(). (3) Use meaningful names and remove unnecessary code. (4) Keep the discount thresholds, business rules, and output format unchanged. (5) Use beginner-level Java only. Explain the changes. Do not apply the result; show the refactored code and a concise change summary.
```

**Copilot produced:** It suggested separate discount and receipt methods, but changed receipt labels and removed input-validation helpers. **Decision:** I rejected those changes because they would change required output and remove useful checks. I kept the method-extraction approach and the existing labels and validations. The before/after sample output comparison was already recorded above.

### Exercise 5 — Boundary scenarios

**Prompt actually sent:**

```text
Act as a Java software tester. Analyze the SmartStore billing application and generate test cases. Include: (1) Purchase amount below Rs. 1,000. (2) Exactly Rs. 1,000. (3) Rs. 4,999. (4) Exactly Rs. 5,000. (5) Above Rs. 5,000. (6) Quantity greater than one. For each case give price, quantity, total, discount percentage, discount amount, and final amount. Identify additional edge cases, especially Rs. 4,999.50, zero quantity, negative values, and non-numeric input. Use a compact table.
```

**Copilot produced:** Correct expected results for Rs. 999.99 (0%), Rs. 1,000 (5%), Rs. 4,999 (5%), Rs. 5,000 (10%), Rs. 6,000 (10%), and Rs. 1,200 × 5 (Rs. 6,000, 10%). It noted that Rs. 4,999.50 is in the 5% band if the rule is interpreted as totals below Rs. 5,000; the rounded discount is Rs. 249.98 and final amount Rs. 4,749.52.

**Verification:** I ran all eight SmartStore rows through the console program. Actual final amounts were Rs. 800.00, Rs. 950.00, Rs. 4,749.05, Rs. 4,500.00, Rs. 6,300.00, Rs. 5,400.00, Rs. 999.99, and Rs. 950.00. They matched the expected values.

### Exercise 6 — JUnit prompt and tests

**Prompt actually sent:**

```text
I have a Java billing application with this method: public static double calculateDiscount(double totalAmount). It returns the discount amount in rupees. Generate beginner-friendly JUnit 5 test methods for it. Test: amount below Rs. 1,000 (800), exactly Rs. 1,000, Rs. 4,999, Rs. 4,999.50, exactly Rs. 5,000, and above Rs. 5,000 (7,000). Use assertEquals(expected, actual, 0.001) for double values. Add a test for the final amount if a calculation method is available. Explain each test case and how the assertion verifies the expected result. Do not modify the business requirements.
```

**Copilot produced:** Boundary tests for the discount thresholds and a final-amount example for Rs. 7,000, which gives Rs. 700 discount and Rs. 6,300 final amount. I kept the existing billing tests and added the salary tests described below rather than duplicating the billing tests.

### Exercise 7 — Review, selected validation fix, and prompt comparison

**Review prompt actually sent:**

```text
Review the SmartStore billing application. Identify: (1) possible logical errors, (2) incorrect discount calculations, (3) invalid user inputs, (4) security or reliability issues, and (5) improvements needed before deployment. Mark each finding critical or optional and explain why. Do not rewrite the code yet. Do not suggest advanced frameworks.
```

**Copilot produced:** It confirmed the discount boundary logic, flagged `double` precision and unbounded very large totals as reliability concerns, and emphasized preserving input checks. It also recommended testing negative, non-finite, zero and malformed values. I kept `double` for the beginner exercise and preserved the existing interactive input checks.

**Follow-up prompt actually sent:**

```text
Apply only this accepted validation improvement to the SmartStore code: calculateDiscount(totalAmount) must reject a negative, NaN, or infinite total by throwing IllegalArgumentException. Keep existing positive-input loops, method signatures, discount thresholds, output text, and beginner-level structure unchanged. Do not remove validation helpers or change bill labels. Show the code changes and explain them; do not edit the workspace.
```

**Copilot produced / decision:** It recommended checking `totalAmount < 0 || Double.isNaN(totalAmount) || Double.isInfinite(totalAmount)` and throwing `IllegalArgumentException`. I applied that guard and added tests for negative, NaN, and infinite totals. The final Lab1 JUnit suite passes all 20 tests.

**R-T-C-C-O weak-prompt experiment — new Copilot chat:**

Weak prompt sent:

```text
Write Java billing code.
```

Copilot searched the repository and returned a short summary of the existing `BillingApplication`; it did not generate a new program. The prompt did not identify the target, requirements, Java level, or desired answer.

Improved prompt sent in the same chat:

```text
Role: Act as an experienced Java instructor. Task: provide a complete Java console billing program for a retail store. Context: it must be class BillingApplication in package com.smartstore.billing, read customer name, product name, price, and quantity, calculate total, discount, and final amount. Apply 5% when total is at least Rs. 1,000 and below Rs. 5,000, 10% when total is at least Rs. 5,000, and no discount below Rs. 1,000. Constraints: use beginner-level Java fundamentals, Scanner, if-else and arithmetic only; use nextLine so names with spaces work; include comments; show all currency to 2 decimals; do not use advanced frameworks. Output: return the full executable code and a checked example for customer Rahul buying 5 Laptop Bags at Rs. 1,200 each, with the total, discount, and final amount. Do not edit workspace files.
```

Copilot returned a complete executable program and checked total Rs. 6,000.00, discount Rs. 600.00, final Rs. 5,400.00. The stronger prompt supplied all five R-T-C-C-O elements and asked for checkable output.

| Difference in actual output | R-T-C-C-O element |
|---|---|
| The weak prompt led Copilot to summarize an existing repository program; the stronger prompt requested and received new executable code. | Task |
| The stronger prompt specified SmartStore, the class/package, inputs, and discount boundaries. | Context and Task |
| The stronger prompt restricted Java features, required spaced names and formatted amounts, and requested a sample result. | Constraints and Output |

### Final challenge — SalaryCalculator with Copilot

**Generation prompt actually sent:**

```text
Act as a Java developer writing code for beginners. For the Week 2 Day 2 salary final challenge, create a console class SalaryCalculator in package com.smartstore.billing. Read employee ID, employee name, and basic salary. Calculate HRA as 20% and DA as 10% of basic salary; gross = basic + HRA + DA; deduct professional tax of Rs. 200 only when gross salary is strictly above Rs. 20,000; net = gross - tax. Print all amounts to two decimals. Validate blank details, malformed salary text, negative salary, NaN, and infinity. Include public static calculateGross(double) and calculateProfessionalTax(double) methods and a main method. Use simple Java and Scanner. Show complete code and calculate the result for basic salary Rs. 16,000. Do not edit the workspace.
```

**Copilot produced:** A full calculator with named HRA/DA/tax constants, input loops, separate calculation methods, and the Rs. 16,000 example (HRA Rs. 3,200; DA Rs. 1,600; gross Rs. 20,800; tax Rs. 200; net Rs. 20,600). I kept the current output and validation behavior rather than replacing the file wholesale.

**Explanation prompt actually sent:**

```text
Act as a beginner Java instructor. Explain the current SalaryCalculator line by line in labeled sections: Scanner input and validation; data types; HRA and DA; calculateGross; the strict professional-tax threshold; net salary and formatted output. Use basic salary Rs. 16,000 to show HRA, DA, gross, tax, and net. Do not rewrite the file.
```

**Copilot produced:** It accurately explained parsing, blank/non-finite checks, double values, allowance methods, cent rounding, the strict `> Rs. 20,000` rule, and formatted output. The Rs. 16,000 example matched the calculation above.

**Refactor prompt actually sent:**

```text
Refactor the current SalaryCalculator in beginner-friendly Java. Keep the package, class, existing valid behavior, input validation, output labels, and strict tax rule. Put the gross formula in public static double calculateGross(double basicSalary), and the tax decision in public static double calculateProfessionalTax(double grossSalary). Keep or add named constants for HRA rate, DA rate, tax amount, and threshold. Explain each method, then show only the changed method signatures/code and note any important difference from the current implementation. Do not edit the workspace.
```

Copilot recommended `HRA_RATE`, `DA_RATE`, `PROFESSIONAL_TAX`, and `TAX_THRESHOLD` constants and preserving the current cent rounding. I applied those constants to `SalaryCalculator`; I retained the existing rounding and methods because they protect the exact threshold behavior.

**Scenario prompt actually sent:**

```text
Act as a Java software tester. For the current SalaryCalculator, calculate and tabulate five scenarios: basic salary Rs. 10,000; Rs. 15,000; Rs. 16,000; the basic salary 20,000 / 1.3 that produces gross exactly Rs. 20,000; and Rs. 15,384.63, just above the gross threshold. Show HRA, DA, gross, tax, and net to cents. Also state expected behavior for negative and non-numeric salary input. Use the current code's rounding and strict > threshold rule. Do not edit code.
```

Copilot's results were respectively: gross/net Rs. 13,000 with no tax; Rs. 19,500 with no tax; Rs. 20,800 gross, Rs. 200 tax, Rs. 20,600 net; exactly Rs. 20,000 gross, no tax; and Rs. 20,000.02 gross, Rs. 200 tax, Rs. 19,800.02 net. Negative and malformed salary inputs are rejected. I ran all five console scenarios and confirmed these results.

**JUnit prompt actually sent:**

```text
Generate at least eight beginner-friendly JUnit 5 tests for the current SalaryCalculator public calculation methods. Test calculateHra, calculateDa, calculateGross, and calculateProfessionalTax for basic salary 0, 10,000, 15,000, 16,000, exactly Rs. 20,000 gross using 20,000 / 1.3, and just above the threshold using 15,384.63. Include a net-pay calculation case. Use assertEquals(expected, actual, 0.001). Explain each test briefly. Do not edit the workspace.
```

Copilot proposed ten tests covering allowance rates, gross pay, exact and just-above tax boundaries, and net pay. I added five non-duplicate salary tests from those recommendations. The combined Lab1 suite now has ten billing tests and ten salary tests; all 20 pass.

**Salary review prompt actually sent:**

```text
Act as a Java code reviewer. Review the current SalaryCalculator and its tests for incorrect salary calculations, the exact Rs. 20,000 tax boundary, invalid or non-finite input, reliability, and beginner readability. Separate assignment-critical findings from optional production improvements. Do not rewrite code or suggest frameworks.
```

Copilot found no assignment-critical formula or boundary errors. It listed repeated validation, more specific messages, and re-prompting after malformed input as optional improvements. I kept the beginner-level current behavior and retained the strict boundary tests.

**Final verification:** All eight SmartStore console cases and five salary scenarios were run. The standalone JUnit 5 suite reports 20 tests successful and zero failed.

### Completion addendum — Copilot review in VS Code, 2026-11-20

**Review prompt actually sent:**

```text
Act as a Java reviewer. Review the attached BillingApplication.java against the Week 2 Day 2 SmartStore lab requirements: read customer and product names, price and quantity; calculate price times quantity; apply 0% below Rs. 1,000, 5% from Rs. 1,000 to below Rs. 5,000, and 10% from Rs. 5,000; print the required bill fields with two decimal places. Check that names with spaces work and review the Rs. 4,999.50 boundary. Do not edit files. List any missing or incorrect requirements and any optional risks, briefly.
```

**Copilot produced:** It found no assignment-critical gaps. It confirmed that `nextLine()` keeps names with spaces intact and that Rs. 4,999.50 receives 5%, with a rounded discount of Rs. 249.98 and final amount of Rs. 4,749.52. It listed optional risks: `double` currency precision, direct calls to `calculateDiscountPercentage()` with invalid totals, extremely large prices overflowing during multiplication, and `printBill()` being private.

**Decision:** I kept `double` and the beginner-level design because the assignment uses simple arithmetic and does not require production currency handling. I deferred direct helper validation and extreme-magnitude limits because the main input path already rejects invalid prices and `calculateDiscount()` rejects non-finite totals. I rejected the visibility concern because the lab only requires a separate `printBill()` method, not a public one. No assignment rule or sample output changes.
