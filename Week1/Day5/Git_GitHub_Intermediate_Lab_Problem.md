# Git & GitHub -- Intermediate Developer Hands-on Lab

**Case study:** Employee Management System (EMS)\
**Company:** TechNova Solutions\
**Duration:** 2 hours (120 minutes)\
**Level:** Intermediate\
**Mode:** Individual or pairs

## Business scenario

You have joined the development team at TechNova Solutions. The team is
building an Employee Management System for the HR department. The source
code is maintained in GitHub, and developers use branches, commits,
pushes, pulls, and pull requests to collaborate.

Your team follows these practices:

-   `main` contains stable code.
-   Developers create feature branches for new work.
-   Meaningful changes must be committed.
-   Feature branches must be pushed to GitHub.
-   Completed work is integrated into `main` after review.
-   Developers must synchronize their local repository with remote
    changes.

**Repository:** `https://github.com/fortrainees2020/gitdemo`

Your task is to complete the activities below as a developer. The focus
is Git and GitHub; a complete Java application is not required.

## Learning objectives

By the end of the lab, you should be able to:

1.  Clone a repository and inspect its configuration.
2.  Create and switch branches.
3.  Create, modify, rename, and delete files.
4.  Stage and commit changes.
5.  Push a feature branch to GitHub.
6.  Fetch and pull remote changes.
7.  Merge branches and resolve merge conflicts.
8.  Inspect commit history and repository status.

## Required project structure

Create the following structure in the repository as you work through the
tasks:

``` text
gitdemo/
├── README.md
├── src/
│   ├── Employee.java
│   ├── Department.java
│   ├── Manager.java
│   └── EmployeeService.java
├── docs/
│   └── requirements.md
└── .gitignore
```

## Lab tasks

### Task 1 -- Clone and inspect the repository (10 minutes)

1.  Navigate to your workspace.
2.  Clone the repository.
3.  Open the cloned directory.
4.  Identify the current branch.
5.  Display the remote URL.
6.  View the recent commit history.
7.  Check the working-tree status.

### Task 2 -- Create a feature branch (10 minutes)

1.  Create a branch named `feature/employee-management`.
2.  Switch to that branch.
3.  Verify that you are no longer working on `main`.

### Task 3 -- Create the initial project files (15 minutes)

Create these files:

-   `src/Employee.java`
-   `src/Department.java`
-   `src/Manager.java`
-   `src/EmployeeService.java`
-   `docs/requirements.md`

Use the following content.

**Employee.java**

``` java
public class Employee {
    private int employeeId;
    private String employeeName;
    private String email;
    private double salary;
}
```

**Department.java**

``` java
public class Department {
    private int departmentId;
    private String departmentName;
}
```

**Manager.java**

``` java
public class Manager {
    private int managerId;
    private String managerName;
}
```

**EmployeeService.java**

``` java
public class EmployeeService {
    public void addEmployee() {
        System.out.println("Employee added successfully");
    }

    public void displayEmployees() {
        System.out.println("Displaying employees");
    }
}
```

**requirements.md**

``` text
Employee Management System Requirements

1. Add Employee
2. View Employee
3. Update Employee
4. Delete Employee
5. Assign Employee to Department
```

Stage and commit the files with a meaningful commit message.

### Task 4 -- Modify existing files (10 minutes)

The team lead requests these changes:

1.  Add `private String phoneNumber;` to `Employee.java`.
2.  Add a `deleteEmployee()` method to `EmployeeService.java` that
    prints `Employee deleted successfully`.

Review the changes before staging. Stage only the two modified files and
commit them.

### Task 5 -- Rename and delete files (10 minutes)

Following a code review:

1.  Rename `src/Manager.java` to `src/TeamManager.java`.
2.  Delete `src/Department.java`.
3.  Check the Git status.
4.  Commit the rename and deletion.

### Task 6 -- Push your feature branch (10 minutes)

1.  Push `feature/employee-management` to GitHub.
2.  Verify that the branch is visible on GitHub.
3.  Inspect the files and commit history on the remote branch.

### Task 7 -- Simulate a change made by another developer (15 minutes)

Developer B updates `README.md` directly on GitHub's `main` branch with
the following content:

``` text
Employee Management System

Developed by TechNova Solutions.

Modules:
1. Employee Management
2. Department Management
3. Payroll Management

Technology: Java
Version Control: Git and GitHub
```

Make this change using the GitHub web interface. Then update your local
`main` branch and inspect the new commit.

### Task 8 -- Synchronize your feature branch (10 minutes)

Your feature branch was created before Developer B's README update.

1.  Switch to `feature/employee-management`.
2.  Integrate the latest changes from `main`.
3.  Verify that the feature branch contains the latest README content
    and your feature files.

### Task 9 -- Merge the feature into main (10 minutes)

1.  Push the latest feature branch changes.
2.  Create a Pull Request from `feature/employee-management` into
    `main`.
3.  Review the changed files.
4.  Merge the Pull Request.
5.  Update your local `main` branch.
6.  Inspect the commit graph.

### Task 10 -- Create and resolve a merge conflict (10 minutes)

Two developers independently update the same project-status line in
`README.md`.

-   Developer A's version: `Project Status: Development Completed`
-   Developer B's version: `Project Status: Testing In Progress`

Simulate both changes using local Git branches:

1.  Create a branch named `feature/project-status` from `main`.
2.  On that branch, change the README status line to Developer A's
    version and commit it.
3.  Switch to `main`.
4.  Change the same status line to Developer B's version and commit it.
5.  Merge `feature/project-status` into `main`.
6.  Inspect the conflict markers in `README.md`.
7.  Resolve the conflict with this final line:

``` text
Project Status: Testing In Progress - Development Completed
```

8.  Stage the resolved file and complete the merge.
9.  Verify the repository status and commit history.

### Final verification (10 minutes)

Verify all of the following:

-   [ ] The employee management files are present.
-   [ ] `Manager.java` has been renamed to `TeamManager.java`.
-   [ ] `Department.java` has been deleted.
-   [ ] The latest README content is present.
-   [ ] The feature branch was pushed and merged.
-   [ ] The merge conflict was resolved.
-   [ ] Commit history is visible.
-   [ ] The working tree is clean.

## Additional challenge questions

Complete these if you finish early:

1.  How do you unstage a file without deleting it?
2.  How do you discard uncommitted changes in a modified file?
3.  How do you compare two commits?
4.  How do you delete a remote branch?
5.  How do you create and push a Git tag named `v1.0`?
6.  How do you revert a previously committed change?
7.  What is the difference between `git fetch` and `git pull`?
8.  What can happen when two developers push to the same branch?

## Submission requirements

Submit:

1.  GitHub repository URL.
2.  Screenshot showing your local branches.
3.  Screenshot of the feature branch on GitHub.
4.  Screenshot of the merged Pull Request.
5.  Screenshot showing the resolved merge conflict.
6.  Output of `git log --oneline --graph --all`.

## Evaluation rubric

  Skill                                Marks
  -------------------------------- ---------
  Clone and repository setup              10
  Branch creation and management          10
  File creation and modification          15
  Staging and committing                  15
  Push and pull operations                15
  Remote synchronization                  10
  Merge and conflict resolution           20
  History and final verification           5
  **Total**                          **100**

**Important:** Confirm that you have write access to the practice
repository before starting. If you do not, use a fork or an individual
practice repository supplied by your instructor. Do not commit directly
to a shared `main` branch unless your instructor explicitly asks you to.
