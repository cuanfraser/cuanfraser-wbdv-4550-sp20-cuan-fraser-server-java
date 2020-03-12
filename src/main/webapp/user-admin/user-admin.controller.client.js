(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn, $updateBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    var $currentId;
    $(main);

    function main() {
        console.log("main()");

        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");

        $createBtn = $(".wbdv-create");
        $createBtn.click(createUser);
        $updateBtn = $(".wbdv-update");
        $updateBtn.click(() => updateUser($currentId))
        $tbody = $(".wbdv-tbody");
        $userRowTemplate = $(".wbdv-template");

        findAllUsers();
    }

    function createUser() {
        console.log("createUser()");

        const newUser = new User($usernameFld.val(), $passwordFld.val(), $firstNameFld.val(), $lastNameFld.val(), $roleFld.val());

        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");

        userService.createUser(newUser)
            .then((actualUser) => {
                renderUser(actualUser)
            })
    }


    function findAllUsers() {
        console.log("findAllUsers()");

        userService
            .findAllUsers()
            .then(theusers => {
                users = theusers
                renderUsers(users)
            })
    }

    function findUserById(userId) {
        console.log("findUserById()");

        return userService.findUserById(userId);
    }

    function deleteUser(userId) {
        console.log(`deleteUser(${userId})`);

        $target = $tbody.find(`#${userId}`);
        $target.remove();

        userService.deleteUser(userId)
            .then(response => {
                renderUsers()
            })
    }

    function selectUser(userId) {

        $currentId = userId;

        findUserById(userId)
            .then(actualUser => {
                $usernameFld.val(actualUser.username)
                $passwordFld.val(actualUser.password)
                $firstNameFld.val(actualUser.firstName)
                $lastNameFld.val(actualUser.lastName)
                $roleFld.val(actualUser.role)
            })
    }

    function updateUser(userId) {
        console.log(`updateUser(${userId})`);

        const updatedUser = new User($usernameFld.val(), $passwordFld.val(), $firstNameFld.val(), $lastNameFld.val(), $roleFld.val());

        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");

        userService.updateUser(userId, updatedUser)
            .then((actualUser) => {
                findAllUsers()
            })
    }

    function renderUser(user) {
        console.log("renderUser(user)");

        const row = $userRowTemplate.clone();
        const usernameCol = row.find(".wbdv-username");
        const firstnameCol = row.find(".wbdv-first-name");
        const lastnameCol = row.find(".wbdv-last-name");
        const roleCol = row.find(".wbdv-role");
        const removeCol = row.find("#wbdv-remove");
        const editCol = row.find("#wbdv-edit");

        usernameCol.html(user.username);
        firstnameCol.html(user.firstName);
        lastnameCol.html(user.lastName);
        roleCol.html(user.role);

        removeCol.attr("id", user._id);
        editCol.attr("id", user._id);
        row.attr("id", user._id);

        $removeBtn = removeCol;
        $editBtn = editCol;
        $removeBtn.click(() => deleteUser(user._id))
        $editBtn.click(() => selectUser(user._id))

        $tbody.append(row);
    }

    function renderUsers(users) {
        console.log("renderUsers(users)");

        for (let u in users) {
            let user = users[u]
            renderUser(user);
        }


    }

})();
