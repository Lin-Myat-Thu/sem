# USE CASE 7: Update an Employee’s Details
## CHARACTERISTIC INFORMATION

### Goal in Context
As an HR advisor I want to update an employee’s details so that employee’s details are kept up-to-date.

### Scope
Company.

### Level
Primary task.

### Preconditions
HR advisor is authenticated. Employee exists in database.

### Success End Condition
Employee details are updated successfully.

### Failed End Condition

Employee details are not updated.

### Primary Actor

HR Advisor.

### Trigger

HR identifies a need to update employee information.

### MAIN SUCCESS SCENARIO

1. HR advisor selects “Update Employee Details.”

2. HR advisor enters employee identifier.

3. System retrieves current details.

4. HR advisor edits details (e.g., salary, role, department).

5. System validates input.

6. System updates employee details.

7. System confirms changes.

### EXTENSIONS

1. Employee not found:
- System informs HR advisor employee does not exist.

2. Validation fails:
-  System requests correction.

### SUB-VARIATIONS

None.

### SCHEDULE

DUE DATE: Release 1.0