# Vehicle Rental System

**Batch:** Ashu Jauhari

The assignment solution uses `Vehicle` as a base class, with `Car` and `Bike` overriding rent calculation and detail display. `RentalApp` reads input with `Scanner` and stores all rentals in one `Vehicle[]`.

## Compile and run

Open a terminal in this folder and run:

```text
javac Vehicle.java Car.java Bike.java RentalApp.java
java RentalApp
```

Enter these values to show three rentals:

```text
3
1
KA01AB1234
Swift
1500
3
200
2
KA02CD5678
Activa
500
4
1
KA05EF9012
City
1800
2
250
```

The expected total is `11000.0`.

## Required screenshot

Run the sample interactively in the VS Code terminal. Capture the terminal after it displays all three rental rows and `Total Rent Collected: 11000.0`, then save the image as `vehicle_rental_output.png` in this folder.
