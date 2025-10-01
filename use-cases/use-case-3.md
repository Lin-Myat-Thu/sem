# USE CASE 3: Produce a Report on the Salary of Employees in a Department (by Manager)
## CHARACTERISTIC INFORMATION

### Goal in Context
As a department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.

### Scope
Department.

### Level
Primary task.

### Preconditions
Department manager is authenticated. Database contains current employee salary data.

### Success End Condition
A report is available for the department manager to provide to finance.

### Failed End Condition
No report is produced.

### Primary Actor
Department Manager.

### Trigger
A request for departmental financial information is needed.

### MAIN SUCCESS SCENARIO

1. Department manager requests salary information for their department.

2. System authenticates department manager.

3. System extracts current salary information of employees in that manager’s department.

4. System provides report to department manager.

### EXTENSIONS

- Department has no employees:
1. Report shows no employees exist in the department.

### SUB-VARIATIONS
None.

### SCHEDULE

DUE DATE: Release 1.0