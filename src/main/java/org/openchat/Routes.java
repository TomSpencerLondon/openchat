package org.openchat;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import org.openchat.api.UsersAPI;
import org.openchat.domain.users.IdGenerator;
import org.openchat.domain.users.UserRepository;
import org.openchat.domain.users.UserService;

public class Routes {

    private UsersAPI usersAPI;

    public void create() {
        createAPIS();
        swaggerRoutes();
        openchatRoutes();
    }

    private void createAPIS() {
        IdGenerator idGenerator = new IdGenerator();
        UserRepository userRepository = new UserRepository();

        UserService userService = new UserService(idGenerator, userRepository);
        usersAPI = new UsersAPI(userService);
    }

    private void openchatRoutes() {
        get("status", (req, res) -> "OpenChat: OK!");
        post("users", (req, res) -> usersAPI.createUser(req, res));
    }

    private void swaggerRoutes() {
        options("users", (req, res) -> "OK");
        options("login", (req, res) -> "OK");
        options("users/:userId/timeline", (req, res) -> "OK");
        options("followings", (req, res) -> "OK");
        options("followings/:userId/followees", (req, res) -> "OK");
        options("users/:userId/wall", (req, res) -> "OK");
    }
}
