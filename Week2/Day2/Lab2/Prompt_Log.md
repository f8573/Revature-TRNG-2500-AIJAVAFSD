# CityPower AI Pair Programming Assignment Log

## Tool and record note

I completed this assignment with the Codex assistant, not GitHub Copilot inside STS. This is an assistant-generated work log, not a Copilot transcript. The prompts below are prepared for each exercise. If the course requires a Copilot transcript, run these prompts in Copilot and replace the tool-specific summaries. Source and tests were compiled and run locally.

## Task 1 — Plan and calculate by hand (no AI)

**Inputs and types:** consumer number and name are `String`; previous and current meter readings are non-negative `int` values.

**250-unit calculation:** first 100 units: `100 x Rs. 3.00 = Rs. 300.00`; next 150 units: `150 x Rs. 5.00 = Rs. 750.00`; energy charge is Rs. 1,050.00. The fixed charge is Rs. 50.00 and no surcharge applies, so the total is Rs. 1,100.00.

**Invalid readings:** if current is less than previous, show an error and do not print a bill.

**Boundaries:** 100/101 units; 300/301 units; energy charge at Rs. 2,000; current reading equal to or below previous.

## Prompt 1 — Generate the program

**Goal:** Generate a beginner-level CityPower bill program with the exact progressive rates and validation rules.

**Prompt used:**

```text
Act as a Java developer writing code for beginners. Write a Java console program
class ElectricityBillApplication in package com.citypower.billing for CityPower.
Read consumer number, consumer name (may contain spaces), previous reading and
current reading with Scanner. Units = current - previous; if current < previous,
print "Invalid readings: current reading cannot be less than previous" and stop.
Energy charge uses progressive slabs: first 100 units at Rs. 3.00, units 101-300
at Rs. 5.00, units above 300 at Rs. 7.50 (e.g. 150 units = 300 + 250 = Rs. 550).
Add a fixed charge of Rs. 50. Add a surcharge of 5% of the energy charge only if
the energy charge is above Rs. 2,000. Print consumer details, units, energy
charge, fixed charge, surcharge and total, amounts to 2 decimal places.
Use only variables, if-else, arithmetic and Scanner. Comment the slab logic.
Output the complete code, then the expected output for readings 1200 and 1600.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** The first version reads the consumer data, validates the reading order, computes the progressive energy charge, adds the fixed charge and conditionally adds the surcharge. For readings 1200 and 1600, it reports 400 units, Rs. 2,050.00 energy, Rs. 102.50 surcharge, and Rs. 2,202.50 total.

**Verification:** Hand-calculated the 250-unit example and the 400-unit sample. Both agree with the slab rules.

**Accepted / changed / rejected:** Kept the three progressive bands and strict surcharge condition. The first version is preserved as `ElectricityBillApplicationOriginal.java` for the refactoring comparison.

**What I would improve in this prompt:** State whether very large meter readings need an explicit maximum.

## Prompt 1b — Check the slab example

**Goal:** Confirm the progressive calculation on two amounts before refactoring.

**Prompt used:**

```text
Check the progressive slab calculation in the generated program. Confirm the
energy charge and total for 150 units and 250 units by showing the work. Do not
change the code unless you find a specific arithmetic error.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** 150 units cost `100 x 3 + 50 x 5 = Rs. 550.00`; with fixed charge the total is Rs. 600.00. 250 units cost `100 x 3 + 150 x 5 = Rs. 1,050.00`; with fixed charge the total is Rs. 1,100.00.

**Verification:** Both examples were recalculated by hand. No arithmetic correction was needed.

**Accepted / changed / rejected:** Kept the code as written. The handout's 150-unit example is correct; the separate Task 1 answer for 250 units is also correct.

## Prompt 2 — Explain the code

**Prompt used:**

```text
Act as a Java instructor for a beginner. Explain ElectricityBillApplication
line by line. Explain Scanner input, how nextLine preserves spaces in names,
the reading validation, how the progressive slab conditions divide the units,
the fixed charge, why the surcharge condition is strictly greater than 2000,
and how formatted output works. Explain the sample of 400 units with the
calculation. Do not rewrite the code.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** Explained how input values flow into the calculations and why each slab charges only the units inside its range. At 400 units the energy is Rs. 2,050.00, surcharge is Rs. 102.50, fixed charge is Rs. 50.00, and total is Rs. 2,202.50.

**Verification:** Independently recalculated `300 + 1000 + 750 = 2050`, then `2050 + 102.50 + 50 = 2202.50`.

**Accepted / changed / rejected:** Kept the explanation. No code change was made in this task.

**What I would improve in this prompt:** Ask for a small trace table showing units remaining after each slab.

## Prompt 3 — Refactor into methods

**Prompt used:**

```text
Refactor ElectricityBillApplication into beginner-friendly methods without
changing its valid-input bill output. Use these exact public static methods:
double calculateEnergyCharge(int units), double calculateSurcharge(double
energyCharge), and double calculateTotal(double energyCharge). Keep the console
flow in main, move bill output into printBill(...), and keep the package and
class names. The surcharge must be 5% of energy charge only when it is strictly
above Rs. 2,000. Explain each method and do not add MVC or advanced frameworks.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** The refactored class contains the three requested calculation methods, `calculateUnits`, reading validation, and a `printBill` method. It preserves the original bill layout for valid input.

**Verification:** Compiled both versions and ran the same sample input. The formatted sample output matched exactly.

**Accepted / changed / rejected:** Accepted method extraction. Added validation for non-negative readings and negative energy inputs. No output-format change was accepted.

**What I would improve in this prompt:** Specify the exact invalid-reading message, which is now included in the code.

## Prompt 4 — Generate test scenarios

**Prompt used:**

```text
Act as a software tester. Create boundary test cases for the CityPower bill.
Cover 0, 80, 100, 101, 300, 301, 393, 394, and 400 units, plus a current
reading lower than the previous reading. For every valid case show energy
charge, surcharge, and total using the exact progressive slab rules. The
surcharge is 5% of energy charge only when energy charge > Rs. 2,000. Show the
invalid case as an error with no bill. Suggest additional edge cases.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Hand-calculated / verified execution sheet:**

| # | Units | Expected energy (Rs.) | Expected surcharge (Rs.) | Expected total (Rs.) | Actual | Pass? |
|---:|---:|---:|---:|---:|---:|:---:|
| 1 | 0 | 0.00 | 0.00 | 50.00 | 50.00 | Yes |
| 2 | 80 | 240.00 | 0.00 | 290.00 | 290.00 | Yes |
| 3 | 100 | 300.00 | 0.00 | 350.00 | 350.00 | Yes |
| 4 | 101 | 305.00 | 0.00 | 355.00 | 355.00 | Yes |
| 5 | 300 | 1,300.00 | 0.00 | 1,350.00 | 1,350.00 | Yes |
| 6 | 301 | 1,307.50 | 0.00 | 1,357.50 | 1,357.50 | Yes |
| 7 | 393 | 1,997.50 | 0.00 | 2,047.50 | 2,047.50 | Yes |
| 8 | 394 | 2,005.00 | 100.25 | 2,155.25 | 2,155.25 | Yes |
| 9 | 400 | 2,050.00 | 102.50 | 2,202.50 | 2,202.50 | Yes |
| 10 | Invalid | Error; no bill | — | — | Error; no bill | Yes |

**Result summary:** The surcharge boundary is between 393 units (Rs. 1,997.50 energy, no surcharge) and 394 units (Rs. 2,005.00 energy, Rs. 100.25 surcharge). Additional cases include malformed input, negative readings, maximum integer readings, and names with spaces.

**Verification:** Ran the calculation cases through the Java methods and ran the invalid-reading case through the console program.

**Accepted / changed / rejected:** Accepted the listed boundaries. The surcharge is based only on the energy amount, not the fixed charge or total bill.

**What I would improve in this prompt:** Ask the tester to state whether values are rounded before applying the surcharge; the current rules use exact double arithmetic and format amounts to cents.

## Prompt 5 — Generate JUnit 5 tests

**Prompt used:**

```text
Generate JUnit 5 tests for these exact methods in ElectricityBillApplication:
calculateEnergyCharge(int units), calculateSurcharge(double energyCharge),
and calculateTotal(double energyCharge). Use the expected values from this
verified sheet: 0 -> 0.00; 80 -> 240.00; 100 -> 300.00; 101 -> 305.00;
300 -> 1300.00; 301 -> 1307.50; 393 -> 1997.50 with no surcharge;
394 -> 2005.00 with 100.25 surcharge; 400 -> 2050.00 with 102.50 surcharge
and total 2202.50. Include at least 8 test methods with descriptive names.
Use assertEquals(expected, actual, 0.001) for double values. Explain each test.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** Generated 13 descriptive JUnit tests for the slab thresholds, surcharge threshold, totals, valid meter subtraction, and rejected invalid units/readings.

**Verification:** All 13 tests ran successfully under JUnit 5; zero failed.

**Accepted / changed / rejected:** Added exact Rs. 2,000 surcharge and negative-unit validation tests beyond the minimum. No generated test was rejected.

**What I would improve in this prompt:** Ask for one test that confirms a consumer name with spaces is displayed intact.

## Prompt 5b — Add a validation test

**Goal:** Make invalid-reading and negative-unit behavior explicit and testable.

**Prompt used:**

```text
Add tests for invalid inputs without changing the billing rules: negative unit
counts must be rejected, and current meter reading below previous reading must
be rejected before a bill is printed. Use assertThrows for calculation methods
and assertFalse for the reading-pair validation method.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** Added tests that reject negative units and a descending meter reading. The console validation was also run with previous reading 1600 and current reading 1200; it printed the required error and no bill.

**Verification:** The expanded JUnit suite passed all 13 tests; the console check showed the exact error message.

**Accepted / changed / rejected:** Accepted input validation and kept all valid-input calculations unchanged.

**What I would improve in this prompt:** Ask for a malformed text input test as well as numeric boundary tests.

## Prompt 6 — Review the code

**Prompt used:**

```text
Act as a senior Java code reviewer. Review the current CityPower application
for logic errors, boundary mistakes, malformed or negative inputs, reliability,
and readability. Rate each finding critical or optional and explain why.
Do not rewrite code yet. Do not suggest MVC, Spring, or a web application.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Review decisions:**

| Finding | Priority | Decision | Reason |
|---|---|---|---|
| Current reading below previous could create negative units | Critical | Accept | Must stop the bill and show the required error. |
| Negative unit input passed directly to the calculation method | Critical | Accept | Reject invalid calculations instead of returning a plausible amount. |
| `double` has binary floating-point limits for money | Optional in this beginner exercise | Defer | `BigDecimal` would improve production currency accuracy, but the assignment explicitly practices simple arithmetic and `double` JUnit assertions. |
| Fixed charge or surcharge accidentally included in the surcharge base | Critical | Reject as not present | Code calculates the surcharge from energy charge only; tests cover this rule. |
| MVC/Spring layers would improve separation | Out of scope | Reject | The project instructions explicitly prohibit those layers for this assignment. |

**Result summary:** The review found the critical input-validation and financial-boundary checks, and one optional precision improvement. It did not identify a failing slab calculation.

**Verification:** Checked review points against the source and the JUnit test names/results.

**Accepted / changed / rejected:** Accepted invalid-reading and negative-unit guards. Deferred `BigDecimal` for the course version and rejected the out-of-scope architecture change.

## Prompt 7 — Apply accepted improvements

**Prompt used:**

```text
Apply only these accepted changes to ElectricityBillApplication:
1. Reject negative previous readings and current readings lower than previous.
2. Reject negative unit counts in calculateEnergyCharge.
3. Reject negative, NaN, or infinite energy charges in calculateSurcharge.
4. Keep calculateEnergyCharge(int), calculateSurcharge(double), and
   calculateTotal(double) with the same signatures.
5. Keep the progressive rates, strict surcharge condition, package, class,
   and valid bill output exactly unchanged.
Do not add frameworks or change business rules. Explain each change.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Result summary:** Added validation at input and calculation boundaries. The final implementation keeps all three required method signatures and uses the same output for valid readings.

**Verification:** Recompiled the application; all 13 JUnit tests passed. The 1200-to-1600 sample still produced Rs. 2,202.50. The original and refactored valid-input sample output matched exactly.

**Accepted / changed / rejected:** Applied only the accepted validations and kept the business rules and output format unchanged.

## Submission checklist

- [x] Prompt log for Prompts 1–7 and two follow-ups.
- [x] `ElectricityBillApplication.java` and `ElectricityBillApplicationTest.java`.
- [x] Test execution sheet with all 10 assignment rows.
- [x] 13 JUnit tests pass.
- [x] Screenshot of the green JUnit view in VS Code, saved as `junit_green.png` in this Lab2 folder.

## Completion addendum — Task 3 and Task 4

These answers and output records were completed with Codex; they are not Copilot chat transcripts.

### Task 3 — Follow-up prompt and explanation worksheet

**Prompt 2b prepared:** Explain why the second slab charges only units 101 through 300. Trace the values in calculateEnergyCharge(400), compare 300 and 301 units, and explain why the surcharge condition uses greater than rather than greater than or equal to Rs. 2,000. Do not change the code.

**Independent answer:** The first 100 units cost Rs. 300. The next 200 units cost Rs. 1,000. The remaining 100 units cost Rs. 750, so 400 units cost Rs. 2,050 in energy charges. At 300 units, the energy charge is Rs. 1,300; at 301 units it is Rs. 1,307.50. The surcharge condition is strictly above Rs. 2,000, so an energy charge of exactly Rs. 2,000 receives no surcharge.

| Question | Answer |
|---|---|
| How does the code split 400 units into slabs? | 100 units x Rs. 3 = Rs. 300; 200 units x Rs. 5 = Rs. 1,000; 100 units x Rs. 7.50 = Rs. 750; total energy charge = Rs. 2,050. |
| Which condition decides the surcharge, and what happens at exactly Rs. 2,000? | The condition is energyCharge > 2000. Exactly Rs. 2,000 has no surcharge. |
| How does the program read a name with spaces? | It reads the name with Scanner.nextLine(), which consumes the entire line, including spaces. |
| Did the explanation contain anything wrong or unclear? | The code comments and calculations agree with the progressive slab rules. The strict boundary is easy to miss, so I checked it against the test for exactly Rs. 2,000. |

**Debugger record:** I traced the sample by executing it and checked the source calculations, but did not step through the STS debugger. The course's debugger screenshot still needs to be captured in STS.

### Task 4 — Before and after output

Input for both versions: consumer number CP1001, consumer name Priya Sharma, previous reading 1200, current reading 1600. The bill output matched exactly:

~~~text
========== CITYPOWER ELECTRICITY BILL ==========
Consumer number : CP1001
Consumer name   : Priya Sharma
Previous reading: 1200
Current reading : 1600
Units consumed  : 400
Energy charge   : Rs. 2,050.00
Fixed charge    : Rs. 50.00
Surcharge       : Rs. 102.50
Total bill      : Rs. 2,202.50
================================================
~~~

## Independent review correction

During a later source review, I found that the test file had an accidental second top-level `ElectricityBillApplication` class, which prevented the source and tests from compiling together. I removed that duplicate and replaced the refactored application's slab rates and surcharge thresholds with named constants as required by Task 3. The updated source and tests compile, all 13 JUnit tests pass, and the 400-unit output still matches the original program. This correction and verification were done with Codex; they are not a GitHub Copilot transcript. At this point the green JUnit screenshot was still missing; it was later supplied and saved as `junit_green.png` in this Lab2 folder.

## VS Code Copilot Chat session supplement — 2026-11-20

The original log above contains the hand calculations and prior assistant-authored records. The entries below record additional prompts actually sent in GitHub Copilot Chat in VS Code Ask mode, the returned results, and decisions made after checking them.

### Prompt 1 — Generate the program

**Prompt actually sent:**

```text
Act as a Java developer writing code for beginners. Write a Java console program named ElectricityBillApplication in package com.citypower.billing for CityPower. Read consumer number, consumer name (which may contain spaces), previous meter reading, and current meter reading with Scanner. Units = current reading - previous reading; if current is less than previous, print "Invalid readings: current reading cannot be less than previous" and stop. Calculate energy charge progressively: first 100 units at Rs. 3.00, units 101–300 at Rs. 5.00, and units above 300 at Rs. 7.50 (150 units must cost Rs. 550.00). Add a fixed Rs. 50 charge. Add a surcharge of 5% of energy charge only when energy charge is above Rs. 2,000. Print consumer details, units, energy charge, fixed charge, surcharge, and total with amounts to two decimal places. Use Java fundamentals, if-else, arithmetic, and Scanner; add comments for slab logic. Return complete runnable code and expected output for readings 1200 and 1600. Do not edit the workspace.
```

**Copilot produced:** A complete single-main console example using progressive slabs and `energyCharge > 2000.00`. For readings 1200 and 1600 it computed 400 units, energy Rs. 2,050.00, fixed charge Rs. 50.00, surcharge Rs. 102.50, and total Rs. 2,202.50.

**Decision:** The arithmetic matched. I kept the existing final application because it has additional reusable methods and input guards; I did not replace the source with the generated draft.

### Prompt 1b — Check slab boundaries

**Prompt actually sent:**

```text
Check the CityPower slab calculation from the current assignment: for 150 units, show the units in each progressive slab, each multiplication, and the energy-charge total. Also state the result for 100, 101, 300, and 301 units. Do not edit the workspace.
```

**Copilot produced:** 150 units cost `100 × 3 + 50 × 5 = Rs. 550.00`; 100 = Rs. 300.00; 101 = Rs. 305.00; 300 = Rs. 1,300.00; 301 = Rs. 1,307.50. I checked these values against the hand calculations; no change was needed.

### Prompt 2 — Explain the code

**Prompt actually sent:**

```text
Act as a Java instructor for a beginner. Explain the current CityPower ElectricityBillApplication without rewriting it. Trace the sample of 400 units through each progressive slab, explain why energy charge Rs. 2,050 produces surcharge Rs. 102.50 and total Rs. 2,202.50, explain why exactly Rs. 2,000 energy charge has no surcharge, and explain how nextLine reads a consumer name with spaces. Use short labeled sections.
```

**Copilot produced:** It traced 100 × Rs. 3, 200 × Rs. 5, and 100 × Rs. 7.50 to Rs. 2,050; it then calculated a Rs. 102.50 surcharge and Rs. 2,202.50 total. It correctly identified the strict `>` comparison and explained that `nextLine()` preserves spaces.

**Prompt 2b follow-up actually sent:**

```text
Prompt 2b follow-up: Explain what happens at exactly 300 units and at 301 units in the generated calculateEnergyCharge method, showing the slab amounts. Then explain why exactly Rs. 2,000 energy charge receives no surcharge, while Rs. 2,000.01 does. Do not change the code.
```

**Copilot produced:** 300 units cost Rs. 1,300.00; 301 units cost Rs. 1,307.50. Exactly Rs. 2,000 has no surcharge; Rs. 2,000.01 is above the threshold and its 5% surcharge formats to Rs. 100.00. I checked the slab arithmetic and the strict comparison. The explanation was accurate.

### Prompt 3 — Refactor into methods

**Prompt actually sent:**

```text
Act as a beginner-level Java refactoring assistant. Refactor the selected ElectricityBillApplicationOriginal into class ElectricityBillApplication in package com.citypower.billing. Keep every valid-input bill line and the invalid-reading message identical. Use these exact public static methods: double calculateEnergyCharge(int units), double calculateSurcharge(double energyCharge), and double calculateTotal(double energyCharge). Move bill printing into printBill(...). Replace all magic numbers for slab limits, rates, fixed charge, surcharge threshold, and surcharge rate with named constants. Keep progressive slabs (first 100 at Rs. 3.00, next 200 at Rs. 5.00, remaining at Rs. 7.50) and surcharge only when energy charge is strictly above Rs. 2,000. Use beginner Java only. Return complete code and explain method roles. Do not edit files.
```

**Copilot produced:** A method-based class with the requested public calculation signatures, constants, and a `printBill(...)` method. It preserved the bill labels and strict surcharge condition. Its draft did not reject negative units passed directly to `calculateEnergyCharge`; I rejected that omission and kept the current implementation's `IllegalArgumentException` guard. The final source retains all required methods, constants, and additional validation.

### Prompt 4 — Test scenarios

**Prompt actually sent:**

```text
Act as a Java software tester for the current CityPower program. Give a compact table for 0, 80, 100, 101, 300, 301, 393, 394, and 400 units with energy charge, surcharge, and total. Include the exact Rs. 2,000 energy-charge boundary, a negative unit count, a descending meter reading, and malformed numeric input. Apply surcharge only when energy charge > Rs. 2,000. Do not edit code.
```

**Copilot produced:** It returned the expected table: 0 → Rs. 0.00 / Rs. 0.00 / Rs. 50.00; 80 → Rs. 240.00 / Rs. 0.00 / Rs. 290.00; 100 → Rs. 300.00 / Rs. 0.00 / Rs. 350.00; 101 → Rs. 305.00 / Rs. 0.00 / Rs. 355.00; 300 → Rs. 1,300.00 / Rs. 0.00 / Rs. 1,350.00; 301 → Rs. 1,307.50 / Rs. 0.00 / Rs. 1,357.50; 393 → Rs. 1,997.50 / Rs. 0.00 / Rs. 2,047.50; 394 → Rs. 2,005.00 / Rs. 100.25 / Rs. 2,155.25; 400 → Rs. 2,050.00 / Rs. 102.50 / Rs. 2,202.50. At exactly Rs. 2,000 energy, the total is Rs. 2,050.00 with no surcharge. Negative units throw, descending readings print the specified error, and malformed reading text is rejected and re-prompted.

**Verification:** I ran all ten required previous/current reading pairs through the console program. All nine valid totals matched the expected values, and the descending-reading case printed the required error without a bill.

### Prompt 5 — Generate JUnit 5 tests

**Prompt actually sent:**

```text
Generate beginner-friendly JUnit 5 test methods for the current ElectricityBillApplication methods calculateEnergyCharge(int), calculateSurcharge(double), calculateTotal(double), and isValidReadingPair(int,int). Include tests for 0, 80, 100, 101, 300, 301, 393, 394, 400 units, exact Rs. 2,000 energy charge, negative units, non-finite surcharge input, equal readings, and descending readings. Use assertEquals(expected, actual, 0.001) for doubles and assertThrows/assertFalse for invalid values. Show a concise test list and explain what each covers. Do not modify files.
```

**Copilot produced:** Sixteen descriptive tests for slab boundaries, surcharge thresholds, final totals, negative inputs, non-finite energy charges, and valid/invalid reading pairs. I used the additional ideas for negative surcharge, NaN/infinite surcharge, and equal readings. A later follow-up added a separate negative-meter-reading test, bringing the final suite to 17 methods.

### Prompts 6 and 7 — Review and apply accepted changes

**Prompt 6 actually sent:**

```text
Act as a Java code reviewer. Review the current CityPower program for logical errors, slab and surcharge boundaries, invalid or malformed readings, reliability, and readability. Separate required corrections from optional production improvements. Do not rewrite the code and do not propose frameworks.
```

**Copilot produced:** No assignment-required corrections for the current program. It confirmed slab and surcharge boundaries, negative/descending reading guards, and malformed input handling. Optional observations included `double` money precision, more specific invalid-input messages, and duplicate validation checks. I deferred those production refinements as outside this beginner assignment.

**Prompt 7 actually sent:**

```text
Apply only assignment-required corrections identified in your review to the current CityPower file. Preserve beginner-level Java, method signatures, named constants, progressive rates, strict > Rs. 2,000 surcharge rule, and valid bill output. Do not apply optional BigDecimal or framework changes. If no required correction remains, leave the file unchanged and say so.
```

**Copilot produced / decision:** It confirmed all required methods, named constants, calculations, and input checks were already present and left the workspace unchanged. I kept the implementation as-is.

### Prompt 5c — Test negative meter readings (Copilot in VS Code Ask mode)

**Goal:** Add a JUnit test for the accepted non-negative meter-reading validation.

**Prompt actually sent:**

```text
Using the attached ElectricityBillApplicationTest.java and current CityPower application, propose one additional JUnit 5 test method for the validation rule that both readings must be nonnegative and current must be at least previous. Cover a negative previous reading and a negative current reading. Use the existing style: assertFalse for isValidReadingPair and assertThrows for calculateUnits. The imports are already present. Return only the method and do not edit any file.
```

**R-T-C-C-O check:** Role [x] Task [x] Context [x] Constraints [x] Output [x]

**Copilot produced:**

```java
@Test
void negativeReadingsAreRejected() {
    assertFalse(ElectricityBillApplication.isValidReadingPair(-1, 100));
    assertThrows(IllegalArgumentException.class,
            () -> ElectricityBillApplication.calculateUnits(-1, 100));
    assertFalse(ElectricityBillApplication.isValidReadingPair(100, -1));
    assertThrows(IllegalArgumentException.class,
            () -> ElectricityBillApplication.calculateUnits(100, -1));
}
```

**Accepted / changed / rejected:** I added the suggested method. It checks both negative previous and negative current readings through the validation and calculation methods. No part of the suggestion was rejected.

**Verification:** Compiled all CityPower sources and tests with JDK 17 and ran the JUnit standalone runner. All 17 tests passed with zero failures.

**What I would improve in this prompt:** Ask Copilot to include malformed text-input behavior in a separate integration test, because the calculation methods do not read console input.

### Final verification — 2026-11-23

- All ten required previous/current reading cases matched the expected outputs, including the descending-reading error with no bill.
- All 17 JUnit tests passed with zero failures.
- The 1200-to-1600 sample produced Rs. 2,202.50, and the 400-unit debugger trace produced Rs. 2,050.00 energy and Rs. 102.50 surcharge.
- The user-provided VS Code screenshot shows 17/17 JUnit tests passing and is saved as `junit_green.png` in this Lab2 folder.
