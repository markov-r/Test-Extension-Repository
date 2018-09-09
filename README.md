# Extension-Repository
Extension Repository is a web application for browsing and downloading of extensions for popular software development platforms.
The available functionalities are:
- User registration, login, logout, including validation for username, password and email
- There is public part (accessible w/o authentication), private part and admin only part
- Public part shows "New", "Featured", "Popular" approved extensions
- New lists the newest extensions (sorted by upload date)
- Featured lists the extensions selected by site administration
- Popular lists the extensions that have been downloaded the most
- There is a search functionality for extensions
- Also search by extension tags, created with 
- Integrates with GitHub and fetches data automatically. Git data is refreshed automatically on a predefined interval.
  The interval can be changed by an admin, the entered value is in minutes.
- Integration with continuous integration server (Jenkins)
- REST API for the public part (can be tested using Postman)
- Using optimistic locking for System Properties (Git update interval, last successful or failed sync date/time etc.)
- Approve extensions (admin only)
- Disable/delete users (admin only)

When a new extension is created, it is in pending state and need to be manually approved by a site admin.
The extensions are also assigned different tags, like e.g. "Java", "Intellij-Idea", "Plugin", etc.
Clicking on one tag leads you to a list of all extensions that share the respective tag.
On each extension page there's a link to download the file. The number of times the extension have been downloaded is counted and used
in "Popular" carousel.
When a user is logged in he can view/list his own extensions and edit/delete them.
In addition he can also view his account details.
Extension fields number of open issues, number of pull requests and last commit date are fetched from GitHub. 
The admins can disable or delete users and approve or delete all extensions, not only those created (owned) by them.
When a user is disabled he is not allowed to login, but his user data is still kept.
Admin panel also shows GitHub Synchronization Details:
- synchronization interval (shown in miliseconds)
- last successful synchronization date and time
- last failed synchronization date and time
- details about last failed sync

The used technologies -> Java, Spring Boot, Hibernate, MariaDB, Thymeleaf, Bootstrap

**Installation instructions:

Clone the repository from GitHub and build it as a Gradle project using auto import.

If you want to use an example database, use ext_repo.sql

Replace your user/password for the database in application properties file.

The application is set to run on port 8081, change it if you need another port.

Open localhost:8081 and enjoy!
