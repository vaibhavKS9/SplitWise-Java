# SplitWise-Java
* Built a Splitwise-like expense sharing system (Java, OOP) supporting groups, users, expenses, debt tracking, and settlements, enabling users to add expenses and view balances across a group.

* Implemented optimal debt simplification using Bitmask DP + subset partitioning, generating the minimum number of transactions required to settle all group expenses.

* Designed extensible expense splitting via the Strategy Pattern (Equal/Exact/Percentage splits), making it easy to plug in new split methods without modifying core group logic (Open/Closed Principle).

* Added Builder Pattern for fluent, safe creation of User and Group objects with required fields + defaults, improving readability and preventing invalid state during initialization.

* Developed end-to-end expense lifecycle: add expense → compute per-user share → update net balances → produce simplified settlement graph → allow per-user settle-up to update balances incrementally.

* Provided clear financial visibility by supporting “show all group expenses” and “show simplified transactions” views, helping users understand where money went and how to settle quickly.
