# Extension-Repository
Extension Repository is a web application for browsing and downloading of extensions for popular software development tools.
The public part (home page) lists all extensions, together with tabs "Featured", "New" and "Popular":

Featured extensions are those selected by site administrators.
New extensions are the extensions that have been updated most recently.
Popular extensions are those that have been downloaded the most.

There is a registration form for new users and of course a login form too.
When logged in the user can add a new extension, list and browse all extensions; they can be downloaded too.
The extensions are also assigned different tags, like e.g. "Java", "Intellij-Idea", "Plugin", etc.
Clicking on one tag leads you to a list of all extensions that share the tag in question.

The fields number of open issues, number of pull requests and last commit date are automatically fetched from GitHub *. 
The admins can disable or delete users and approve or delete extensions.

- Special thanks to Kohsuke Kawaguchi-san for his nice GitHub API.
