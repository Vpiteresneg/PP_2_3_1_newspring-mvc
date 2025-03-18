
/// /В приложении должна быть страница, на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера.
/// /4. Конфигурация Spring через JavaConfig и аннотации, по аналогии с предыдущими проектами. Без использования xml. Без Spring Boot.
/// /5. Внесите изменения в конфигурацию для работы с базой данных. Вместо SessionFactory должен использоваться EntityManager.
/// /6. Используйте только GET/POST маппинги
/// /7. Используйте ReqestParam аннотацию, использование аннотации PathVariable запрещено
/// /GET используется для запроса данных с сервера
/// /POST используется для отправки данных на сервер
//@Controller
//@RequestMapping("/users")//задает общий url
//public final class UsersController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping()//для получения данных с сервера.
//    public String findAll(Model model) {  // Страница для отображения всех пользователей
//        //получим всех пользователей из dаo и передадим на отображение в представление
//
//        model.addAttribute("users", userService.getAll());//назовем переменную "users", обратимся к dao и вызовем метод getUsers, под ключом"users" у нас будет лежать список из людей
//        return "user/users";// вернем ту страницу (шаблон) который будет отображать список из людей
//    }
//
//    @GetMapping("/show")
//    public String show(@RequestParam("id") Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        return "users/show";
//    }
////метод будет возвращать html форму для создания нового человека
//    @GetMapping("/new")// по запросу/user/new вернется в браузере htlm форма по созданию нового человека
//    public String newUser(Model model) {//внедряем модель т.к. используем таймлиф форму
//        model.addAttribute("user", new User());
//
//        return "user/new";
//    }
//    // метод будет принимать на вход post запрос(получаем данные из формы и создаем нового юзера положить в него данные которые пришли из формы и добавить этого юзера в БД), будет брать данные из этого постзапроса и будет добавлять нового человека в БД с помощью dao
//
//
//    @PostMapping()
//public String create(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/users";//переходим на эту страницу, когда человек будет добавлен в БД браузер перейдет на эту страницу со всеми юзерами
//}
//
//

package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/new";// Страница для создания нового пользователя
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users"; // Редирект на страницу списка пользователей
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/edit";// Страница для редактирования пользователя
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}



