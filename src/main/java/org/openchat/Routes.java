package org.openchat;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import org.openchat.api.LoginAPI;
import org.openchat.api.PostsAPI;
import org.openchat.api.UsersAPI;
import org.openchat.domain.posts.PostService;
import org.openchat.domain.users.IdGenerator;
import org.openchat.domain.users.UserRepository;
import org.openchat.domain.users.UserService;

public class Routes {

    private UsersAPI usersAPI;
    private LoginAPI loginAPI;
    private PostsAPI postsAPI;

    public void create() {
        createAPIS();
        swaggerRoutes();
        openchatRoutes();
    }

    private void createAPIS() {
        IdGenerator idGenerator = new IdGenerator();
        UserRepository userRepository = new UserRepository();

        UserService userService = new UserService(idGenerator, userRepository);
        PostService postService = new PostService();
        usersAPI = new UsersAPI(userService);
        loginAPI = new LoginAPI(userRepository);
        postsAPI = new PostsAPI(postService);
    }

    private void openchatRoutes() {
        get("status", (req, res) -> "OpenChat: OK!");
        post("users", (req, res) -> usersAPI.createUser(req, res));
        post("login", (req, res) -> loginAPI.login(req, res));
        post("users/:userId/timeline", (req, res) -> postsAPI.createPost(req, res));
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
