# USE CASE 8: Delete an Employee’s Details
## CHARACTERISTIC INFORMATION

### Goal in Context
As an HR advisor I want to delete an employee’s details so that the company is compliant with data retention legislation.

### Scope
Company.

### Level
Primary task.

### Preconditions
HR advisor is authenticated. Employee exists in database. Retention period is met.

### Success End Condition
Employee details are deleted from the system.

### Failed End Condition
Employee details are not deleted.

### Primary Actor
HR Advisor.

### Trigger
Data retention period has expired or employee has left the company.

### MAIN SUCCESS SCENARIO
1. HR advisor selects “Delete Employee.”

2. HR advisor enters employee identifier.

3. System checks compliance with data retention policy.

4. System deletes employee details.

5. System confirms deletion.

### EXTENSIONS

### Employee not found:
i. System informs HR advisor employee does not exist.

### Retention period not met:
i. System blocks deletion and informs HR advisor.

### SUB-VARIATIONS

None.

### SCHEDULE

DUE DATE: Release 1.0