# USE CASE 6: View an Employee’s Details

## CHARACTERISTIC INFORMATION

### Goal in Context
As an HR advisor I want to view an employee's details so that the employee's promotion request can be supported.

### Scope
Company.

### Level
Primary task.

### Preconditions
HR advisor is authenticated. Database contains employee details.

### Success End Condition
HR advisor views employee’s details.

### Failed End Condition
Employee details are not shown.

### Primary Actor
HR Advisor.

### Trigger
A request to review an employee’s promotion.

### MAIN SUCCESS SCENARIO

1. HR advisor selects “View Employee Details.”

2. HR advisor enters employee identifier (e.g., employee ID).

3. System retrieves employee details.

4. HR advisor views employee details on screen.

### EXTENSIONS

1. Employee does not exist:
- System informs HR advisor that employee is not found.

### SUB-VARIATIONS

None.

### SCHEDULE

DUE DATE: Release 1.0