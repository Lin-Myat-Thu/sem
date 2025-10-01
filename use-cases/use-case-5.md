# USE CASE 5: Add a New Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context
As an HR advisor I want to add a new employee's details so that I can ensure the new employee is paid.

### Scope
Company.

### Level
Primary task.

### Preconditions
HR advisor is authenticated. Employee data is available (e.g., name, role, salary, department).

### Success End Condition
New employee record is stored in the database and payroll is updated.

### Failed End Condition
Employee details are not added.

### Primary Actor
HR Advisor.

### Trigger
A new employee joins the company.

### MAIN SUCCESS SCENARIO
1. HR advisor selects “Add Employee” function.

2. HR advisor enters new employee details (name, role, department, salary, etc.).

3. System validates input.

4. System stores employee details in database.

5. System confirms employee successfully added.

### EXTENSIONS

1. Validation fails (e.g., missing or invalid data):
- System requests correction of data.

2. Database connection fails:
- System informs HR advisor that employee could not be added.

### SUB-VARIATIONS

None.

### SCHEDULE

DUE DATE: Release 1.0